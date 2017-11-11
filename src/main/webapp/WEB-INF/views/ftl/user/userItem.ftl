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

<#macro userMoney userAccount>
<b class="card-label">Money</b>
<div class="card">
    <div class="card-row">${userAccount.money} $</div>
</div>

<b class="card-label">Add money</b>
<div class="card">
    <form method="POST" action="/money/add" class="card-row">
        <div class="card-row">
            <label for="money">Money:</label>
            <input id="money" type="number" name="money"/>
            <label for="money">$</label>
        </div>

        <input type="hidden" value="${userAccount.userId}" name="userId">

        <input class="input-label card-row green" type="submit" value="Submit" />
    </form>
</div>
</#macro>