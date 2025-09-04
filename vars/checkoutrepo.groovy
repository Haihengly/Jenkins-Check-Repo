def call(String branch, String repoUrl) {
    stage('Checkout') {
        script {
            git branch: branch, url: repoUrl
        }
    }
}
