def call(Map config) {
    git (
        branch: config.branch, 
        url: config.repoUrl, 
        credentialsId: 'git_token'
    )
}
