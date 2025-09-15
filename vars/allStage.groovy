def call(String branch, String repoUrl, Map buildParams = [:]) {
    return [
        [name: 'Checkout', action: { checkoutrepo(branch, repoUrl) }],
        [name: 'Build', action: { build(buildParams) }],
        [name: 'Deploy', action: { deploy(buildParams) }]
    ]
}
