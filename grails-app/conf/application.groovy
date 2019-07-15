// Added by the Spring Security Core plugin:
grails.plugin.springsecurity.userLookup.userDomainClassName = 'auth.Usuario'
grails.plugin.springsecurity.userLookup.authorityJoinClassName = 'auth.UsuarioRol'
grails.plugin.springsecurity.authority.className = 'auth.Rol'

grails.plugin.springsecurity.successHandler.defaultTargetUrl = '/inicio'
grails.plugin.springsecurity.auth.ajaxLoginFormUrl = '/login'


grails.plugin.springsecurity.controllerAnnotations.staticRules = [
        [pattern: '/**', access: ['IS_AUTHENTICATED_REMEMBERED']],
        [pattern: '/*', access: ['IS_AUTHENTICATED_REMEMBERED']],
        [pattern: '/', access: ['IS_AUTHENTICATED_REMEMBERED']],
        [pattern: '', access: ['IS_AUTHENTICATED_REMEMBERED']],
        [pattern: '/dbconsole/**', access: ['IS_AUTHENTICATED_REMEMBERED']],
        [pattern: '/login/*',        access: ['permitAll']],
        [pattern: '/login/**',       access: ['permitAll']],
        [pattern: '/logout/**',       access: ['permitAll']],
        [pattern: '/auth.gsp', access: ['IS_AUTHENTICATED_REMEMBERED']],
        [pattern: '/shutdown', access: ['permitAll']],
        [pattern: '/assets/**', access: ['permitAll']],
        [pattern: '/**/js/**', access: ['permitAll']],
        [pattern: '/**/css/**', access: ['permitAll']],
        [pattern: '/**/images/**', access: ['permitAll']],
        [pattern: '/**/favicon.ico', access: ['permitAll']]
]

grails.plugin.springsecurity.filterChain.chainMap = [
        [pattern: '/assets/**', filters: 'none'],
        [pattern: '/**/js/**', filters: 'none'],
        [pattern: '/**/css/**', filters: 'none'],
        [pattern: '/**/images/**', filters: 'none'],
        [pattern: '/**/favicon.ico', filters: 'none'],
        [pattern: '/**', filters: 'JOINED_FILTERS']
]




