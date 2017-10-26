<#macro userItem user>
<div class="card">
    <div class="card-row">${user.firstName} ${user.lastName}</div>
    <div class="card-row">Email: ${user.email}</div>
    <div class="card-row">Birthday: ${user.birthday}</div>

    <div class="card-action">
        <a class="card-action-item" href="byId?id=${user.id}">Tickets</a>
    </div>
</div>
</#macro>