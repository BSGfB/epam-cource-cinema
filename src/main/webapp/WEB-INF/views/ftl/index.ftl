<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.w3.org/1999/xhtml">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Cinema sessions</title>
    <link rel="stylesheet" href="../../../resources/style/style.css">
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
</head>
<body>
<#import "./user/userItem.ftl" as userItem>
<#import "./user/addUser.ftl" as addUserForm>
<#import "./ticket/ticketItem.ftl" as ticketItem>
<#import "./events/eventItem.ftl" as eventItem>
<#import "./auditorium/auditoriumItem.ftl" as auditoriumItem>
<#import "./error/errorItem.ftl" as errorItem>
<#import "./util/upload.ftl" as uploadFile>
<#import "./util/booking.ftl" as booking>
<#include "header.ftl">
<#include "sidebar.ftl">

<div class="container">

    <#if event??>           <@eventItem.eventItem event=event/> </#if>
    <#if error??>           <@errorItem.errorItem error=error/> </#if>
    <#if upload??>          <@uploadFile.upload/>               </#if>
    <#if user??>            <@userItem.userItem user=user/>     </#if>
    <#if registration??>    <@addUserForm.addUser/>             </#if>

    <#if event?? && bookingFlag??><@booking.booking eventId=event.id/></#if>

    <#if users??>
        <b class="card-label">Users</b>
        <#list users as user>
            <@userItem.userItem user=user/>
        </#list>
    </#if>


    <#if ticket??><@ticketItem.ticketItem ticket=ticket/></#if>

    <#if tickets??>
        <b class="card-label">Tickets</b>
        <#list tickets as ticket>
            <@ticketItem.ticketItem ticket=ticket/>
        </#list>
    </#if>

    <#if events??>
        <b class="card-label">Events</b>
        <#list events as event>
            <@eventItem.eventItem event=event/>
        </#list>
    </#if>


    <#if auditorium??><@auditoriumItem.auditoriumItem auditorium=auditorium/></#if>

    <#if auditoriums??>
        <b class="card-label">Auditoriums</b>
        <#list auditoriums as auditorium>
            <@auditoriumItem.auditoriumItem auditorium=auditorium/>
        </#list>
    </#if>
</div>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="../../../resources/js/controller.js"></script>
</body>