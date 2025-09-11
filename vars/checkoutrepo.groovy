def call(String branch, String repoUrl) {
    script {
        git branch: branch, url: repoUrl
    }
}
