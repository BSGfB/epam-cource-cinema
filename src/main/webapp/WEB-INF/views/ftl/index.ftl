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
<#import "./ticket/ticketItem.ftl" as ticketItem>
<#import "./events/eventItem.ftl" as eventItem>
<#import "./auditorium/auditoriumItem.ftl" as auditoriumItem>

<#include "header.ftl">

<div class="container">
    <#if user??><@userItem.userItem user=user/></#if>

    <#if users??>
        <#list users as user>
            <@userItem.userItem user=user/>
        </#list>
    </#if>


    <#if ticket??><@ticketItem.ticketItem ticket=ticket/></#if>

    <#if tickets??>
        <#list tickets as ticket>
            <@ticketItem.ticketItem ticket=ticket/>
        </#list>
    </#if>


    <#if event??><@eventItem.eventItem event=event/></#if>

    <#if events??>
        <#list events as event>
            <@eventItem.eventItem event=event/>
        </#list>
    </#if>


    <#if auditorium??><@auditoriumItem.auditoriumItem auditorium=auditorium/></#if>

    <#if auditoriums??>
        <#list auditoriums as auditorium>
            <@auditoriumItem.auditoriumItem auditorium=auditorium/>
        </#list>
    </#if>
</div>

</body>