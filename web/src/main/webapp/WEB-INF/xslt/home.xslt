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
				      <xsl:choose>
        			    <xsl:when test=". = 'ONE'">
        			      <a href="projects/ONE.html">ONE</a>  
        			    </xsl:when>
        			    <xsl:when test=". = 'TWO'">
        			      <a href="projects/TWO.html">TWO</a>
        			    </xsl:when>
        			    <xsl:when test=". = 'THREE'">
        			      <a href="projects/THREE.html">THREE</a>
        			    </xsl:when>
        			    <xsl:otherwise />
        			  </xsl:choose>	
				    </li>
				  </xsl:for-each>
				</ul>
			  </p>
			</body>
		</html>
	</xsl:template>
</xsl:stylesheet>
