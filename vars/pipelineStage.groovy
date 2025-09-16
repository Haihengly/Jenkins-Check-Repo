// def call(String branch, String repoUrl) {
//     def listStage = allStage(branch, repoUrl)
//     pipeline {
//         agent any
//         environment {
//             BOT_TOKEN = credentials('TELEGRAM_BOT_TOKEN')
//             CHAT_ID   = credentials('TELEGRAM_CHAT_ID')
//         }
//         stages {
//             stage('Checkout Repo') {
//                 steps {
//                     checkoutrepo(branch, repoUrl)
//                 }
//             }
//             stage('Build Stage') {
//                 steps {
//                     script {
//                         build()
//                     }
//                 }
//             }
//             stage('Deploy Stage') {
//                 steps {
//                     script {
//                         deploy()
//                     }
//                 }
//             }
//         }
//         post {
//             success { script { telegramNotify.notify("SUCCESS") } }
//             failure { script { telegramNotify.notify("FAILURE") } }
//             unstable { script { telegramNotify.notify("UNSTABLE") } }
//         }
//     }
// }


def call(PipelineConfig config) {
    def listStage = allStage(config)

    pipeline {
        agent any
        environment {
            BOT_TOKEN = credentials('TELEGRAM_BOT_TOKEN')
            CHAT_ID   = credentials('TELEGRAM_CHAT_ID')
        }
        stages {
            // Use a single stage to wrap dynamic stages in scripted block
            stage('Run Dynamic Stages') {
                steps {
                    script {
                        for (s in listStage) {
                            echo "Running stage: ${s.name}"
                            // Wrap each dynamic stage in a 'stage' method (scripted)
                            stage(s.name) {
                                s.action()
                            }
                        }
                    }
                }
            }
        }
        post {
            success { script { telegramNotify.notify("SUCCESS") } }
            failure { script { telegramNotify.notify("FAILURE") } }
            unstable { script { telegramNotify.notify("UNSTABLE") } }
        }
    }
}
