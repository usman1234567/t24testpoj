# Set file name
$File = 'D:\Temenos\R20PKMB\jboss\modules\com\temenos\t24\main\module.xml'

# Process lines of text from file and assign result to $NewContent variable
$NewContent = Get-Content -Path $File |
    ForEach-Object {
        # Output the existing line to pipeline in any case
        $_

        # If line matches regex
        if($_ -match ('<resource-root path="./Javalib/PKMB_INWD.jar" />'))
        {
            # Add output additional line
            '    <resource-root path="./jenkinsjar/T24-Test-Project.jar" />'
        }
    }

# Write content of $NewContent varibale back to file
$NewContent | Out-File -FilePath $File -Encoding Default -Force