<#macro eventItem event>
<div class="card">
    <div class="card-row">${event.name} (${event.rating}) - ${event.basePrice}</div>

    <div class="card-action">
        <a class="card-action-item" href="/auditoriums/byEventId?id=${event.id}">auditoriums</a>
    </div>
</div>
</#macro>