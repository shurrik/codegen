<#assign corpName = conf.corpName>
<#assign projectName = conf.projectName>
<#assign moduleName = conf.moduleName>
<#assign className = classObject.className>
<#assign className_lower_case = classObject.className?lower_case>
<#assign className_uncap_first = classObject.className?uncap_first>
<#macro id>${r"#{id}"}</#macro>
<#macro proname name><#if name='createDate'||name='updateDate'>sysdate()<#else>${r"#{"}${name!}${r"}"}</#if></#macro>
<#macro map id>${r"#{map."}${id!}${r"}"}</#macro>
<#macro map name><#if name='createDate'||name='updateDate'>sysdate()<#else>${r"#{map."}${name!}${r"}"}</#if></#macro>
<#macro condition name>${r"$ {condition."}${name!}${r"}"}</#macro>
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper PUBLIC "-//ibatis.apache.org//DTD Mapper 3.0//EN" "http://ibatis.apache.org/dtd/ibatis-3-mapper.dtd">

<mapper namespace="com.${corpName}.${projectName}.dao.I${className}DAO">

    <sql id="Base_Column_List">
      id,
	 <#list classObject.columns as column>
	  ${column.name!} ${column.proName!}<#if column_has_next>,</#if>
	 </#list>
    </sql>
    
    <sql id="Query_condition">
      <trim prefix="WHERE" prefixOverrides="AND | OR">
            <#list classObject.columns as column>
              <if test="condition.${column.proName!}!=null">
			  	AND ${column.name!} Like '%${r"${condition."}${column.proName!}${r"}"}%'
			  </if>
			</#list>    
        </trim>
    </sql>
    
    <insert id="insert" parameterType="${className}">
        INSERT INTO ${(classObject.tableName)!} (
	        id,
		 	<#list classObject.columns as column>
		    ${column.name!}<#if column_has_next>,</#if>
			</#list>
        ) VALUES (
         	${r"#{id}"},
        <#list classObject.columns as column>
       		<@proname name = column.proName /><#if column_has_next>,</#if>
		</#list>
        )
    </insert>

    <!-- 更新 -->
   	<update id="update" parameterType="${className}">
        UPDATE ${(classObject.tableName)!}
        <trim prefix="SET" suffixOverrides=",">
          <#list classObject.columns as column>
              <#if column.proName!='createDate'>
                  <if test="${column.proName!}!=null">
                  ${column.name!}=<@proname name = column.proName />,
                  </if>
              </#if>
		  </#list>
        </trim>
        WHERE
        id =<@id/>
    </update>

   	<update id="updateMap">
        UPDATE ${(classObject.tableName)!}
        <trim prefix="SET" suffixOverrides=",">
          <#list classObject.columns as column>
          <if test="map.${column.proName!}!=null">        
           ${column.name!}=<@map name=column.proName />,
          </if>
		  </#list>     
        </trim>
        WHERE
       id = ${r"#{ map.id }"}
	</update>


     <insert id="insertMap" >
        INSERT INTO ${(classObject.tableName)!} (
             	id,
			<#list classObject.columns as column>
			 	${column.name!},
			</#list>
        ) VALUES (
              	${r"#{map.id}"},
           	<#list classObject.columns as column>
		    	<@map name= column.proName />,
		    </#list>
        )
    </insert>


  	<update id="updateNull" parameterType="${className}">
        UPDATE ${(classObject.tableName)!}
        <trim prefix="SET" suffixOverrides=","> 
            	<#list classObject.columns as column>
			    ${column.name!}=<@proname name= column.proName />,
			    </#list>
        </trim>
        WHERE
        id =<@id/>
    </update>

   	<delete id="deleteById" parameterType="java.lang.Integer">
        DELETE FROM ${(classObject.tableName)!}
        <trim prefix="WHERE" prefixOverrides="AND | OR">
            AND id=<@id/>
        </trim>
    </delete>


 	<delete id="deleteByCondition" parameterType="java.util.Map">
        DELETE FROM  ${(classObject.tableName)!} 
        <trim prefix="WHERE" prefixOverrides="AND | OR">
            <if test="map.id!=null">
                AND id = ${r"#{map.id}"}
            </if>				        
        <#list classObject.columns as column>
			<if test="map.${column.proName!}!=null">
				AND ${column.name!} = <@map name=column.proName />
			</if>				
		</#list> 
        </trim>
    </delete>

    <delete id="deleteByProperty" parameterType="java.util.Map">
        DELETE FROM ${(classObject.tableName)!} WHERE
        ${r"${property}"}=${r"#{value}"}
    </delete>

    <select id="fetch" parameterType="java.lang.Integer" resultType="${className}">
        SELECT
        <include refid="Base_Column_List" />
        FROM ${(classObject.tableName)!}
        <trim prefix="WHERE" prefixOverrides="AND | OR">
            AND id =<@id/>
        </trim>
    </select>

    <select id="findOne" parameterType="java.util.Map" resultType="${className}">
        SELECT
        <include refid="Base_Column_List" />
        FROM ${(classObject.tableName)!} WHERE
        ${r"${property}"}=${r"#{value}"} 
        LIMIT 0,1
    </select>

    <select id="findList" parameterType="java.util.Map" resultType="${className}">
        SELECT
        <include refid="Base_Column_List" />
        FROM ${(classObject.tableName)!} WHERE
       	${r"${property}"} =${r"#{value}"}
    </select>

    <select id="findAll" resultType="${className}">
        SELECT
        <include refid="Base_Column_List" />
        FROM ${(classObject.tableName)!}
    </select>

    <select id="queryPage" resultType="${className}">
        SELECT
        <include refid="Base_Column_List" />
        FROM ${(classObject.tableName)!}
        <include refid="Query_condition" />    
        <if test="condition.sort != null">
			order by ${r"${condition.sort}"}
		</if>
        <if test="offset != null">
            limit ${r"${offset}"}, ${r"${rows}"}
        </if>
    </select>

    <select id="like" parameterType="java.util.Map" resultType="${className}">
        SELECT
        <include refid="Base_Column_List" />
        FROM ${(classObject.tableName)!}
        <trim prefix="WHERE" prefixOverrides="AND | OR">
			<if test="id!=null">
				AND id like '%${r"#{id}"}%'
			</if>
          	<#list classObject.columns as column>
			<if test="${column.proName!}!=null">
				AND ${column.name!} Like '%<@condition name= column.proName />%'
			</if>
			</#list> 
        </trim>
    </select>


 	<select id="queryList" parameterType="java.util.Map" resultType="${className}">
        SELECT
        <include refid="Base_Column_List" />
        FROM ${(classObject.tableName)!}
        <trim prefix="WHERE" prefixOverrides="AND | OR">
            <if test="map.id!=null">
                AND id = ${r"#{map.id}"}
            </if>
		<#list classObject.columns as column>
			<if test="map.${column.proName!}!=null">
				AND ${column.name!} = <@map name=column.proName />
			</if>
		</#list> 
        </trim>
        ORDER BY ${r"${orderBy}"} ${r"${sortBy}"}
    </select>

   	<select id="queryOne" parameterType="java.util.Map" resultType="${className}">
        SELECT
        <include refid="Base_Column_List" />
        FROM ${(classObject.tableName)!}
        <trim prefix="WHERE" prefixOverrides="AND | OR">
            <if test="map.id!=null">
                AND id = ${r"#{map.id}"}
            </if>
		<#list classObject.columns as column>
			<if test="map.${column.proName!}!=null">
				AND ${column.name!} = <@map name=column.proName />
			</if>
		</#list> 
        </trim>
        limit 0,1
    </select>

  	<select id="count" resultType="java.lang.Integer">
        SELECT count(id) FROM ${(classObject.tableName)!}
        <include refid="Query_condition" />
    </select>

	<select id="selectMaxId" resultType="java.lang.Integer">
	    SELECT
	    MAX(id)
	    FROM ${(classObject.tableName)!}
	</select>

</mapper>

