def call(String branchName , String repoUrl) {
    stage('Checkout') {
        steps {
            git branch: "${branchName}", url: "${repoUrl}"
        }
    }
}