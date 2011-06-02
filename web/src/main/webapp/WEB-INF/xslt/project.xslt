<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">

	<xsl:output method="html" omit-xml-declaration="yes"/>

	<xsl:template match="/">
		<html xmlns="http://www.w3.org/1999/xhtml">
			<body>
			  <h3><xsl:value-of select="accounts/account[1]/project" /> project</h3>
			  	<b>Accounts:</b>
			    <xsl:apply-templates />
			    <a href="/accountsweb/accounts/home.html">Back to projects</a> 
			</body>
		</html>
	</xsl:template>
	
	<xsl:template match="account">
		<p>
			<b>Entry name: <xsl:value-of select="entryname" /></b><br/>
			Url: <xsl:value-of select="url" /><br/>
			Username: <xsl:value-of select="username" /><br/>
			Password: <xsl:value-of select="password" /><br/>
			Tag: <xsl:value-of select="tag" />
		</p>			
	</xsl:template>
</xsl:stylesheet>
