param([switch]$CompileOnly)
$ErrorActionPreference = 'Stop'
Push-Location $PSScriptRoot
try {
    if (-not (Get-Command javac -ErrorAction SilentlyContinue)) {
        throw 'JDK 17 or newer is required. Make sure javac is available in PATH.'
    }
    New-Item -ItemType Directory -Path 'out' -Force | Out-Null
    $sources = @(Get-ChildItem -LiteralPath $PSScriptRoot -Filter '*.java' -File -Recurse |
        Where-Object { $_.FullName -notmatch '[\\/](test|out|test-out)[\\/]' } |
        ForEach-Object { $_.FullName })
    if ($sources.Count -eq 0) { throw 'No Java source files found.' }
    & javac --release 17 -d out $sources
    if ($LASTEXITCODE -ne 0) { throw 'Compilation failed. Read the Java error above.' }
    if (-not $CompileOnly) {
        & java -cp out service.Lab10
        if ($LASTEXITCODE -ne 0) { throw 'Java stopped with an error.' }
    }
} finally { Pop-Location }
