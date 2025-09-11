def call(String branch, String repoUrl) {
    // Checkout the branch
    git branch: branch, url: repoUrl
}
