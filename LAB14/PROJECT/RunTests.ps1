$ErrorActionPreference = 'Stop'
Push-Location $PSScriptRoot
try {
    & (Join-Path $PSScriptRoot 'Run.ps1') -CompileOnly
    New-Item -ItemType Directory -Path 'test-out' -Force | Out-Null
    & javac --release 17 -cp 'out;lib/junit.jar' -d test-out test/BankServiceTest.java
    if ($LASTEXITCODE -ne 0) { throw 'Test compilation failed.' }
    & java -jar lib/junit.jar execute --class-path 'out;test-out' --scan-class-path
    if ($LASTEXITCODE -ne 0) { throw 'One or more tests failed.' }
} finally { Pop-Location }
