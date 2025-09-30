def call(Map config) {
    def listStage = allStage(config)
    def STORE_DIR = "/My-Docker/Dev-Service"

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
                    telegramNotify.notify("SUCCESS", "${STORE_DIR}/${config.FOLDER}-${config.envName}")
                }
            }
            failure { 
                script {
                    telegramNotify.notify("FAILURE", "${STORE_DIR}/${config.FOLDER}-${config.envName}")
                }
            }
            unstable { 
                script {
                    telegramNotify.notify("UNSTABLE", "${STORE_DIR}/${config.FOLDER}-${config.envName}")
                }
            }
        }
    }
}
