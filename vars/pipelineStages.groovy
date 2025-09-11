def call(String branch, String repoUrl) {
    pipeline {
        agent any

        stages {
            stage('Checkout') {
                steps {
                    checkoutStage(branch, repoUrl)
                }
            }
            stage('Build') {
                steps {
                    buildStage()
                }
            }
            stage('Deploy') {
                steps {
                    deployStage()
                }
            }
        }

        post {
            success { echo "✅ Pipeline finished successfully" }
            failure { echo "❌ Pipeline failed" }
        }
    }
}
