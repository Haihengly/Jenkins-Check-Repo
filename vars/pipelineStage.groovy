def call(Map config) {
    def listStage = allStage(config)
    def STORE_DIR = "/My-Docker/Dev-Service"
    def gitRepoDir = "${env.WORKSPACE}/.scm-detect"

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
                    def gitRepoDir = "${env.WORKSPACE}/.scm-detect" // always real Git repo
                    telegramNotify.notify("SUCCESS", gitRepoDir, config)
                }
            }
            failure { 
                script {
                    def gitRepoDir = "${env.WORKSPACE}/.scm-detect"
                    telegramNotify.notify("FAILURE", gitRepoDir, config)
                }
            }
            unstable { 
                script {
                    def gitRepoDir = "${env.WORKSPACE}/.scm-detect"
                    telegramNotify.notify("UNSTABLE", gitRepoDir, config)
                }
            }
        }
    }
}
