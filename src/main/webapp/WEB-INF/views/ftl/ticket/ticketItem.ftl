<#macro ticketItem ticket>
<div class="card">
    <div class="card-row">Time: ${ticket.dateTime}</div>
    <div class="card-row">Seat: ${ticket.seat}</div>
    <div class="card-action">
        <a class="card-action-item" href="/users/byId?id=${ticket.user.id}">User</a>
        <a class="card-action-item" href="/events/byId?id=${ticket.event.id}">Event</a>
    </div>
</div>
</#macro>