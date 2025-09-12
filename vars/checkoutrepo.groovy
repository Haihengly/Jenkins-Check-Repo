def call(String branch, String repoUrl) {
    git branch: branch, url: repoUrl, credentialsId: 'git_token'
}
