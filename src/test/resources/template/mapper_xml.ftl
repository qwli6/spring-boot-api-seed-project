<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="${packageName}.mapper.${className}Mapper">

    <resultMap id="Base_Result_Map" type="${packageName}.model.${className}">
        <#list dataMap as dataMap>
        <#if dataMap_index == 0>
            <id column="${dataMap.columnName}" property="${dataMap.columnJavaName}"/>
        <#else>
            <result column="${dataMap.columnName}" property="${dataMap.columnJavaName}"/>
        </#if>
        </#list>
    </resultMap>

    <!-- 基本字段-->
    <sql id="Base_Column_List">
        <#list dataMap as property>
            <trim suffixOverrides=",">
                ${property.columnName},
            </trim>
        </#list>
    </sql>

    <sql id="tableName">
        `${className?lower_case}`
    </sql>

    <!-- 字段值-->
    <sql id="Base_Column_Value">
        <#list dataMap as value>
            <if test="${value.columnJavaName} != null and ${value.columnJavaName} != ''">
                <trim suffixOverrides=",">
                    ${r"#{"}${value.columnJavaName}${r"}"},
                </trim>
            </if>
        </#list>
    </sql>


    <!-- 保存 -->
    <insert id="save${className}" parameterType="${packageName}.model.${className?lower_case}">
        insert into
        <include refid="tableName"/>
        <trim prefix="(" suffixOverrides="," suffix=")">
            <!-- 列对象 -->
            <#list dataMap as property>
                <if test="${property.columnJavaName} != null and ${property.columnJavaName} != ''">
                    ${property.columnName},
                </if>
            </#list>
        </trim>
        <trim prefix="values (" suffixOverrides="," suffix=")">
            <!-- 成员属性-->
            <#list dataMap as value>
                <if test="${value.columnJavaName} != null and ${value.columnJavaName} != ''">
                    ${value.columnJavaName},
                </if>
            </#list>
        </trim>
    </insert>

    <!-- 查找全部-->
    <select id="find${className}List" resultMap="Base_Result_Map">
        select
            <include refid="Base_Column_List"/>
        from
            <include refid="tableName"/>
    </select>

    <!-- 获取数量 -->
    <select id="selectCount" resultType="int">
        select count(1) from <include refid="tableName"/>
    </select>

<#list dataMap as key>
    <#if key_index == 0>
        <!-- 删除 -->
        <delete id="delete${className}By${key.columnJavaName?cap_first}" parameterType="${key.columnType}">
        delete from
        <include refid="tableName"/>
        <where>
            <if test="${key.columnJavaName} != null and ${key.columnJavaName} != ''">
                and ${key.columnName} = ${r"#{"}${key.columnJavaName}${r"}"}
            </if>
        </where>
    </delete>

    <!--批量删除 -->
    <delete id="deleteBatch" parameterType="${key.columnType}">
        delete from
        <include refid="tableName"/>
        <where>
            ${key.columnJavaName} in
            <foreach collection="array" item="item" index="index" open="(" separator="," close=")">
                ${r"#{"}item${r"}"}
            </foreach>
        </where>
    </delete>

    <!-- 根据主键查询-->
    <select id="find${className}By${key.columnJavaName?cap_first}" parameterType="${key.columnType}" resultMap="Base_Result_Map">
        select
        <include refid="Base_Column_List"/>
        from
        <include refid="tableName"/>
        <where>
            <if test="${key.columnJavaName} != null and ${key.columnJavaName} != ''">
                and ${key.columnName} = ${r"#{"}${key.columnJavaName}${r"}"}
            </if>
        </where>
    </select>
    </#if>
</#list>

    <!-- 修改 -->
    <update id="edit${className}" parameterType="${packageName}.model.${className}">
        update
        <include refid="tableName"/>
        set
        <#list dataMap as key>
            <#if key_index != 0>
            <if test="${key.columnJavaName} != null and ${key.columnJavaName} != ''">
            ${key.columnName} = ${r"#{"}${key.columnJavaName}${r"}"}
            </if>
            </#if>
        </#list>
        <where>
        <#list dataMap as key>
            <#if key_index == 0>
            <if test="${key.columnJavaName} != null and ${key.columnJavaName} != ''">
                and ${key.columnName} = ${r"#{"}${key.columnJavaName}${r"}"}
            </if>
            </#if>
        </#list>
        </where>
    </update>
</mapper>