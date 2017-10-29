<#macro booking eventId>

<div class="card">
    <form method="POST" action="/booking/add" class="card-row">
        <div class="card-row">
            <label for="email">User email:</label>
            <input id="email" type="email" name="email"/>
        </div>

        <div class="card-row">
            <label for="seat">Seat:</label>
            <input id="seat" type="number" name="seat"/>
        </div>

        <div class="card-row">
            <label for="dataTime">Time:</label>
            <input id="dataTime" type="datetime-local" name="dataTime"/>
        </div>

        <input type="hidden" value="${eventId}" name="eventId">

        <input class="input-label card-row green" type="submit" value="Submit" />
    </form>
</div>
</#macro>