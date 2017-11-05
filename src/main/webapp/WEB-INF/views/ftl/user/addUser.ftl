<#macro addUser>
<div class="card">
    <div class="card-row"><h1>Registration:</h1></div>
    <form method="POST" action="/registration/add" class="card-row">
        <input class="in" type="text"       placeholder="First name"    name="firstName"    required="">
        <input class="in" type="text"       placeholder="Last name"     name="lastName"     required="">
        <input class="in" type="email"      placeholder="Email"         name="email"        required="">
        <input class="in" type="password"   placeholder="Password"      name="password"     required="">
        <input class="in" type="date"       placeholder="Birthday"      name="birthday"     required="">
        <input class="input-label card-row green" type="submit" value="Submit" />
    </form>
</div>
</#macro>