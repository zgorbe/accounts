<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">

	<xsl:output method="html" omit-xml-declaration="yes"/>

	<xsl:template match="/">
		<html xmlns="http://www.w3.org/1999/xhtml">
			<body>
			  <h3>Projects</h3>
			  <p>
				<ul>
				  <xsl:for-each select="projects/project">
				    <li>
				      <a>
				        <xsl:attribute name="href">
				  	      <xsl:value-of select="concat('projects/',.,'.html')" />
				  	    </xsl:attribute>
				  	    <xsl:value-of select="." />
				  	  </a>
				  	</li>
				  </xsl:for-each>
				</ul>
			  </p>
			  <p>
                <a href="/accountsweb/accounts/new.html">Create new account</a>
			  </p>
			</body>
		</html>
	</xsl:template>
</xsl:stylesheet>
