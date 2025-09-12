def call() {
    pipeline {
        agent any
        environment {
            BOT_TOKEN = credentials('TELEGRAM_BOT_TOKEN')
            CHAT_ID   = credentials('TELEGRAM_CHAT_ID')
            // BRANCH_NAME = 'main'
            // REPO_URL = 'https://github.com/Haihengly/Products-Jenkins'
        }
        stages {
            stage('Run All Stages') {
                steps {
                    script {
                        myStages()
                    }
                }
            }
        }
        // stages {
        //     stage('Checkout') {
        //         steps {
        //             checkoutrepo(BRANCH_NAME, REPO_URL)
        //         }
        //     }
        //     stage('Build') {
        //         steps {
        //             script {
        //                 build()
        //             }
        //         }
        //     }
        //     stage('Deploy') {
        //         steps {
        //             script {
        //                 deploy()
        //             }
        //         }
        //     }
        // }
        post {
            always{
                success { script { telegramNotify.notify("SUCCESS") } }
                failure { script { telegramNotify.notify("FAILURE") } }
                unstable { script { telegramNotify.notify("UNSTABLE") } }
            }
        }
    }
}
