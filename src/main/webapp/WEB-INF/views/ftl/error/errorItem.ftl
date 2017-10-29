<#macro errorItem error>
<div class="card-alert">
    <div class="alert-icon">
        <i class="material-icons md-light">error</i>
    </div>
    <div class="alert-body">
        <div class="alert-row">${error.name}</div>
        <div class="alert-row">${error.message}</div>
    </div>
</div>
</#macro>