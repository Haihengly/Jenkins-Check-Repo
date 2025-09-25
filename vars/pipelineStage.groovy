def call(Map config) {
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
                            echo "Running : ${s.name}"
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
            success { 
                script {
                    dir("/My-Docker/Dev-Service/${config.BUILD_DIR}") {
                        telegramNotify.notify("SUCCESS")
                    }
                }
            }
            failure { 
                script {
                    dir("/My-Docker/Dev-Service/${config.BUILD_DIR}") {
                        telegramNotify.notify("FAILURE")
                    }
                }
            }
            unstable { 
                script {
                    dir("/My-Docker/Dev-Service/${config.BUILD_DIR}") {
                        telegramNotify.notify("UNSTABLE")
                    }
                }
            }
        }
    }
}
