def call() {
    pipeline {
        agent any

        stages {
            stage('Checkout') {
                steps {
                    // checkoutrepo(branch, repoUrl)
                    sh 'echo "Checkout stage placeholder"'
                }
            }
            // stage('Build') {
            //     steps {
            //         script {
            //             build()
            //         }
            //     }
            // }
            // stage('Deploy') {
            //     steps {
            //         script {
            //             deploy()
            //         }
            //     }
            // }
        }
    }
}
