<#macro upload>

<div class="card">
    <form method="POST" action="/upload/files" enctype="multipart/form-data" class="card-row">
        <label class="input-label card-row" for="file1">Choose a user file</label>
        <input type="file" name="files" id="file1" class="input-file" />


        <label class="input-label card-row" for="file2">Choose a event file</label>
        <input type="file" name="files" id="file2" class="input-file" />

        <input class="input-label card-row green" type="submit" value="Submit" />
    </form>
</div>
</#macro>