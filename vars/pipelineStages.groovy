def call(String branch, String repoUrl) {
    pipeline {
        agent any

        stages {
            stage('Checkout') {
                steps {
                    checkoutrepo(branch, repoUrl)
                    '''
                        echo "Success"
                    '''
                }
            }
        }
    }
}