<#macro userItem user>
<div class="card">
    <div class="card-row">${user.firstName} ${user.lastName}</div>
    <div class="card-row">Email: ${user.email}</div>
    <div class="card-row">Birthday: ${user.birthday}</div>

    <div class="card-action">
        <a class="card-action-item" href="/tickets/byUserId?id=${user.id}">Tickets</a>
        <a class="card-action-item" href="/tickets/byUserIdAsPdf?id=${user.id}">PDF Tickets</a>
    </div>
</div>
</#macro>