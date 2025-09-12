def call(String branch, String repoUrl) {
    return [
        [name: 'Checkout', action: { checkoutrepo(branch, repoUrl) }],
        [name: 'Build', action: { build() }],
        [name: 'Deploy', action: { deploy() }]
    ]
}
