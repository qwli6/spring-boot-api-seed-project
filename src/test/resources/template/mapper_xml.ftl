<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<!-- com.company.project.mapper.UserMapper-->
<mapper namespace="${packageName}.mapper.${className}Mapper">

    <resultMap id="BaseResultMap" type="${className}">
        <id column="id" jdbcType="INTEGER" property="id" />
        <result column="username" jdbcType="VARCHAR" property="username" />
        <result column="password" jdbcType="VARCHAR" property="password" />
        <result column="blog_id" jdbcType="INTEGER" property="blogId" />
    </resultMap>

    <!-- 基本字段-->
    <sql id="Base_Column_List">
        <#list columnList as key>
            <trim suffixOverrides=",">
                ${key},
            </trim>
        </#list>
    </sql>

    <!-- 字段值-->
    <sql id="fieldValue">
        <#--<#list propertyList as key>-->
            <#--<#if key_index == propertyList?size - 1>-->
                <#--${key}-->
                <#--<#else>-->
                <#--${key},-->
            <#--</#if>-->
        <#--</#list>-->
    </sql>


    <insert id="save${className}" parameterType="${className?lower_case}">
        insert into `${className?lower_case}`
        <trim prefix="(" suffixOverrides="," suffix=")">
            <!-- 列对象 -->
            <#list columnList as key>
                <if test="${key} != null">
                    ${key},
                </if>
            </#list>
        </trim>
        <trim prefix="values (" suffixOverrides="," suffix=")">
            <!-- 成员属性-->
            <#list fieldList as key>
                <if test="${key} != null">
                    ${key},
                </if>
            </#list>
        </trim>
    </insert>


    <!-- 删除对象-->
    <delete id="delete${className}ById" parameterType="int">
        delete from `${className?lower_case}`
        <where>
            <#--<#list propertyList as key>-->
                <#--<if test="${key[0]} != null and ${key[0]} != ''">-->
                    <#--and ${key} = ${r"#{"}${key}${r"}"}-->
                <#--</if>-->
            <#--</#list>-->
        </where>
    </delete>


    <!-- 修改 -->
    <update id="edit${className}ById" parameterType="${className}">
        update `${className?lower_case}`
        set

    </update>


    <!--通过 id 查找对象 -->
    <select id="find${className}ById" parameterType="" resultType="">
        select
        <include refid="field"/>
        from
        `${className?lower_case}`
        <where>
            and xx = ''
        </where>
    </select>

    <!-- 通过一个条件或者多个条件查询数据-->
    <select id="find${className}ByConditions" parameterType="" resultType="">
        select
        <include refid="field"/>
        from
        `${className?lower_case}`
        <where>
            <!-- 遍历字段集合-->
        </where>
    </select>

    <!-- 无条件查找数量-->
    <select id="countNoConditions" resultType="int">
        select count(1) from `${className?lower_case}`
    </select>

    <!-- 有条件查找数量-->
    <select id="countByConditions" resultType="int">
        select count(1) from `${className?lower_case}`
        <where>

        </where>
    </select>

    <!-- 列表-->
    <select id="find${className}ByPage" parameterType="page" resultType="">
        select
        <include refid="field"/>
        from
        `${className?lower_case}`
        <where>
            <if test="xx != null and xx != ''">
                and xx like contact('%', xxx, '%')
            </if>
        </where>
    </select>


    <!-- 列表（全部）-->
    <select id="findAll" resultType="${className?uncap_first}">
        select
        <include refid="field"/>
        from
        `${className?lower_case}`
    </select>


    <!--批量删除 -->
    <delete id="delBatch" parameterType="string">
        delete from
        `${className?lower_case}`
        where id in
        <foreach collection="array" item="item" index="index" open="(" separator="," close=")">
            xx
        </foreach>
    </delete>
</mapper>