; -- 64Bit.iss --
; Demonstrates installation of a program built for the x64 (a.k.a. AMD64)
; architecture.
; To successfully run this installation and the program it installs,
; you must have a "x64" edition of Windows.

; SEE THE DOCUMENTATION FOR DETAILS ON CREATING .ISS SCRIPT FILES!

[Setup]
AppName=Transoft
AppVersion=1.0
DefaultDirName={pf}\Transoft
DefaultGroupName=Transoft
UninstallDisplayIcon={app}\UninstallImpressoraTransoft.exe
Compression=lzma2
SolidCompression=yes
OutputDir=userdocs:Inno Setup Examples Output
; "ArchitecturesAllowed=x64" specifies that Setup cannot run on
; anything but x64.
ArchitecturesAllowed=x64
; "ArchitecturesInstallIn64BitMode=x64" requests that the install be
; done in "64-bit mode" on x64, meaning it should use the native
; 64-bit Program Files directory and the 64-bit view of the registry.
ArchitecturesInstallIn64BitMode=x64

[Files]
Source: "Impressora.exe"; DestDir: "{app}"; DestName: "ImpressoraTransoft.exe"
Source: "JRE\*"; DestDir: "C:\Program Files\Transoft\JRE"; Flags: recursesubdirs createallsubdirs

[Icons]
Name: "{group}\Transoft"; Filename: "{app}\ImpressoraTransoft.exe"
