<#macro booking eventId>
<#assign  security=JspTaglibs["http://www.springframework.org/security/tags"] />

<div class="card">
    <form method="POST" action="/booking/add" class="card-row">
        <@security.authorize access="isAuthenticated()">
            <div class="card-row">
                <@security.authentication property="principal.username"/>
            </div>
        </@security.authorize>

        <div class="card-row">
            <#--<label for="email">User email:</label>-->
            <input id="email" type="email" name="email" value="<@security.authentication property="principal.username"/>" hidden>
        </div>

        <div class="card-row">
            <label for="seat">Seat:</label>
            <input id="seat" type="number" name="seat"/>
        </div>

        <div class="card-row">
            <#--<label for="dataTime">Time:</label>-->
            <input id="dataTime" type="datetime-local" name="dataTime" value="2017-06-01T08:30" hidden>
        </div>

        <input type="hidden" value="${eventId}" name="eventId">

        <input class="input-label card-row green" type="submit" value="Submit" />
    </form>
</div>
</#macro>