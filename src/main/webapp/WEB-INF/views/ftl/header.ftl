<div class="header">
    <a id="show-sidebar" class="header-item">
        <i class="material-icons md-18">view_headline</i>
    </a>

    <span class="header-item">
        <a href="/events">Cinema</a>
    </span>

    <@security.authorize access="isAuthenticated()">
        <div class="header-right">
            <a href="/users/profile?email=<@security.authentication property="principal.username"/>" class="header-item">
                <@security.authentication property="principal.username"/>
            </a>
            <a href="/logout" class="header-item">
                <i class="material-icons md-18">exit_to_app</i>
            </a>
        </div>
    </@security.authorize>

    <@security.authorize access="! isAuthenticated()">
        <div class="header-right">
            <a href="/login" class="header-item">Sign in</a>
            <a href="/registration/form" class="header-item">Sign up</a>
        </div>
    </@security.authorize>
</div>