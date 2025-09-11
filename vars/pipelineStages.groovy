def call(String branch, String repoUrl) {
    pipeline {
        agent any

        stages {
            stage('Checkout') {
                steps {
                    checkoutrepo(branch, repoUrl)
                }
            }
            stage('Build') {
                steps {
                    script {
                        build()
                    }
                }
            }
            stage('Deploy') {
                steps {
                    script {
                        deploy()
                    }
                }
            }
        }

        post {
            success { echo "✅ Pipeline finished successfully" }
            failure { echo "❌ Pipeline failed" }
        }
    }
}
