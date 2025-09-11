def call(String branch, String repoUrl) {
    stage('Checkout') {
        steps {
            script {
                git branch: branch, url: repoUrl
            }
        }
    }
}
