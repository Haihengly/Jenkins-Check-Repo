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
            success { script { 
                sh "git config --global --add safe.directory $WORKSPACE" 
                telegramNotify.notify("SUCCESS") 
                } }
            failure { script { 
                sh "git config --global --add safe.directory $WORKSPACE"
                telegramNotify.notify("FAILURE") 
            } }
            unstable { script { 
                sh "git config --global --add safe.directory $WORKSPACE"
                telegramNotify.notify("UNSTABLE") 
            } }
        }
    }
}
