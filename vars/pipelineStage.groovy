def call(String branch, String repoUrl) {
    pipeline {
        agent any
        environment {
            BOT_TOKEN = credentials('TELEGRAM_BOT_TOKEN')
            CHAT_ID   = credentials('TELEGRAM_CHAT_ID')
        }
        stages {
            stage('Checkout Repo') {
                steps {
                    checkoutrepo(branch, repoUrl)

                }
            }
            stage('Build Stage') {
                steps {
                    script {
                        build()
                    }
                }
            }
            stage('Deploy Stage') {
                steps {
                    script {
                        deploy()
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
