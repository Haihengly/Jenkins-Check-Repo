def call() {
    pipeline {
        agent any
        environment {
            BOT_TOKEN = credentials('TELEGRAM_BOT_TOKEN')
            CHAT_ID   = credentials('TELEGRAM_CHAT_ID')
            BRANCH_NAME = 'main'
            REPO_URL = 'https://github.com/Haihengly/Products-Jenkins'
        }
        stages {
            stage('Checkout Repo') {
                steps {
                    checkoutrepo(BRANCH_NAME, REPO_URL)
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
