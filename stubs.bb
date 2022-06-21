;; Use this command to return the absolute value of a number; meaning its positive  value. A negative 3 would become a positive 3. If what you want is a number  without a fraction (say, convert 3.1415 into 3) use the Int() command.
;;param number = any valid number or numeric variable
function Abs (number)
number=-3  
Print "The absolute value of " + number + " is: " + Abs(number)  
WaitKey()  
end function
;; Accepts an incoming TCP/IP stream, and returns a TCP/IP stream if one is  available, or 0 if not.
;; See CreateTCPServer and CloseTCPServer.
;;param serverhandle = the server handle assigned when the server was created
function AcceptTCPStream (serverhandle)
; CreateTCPServer, CloseTCPServer, AcceptTCPStream Example  
; This code is in two parts, and needs to be run seperately on the same machine  
; --- Start first code set ---  
; Create a server and listen for push  
svrGame=CreateTCPServer(8080)  
If svrGame<>0 Then  
Print "Server started successfully."  
Else  
Print "Server failed to start."  
End  
End If  
While Not KeyHit(1)  
strStream=AcceptTCPStream(svrGame)  
If strStream Then  
Print ReadString$(strStream)  
Delay 2000  
End  
Else  
Print "No word from Apollo X yet ..."  
Delay 1000  
End If  
Wend  
End  
; --- End first code set ---  
; --- Start second code set ---  
; Copy this code to another instance of Blitz Basic  
; Run the above code first, then run this ... they will 'talk'  
; Create a Client and push data  
strmGame=OpenTCPStream("127.0.0.1",8080)  
If strmGame<>0 Then  
Print "Client Connected successfully."  
Else  
Print "Server failed to connect."  
WaitKey  
End  
End If  
; write stream to server  
WriteString strmGame,"Mission Control, this is Apollo X ..."  
Print "Completed sending message to Mission control..."  
; --- End second code set ---  
end function
;; ACos( c ) is an angle which has cosine = c.
;; It is in the range 0 to +180 degrees.
;;param c = a number in the range -1 to +1.
;;param It is considered to be the cosine of an angle.
function ACos# ( c# )
; ACos example  
; NaN means "Not a Number", the numerical result is invalid.  
Const width = 640, height = 480  
Graphics width, height  
Local C#, AC#   ; Cos and ACos  
Print "    C    ACos( C )"  
Print "=================="  
For n = -11 To 11  
C = n / 10.0  
AC = ACos( C )  
Print RSet(C, 6) + RSet( AC, 10)  
Next  
WaitKey()  :  End  
end function
;; If you haven't read up on the TYPE command, you might  want to do so before continuing.
;; Use this to assign a custom Type object to the next object in the collection.  See the example.
;; See also: <a class=small href=Type.htm>Type</a>, <a class=small href=New.htm>New</a>, <a class=small href=Before.htm>Before</a>, <a class=small href=First.htm>First</a>, <a class=small href=Last.htm>Last</a>, <a class=small href=Each.htm>Each</a>, <a class=small href=Insert.htm>Insert</a>, <a class=small href=Delete.htm>Delete</a>.
;;param custom_type_variable = not the Type name, but the custom Type name
function After custom_type_variable
; Define a crafts Type  
Type crafts  
Field x  
Field y  
Field dead  
Field graphic  
End Type  
; Create 100 crafts, with the unique name of alien  
For t = 1 To 100  
alien.crafts = New crafts  
alien\x = Rnd(0,640)  
alien\y = Rnd(0,480)  
alien\dead = 0  
alien\graphic = 1  
Next  
; Move to the first object  
alien.crafts = First crafts  
Print alien\x  
Print alien\y  
Print alien\dead  
Print alien\graphic  
; move to the next alien object  
alien = After alien  
Print alien\x  
Print alien\y  
Print alien\dead  
Print alien\graphic  
; move to the last alien object  
alien.crafts = Last crafts  
Print alien\x  
Print alien\y  
Print alien\dead  
Print alien\graphic  
; move to the second to the last alien object  
alien = Before alien  
Print alien\x  
Print alien\y  
Print alien\dead  
Print alien\graphic  
end function
;; AND is a logical operator for doing conditional checks of multiple values  and/or expressions. Use this to ensure that two or more conditions are true,  usually in an IF ... THEN conditional.  See example and see OR, NOT, and XOR.
;; See also: <a class=small href=Or.htm>Or</a>, <a class=small href=Not.htm>Not</a>, <a class=small href=Xor.htm>Xor</a>.
;;param None.
function And
; AND example  
name$=Input$("Enter your name:")  
pw$=Input$("Password:")  
if name$="Shane" and pw$="bluedog" then  
print "Access granted! Welcome!"  
else  
print "Your name or password was not recognized"  
end if  
end function
;; Allows you to set the text of the program's title bar, and 'close program?'  message box.
;;param title$ - the text that will be displayed in the title bar of the program  window
;;param close_prompt$ (optional) - the text that will be displayed in a message box  with 'OK/Cancel' options when a user clicks on the close button. If nothing  is specified, the message box will not be displayed and the program will close  immediately.
function AppTitle title$[,close_prompt$]
; Set the title bar  
AppTitle "Super Invaders V1.0", "Are you sure?"  
end function
;; This will return the ASCII value of the first letter of a string.
;;param string$ = any valid string variable (only the first character's ASCII value  will be returned).
function Asc (string$)
a$=Input$("Enter a letter:")  
Print "The ASCII value of the letter is:" + Asc(a$)  
end function
;; ASin( s ) is an angle which has sine = s.
;; It is in the range -90 to +90 degrees.
;;param s = a number in the range -1 to +1
;;param It is considered to be the sine of an angle.
function ASin# ( s# )
; ASin example  
; NaN means "Not a Number", the numerical result is invalid.  
Const width = 640, height = 480  
Graphics width, height  
Local S#, AS#   ; Sin and ASin  
Print "    S    ASin( S )"  
Print "=================="  
For n = -11 To 11  
S = n / 10.0  
AS = ASin( S )  
Print RSet(S, 6) + RSet( AS, 10)  
Next  
WaitKey()  :  End  
end function
;; ATan( t ) is an angle which has tangent = t.
;; It is in the range -90 to +90 degrees.
;; See also ATan2 for an extended version with 360 degree range.
;;param t = any number.
;;param It is considered to be the tangent of an angle.
function ATan# ( t# )
; ATan example  
Const width = 640, height = 480  
Graphics width, height  
Local T#, AT#   ; Tan and ATan  
zero# = 0  
Print "       T      ATan( T )"  
Print "======================="  
; First, an extreme case...  
T# = 1 / zero    ; +Infinity  
AT = ATan( T )  
Print RSet(T, 10) + RSet( AT, 11)  
; Now, back to normal usage...  
For n = -10 To 10  
T = Sgn( n ) * n * n  
AT = ATan( T )  
Print RSet(T, 10) + RSet( AT, 11)  
Next  
; Finally, another extreme case.  
T# = -1 / zero    ; -Infinity  
AT = ATan( T )  
Print RSet(T, 10) + RSet( AT, 11)  
WaitKey()  :  End  
end function
;; ATan2 gives the angle between the positive x-axis and a vector from the point (0,0) to the point (x,y).
;; One common use is in 2d graphics. Suppose you have two objects and you want to aim the first at the second.
;; ATan2( y2 - y1, x2 - x1 ) gives the proper orientation for object1.
;; You can use this angle to select an appropriately rotated image.
;; Notice the reverse order, ATan2( y, x ) rather than ATan2( x, y).
;; ATan2( y, x ) is analogous to ATan( y / x), but covers 360 degrees.
;; The angle satisfies:  -180 < ATan2 <= +180
;;param y, x are any numbers.
;;param They are interpreted as corresponding to a point ( x, y ).
function ATan2# ( y#, x# )
; ATan2 example.  
; Move mouse. Escape quits.  
Const width = 640, height = 480  
Const radius# = .2 * height  
Const KEY_ESC = 1  
Graphics width, height  
SetBuffer BackBuffer( )  
Origin width / 2, height / 2  
HidePointer  
MoveMouse .75 * width, height / 2  
While Not KeyDown( KEY_ESC )  
Cls  
Color 255, 255, 0  
Line 0, 0, width / 2, 0   ; positive x-axis  
x = MouseX() -  width / 2  
y = MouseY() - height / 2  
Oval x - 3, y - 3, 7, 7, True  
Line 0, 0, x, y  
Text .35 * width, -80, "x = " + x  
Text .35 * width, -60, "y = " + y  
Text .35 * width - 96, -40, "ATan2( y, x ) = " + ATan2( y, x )  
Flip  
Wend  
End  
end function

end function
;; When an image is loaded with LoadImage, the  image handle (the location within the image where the image is 'drawn from')  is always defaulted to the top left corner (coordinates 0,0). This means if  you draw an image that is 50x50 pixels at screen location 200,200, the image  will begin to be drawn at 200,200 and extend to 250,250.
;; The MidHandle command moves the image's handle to  the middle of the image. See this command for more information about the image's  handle.
;; This command eliminates the need for the MidHandle  command by making ALL subsequently loaded images default to having their image  handles set to mid.
;; Note about the term 'handle'. There are two types of 'handles' we discuss in  these documents. One is the location within an image - as discussed in this  command. The other is a 'file handle', a variable used to hold an image, sound,  or font loaded with a command. See LoadImage for  more information about file handles.
;;param true = images load with midhandle set as default
;;param false = images load with default image handles at 0,0
function AutoMidHandle true/false
; MidHandle/ImageXHandle()/ImageYHandle()/AutoMidHandle  
; Initiate Graphics Mode  
Graphics 640,480,16  
; Set up the image file handle as a global  
Global gfxBall  
; Load the image - you may need to change the location of the file  
gfxBall=LoadImage ("C:Program FilesBlitz Basicsamplesall.bmp")  
; Until the user presses ESC key ...  
While Not KeyHit(1)  
Text 0,0,"Default Image Handle for gfxBall... Press ESC ..."  
Text 0,14,"X handle-" + ImageXHandle(gfxBall) ; Print the location of the image  handle x location  
Text 0,28,"Y handle-" + ImageYHandle(gfxBall) ; Print the location of the image  handle y location  
DrawImage gfxBall,200,200,0 ; draw the image at 200,200  
Wend  
; Clear the screen  
Cls  
; Set the ball's handle to the center of the image  
MidHandle gfxBall  
; Until the user presses ESC key ... show the new information  
While Not KeyHit(1)  
Text 0,0,"New Image Handle for gfxBall... Press ESC ..."  
Text 0,14,"X handle-" + ImageXHandle(gfxBall)  
Text 0,28,"Y handle-" + ImageYHandle(gfxBall)  
DrawImage gfxBall,200,200,0  
Wend  
; Makes all images load up with their handles in the center of the image  
AutoMidHandle True  
Cls  
; Load the image again  
gfxBall=LoadImage ("C:Program FilesBlitz Basicsamplesall.bmp")  
; Until the user presses ESC key ... show the new information  
While Not KeyHit(1)  
Text 0,0,"Automatic image handle of gfxBall... Press ESC ..."  
Text 0,14,"X handle-" + ImageXHandle(gfxBall)  
Text 0,28,"Y handle-" + ImageYHandle(gfxBall)  
DrawImage gfxBall,200,200,0  
Wend  
end function
;; This command will return the total bytes of available free video memory.  Use this to keep track of your resources!
;;param None.
function AvailVidMem()
Print "Your available video memory is: " + AvailVidMem()  
end function
;; This is a value usually used with SETBUFFER  to denote the secondary non-visible drawing buffer called the Back Buffer. In  MOST gaming situations, you will want to be using the BackBuffer() for drawing  operations then using Flip to bring that buffer to the FrontBuffer() where it can be seen. There are  other uses for the command, but this is the biggie. See SETBUFFER for more info, and check out the example.  Once again - if you set drawing operations to the BackBuffer() you will NOT  see any of them until you call FLIP.
;;param None.
function BackBuffer()
; Flip/Backbuffer()/Rect Example  
; Set Graphics Mode  
Graphics 640,480  
; Go double buffering  
SetBuffer BackBuffer()  
; Setup initial locations for the box  
box_x = -20 ; negative so it will start OFF screen  
box_y = 100  
While Not KeyHit(1)  
Cls ; Always clear screen first  
Rect box_x,box_y,20,20,1 ; Draw the box in the current x,y location  
Flip ; Flip it into view  
box_x = box_x + 1 ; Move the box over one pixel  
If box_x = 640 Then box_x=-20 ; If it leaves the Right edge, reset its x location  
Wend  
end function
;; Use this command to determine the size of an existing bank.
;; See also: <a class=small href=CreateBank.htm>CreateBank</a>, <a class=small href=ResizeBank.htm>ResizeBank</a>, <a class=small href=CopyBank.htm>CopyBank</a>.
;;param bankhandle - handle assigned to the bank when created.
function BankSize (bankhandle)
; BankSize, ResizeBank, CopyBank Example  
; create a bank  
bnkTest=CreateBank(5000)  
; Fill it with rand Integers  
For t = 0 To 4999  
PokeByte bnkTest,t,Rand(9)  
Next  
; Resize the bank  
ResizeBank bnkTest,10000  
; Copy the first half of the bank to the second half  
CopyBank bnkTest,0,bnkTest,5000,5000  
; Print final banksize  
Print BankSize(bnkTest)  
end function
;; If you haven't read up on the TYPE command, you might  want to do so before continuing.
;; Use this to assign a custom Type object to the previous object in the collection.  See the example.
;; See also: <a class=small href=Type.htm>Type</a>, <a class=small href=New.htm>New</a>, <a class=small href=After.htm>After</a>, <a class=small href=First.htm>First</a>, <a class=small href=Last.htm>Last</a>, <a class=small href=Each.htm>Each</a>, <a class=small href=Insert.htm>Insert</a>, <a class=small href=Delete.htm>Delete</a>.
;;param custom_type_variable = not the Type name, but the custom Type name
function Before custom_type_variable
; Define a crafts Type  
Type crafts  
Field x  
Field y  
Field dead  
Field graphic  
End Type  
; Create 100 crafts, with the unique name of alien  
For t = 1 To 100  
alien.crafts = New crafts  
alien\x = Rnd(0,640)  
alien\y = Rnd(0,480)  
alien\dead = 0  
alien\graphic = 1  
Next  
; Move to the first object  
alien.crafts = First crafts  
Print alien\x  
Print alien\y  
Print alien\dead  
Print alien\graphic  
; move to the next alien object  
alien = After alien  
Print alien\x  
Print alien\y  
Print alien\dead  
Print alien\graphic  
; move to the last alien object  
alien.crafts = Last crafts  
Print alien\x  
Print alien\y  
Print alien\dead  
Print alien\graphic  
; move to the second to the last alien object  
alien = Before alien  
Print alien\x  
Print alien\y  
Print alien\dead  
Print alien\graphic  
end function
;; Converts integer values into binary values. If you don't know what binary  is, you don't need to know this command :)
;;param integer = any valid integer or integer variable
function Bin$ (integer)
intValue="64738"  
Print "The binary value of "+intValue+" is: " + bin$(intValue)  
end function
;; The DLL is called with pointers to and sizes of bank memory. Dll function prototypes  should like something like this (Visual C++) example:
;; extern "C"{
;; _declspec(dllexport) int _cdecl my_dll_func( const void *in,int in_size,void  *out,int out_sz );
;; }
;; The 'extern "C"' bit prevents C++ 'name-mangling', and the _cdecl bit prevents  name decoration. You could call this function using something like:
;; in_bank=CreateBank(...)
;; out_bank=CreateBank(...)
;; ;poke input parameters into in_bank
;; result=CallDLL( "mydll","my_dll_func",bank1,bank2 )
;; ;peek output results from out_bank
;; Click <a href=http://www.blitzbasic.co.nz/b3ddocs/command.php?name=CallDLL&ref=comments target=_blank>here</a> to view the latest version of this page online</body>
;; </html>
;;param dll_name$ - name of dll
;;param proc_name$ - name of procedure
;;param in_bank (optional) - handle of bank that is made available from Blitz to DLL  procedure
;;param out_bank (optional) - handle of bank that is made available from DLL procedure  to Blitz
function CallDLL( dll_name$, proc_name$[,in_bank,out_bank] )
end function
;; When using a SELECT structure, the CASE command defines the starting point of command execution if the SELECT value matches  the CASE value. If the SELECT value doesn't match the CASE value, the commands following it are ignored until the next CASE, DEFAULT, or END SELECT command is encountered. See SELECT and the example for a better understanding.
;; If multiple values should result in the same outcome, a single CASE statement can be used with each value separated by commas.
;; If you wish to use ranges for a case statement, or if you wish to test more than one variable, you will need to use a programming trick illustrated in the example below.  This involves using SELECT TRUE initially, and then in each Case statement specify a logical expression such as A > 1 AND A < 4.
;; See also: <a class=small href=Select.htm>Select</a>, <a class=small href=Default.htm>Default</a>, <a class=small href=End Select.htm>End Select</a>.
;;param value = any valid value of the SELECT variable
function Case value [,value [,value ... ] ]
; Advanced SELECT/CASE/DEFAULT/END SELECT Example  
; Assign a random number 1-10  
mission=Rnd(1,10)  
; Start the selection process based on the value of 'mission' variable  
Select True  
; Is mission = 1?  
Case mission=1  
Print "Your mission is to get the plutonium and get out alive!"  
; Is mission = 2?  
Case mission=2  
Print "Your mission is to destroy all enemies!"  
; Is mission = 3 to 5  
Case mission>=3 And mission<=5  
Print "Your mission is to steal the enemy building plans!"  
; What do do if none of the cases match the value of mission  
Default  
Print "Missions 6-10 are not available yet!"  
; End the selection process  
End Select  
end function
;; As with Floor there is often some confusion with negative numbers:
;; Ceil(  1.75 ) ...... 2.0
;; Ceil( -1.75 ) ... -1.0
;; See also Floor and Int for other types of rounding.
;;param y = any number
function Ceil# ( y# )
; Ceil / Floor / Int example, three kinds of rounding.  
; Move mouse. Escape quits.  
Graphics 640, 480  
Const KEY_ESC = 1  
SetBuffer BackBuffer()  
Origin 320, 240  
MoveMouse 320, 240  :  HidePointer  
While Not KeyDown( KEY_ESC )  
Cls  
my = MouseY() - 240  
Color 100, 100, 0  
Line -320, my, 319, my  
DrawNumberLine  
y# = Float( -my ) / 32  
Text 100, 50, "          y = "  + y  
Text 100, 70, "  Ceil( y ) = "  + Ceil( y )  
Text 100, 90, " Floor( y ) = "  + Floor( y )  
Text 100, 110, "   Int( y ) = " + Int( y )  
Flip  
Wend  
End  
Function DrawNumberLine( )  ; vertical line with numeric labels  
Color 255, 255, 255  
Line 0, -240, 0, 239  
For n = -7 To 7  
yn = -32 * n  
Line -2, yn, 2, yn  
Text -30, yn - 6, RSet( n, 2 )  
Next  
End Function  
end function
;; This command will change the currently selected directory for disk operations,  useful for advanced file operations. Use CURRENTDIR$()  to see what the current directory is.
;; Use a directory/path of ".." to change to the parent of the current directory,  unless you are at the root directory of the drive, then no change happens.
;;param directory/path = full path to directory/folder
function ChangeDir directory/path
; ChangeDir example  
ChangeDir "c:winntsystem32"  
Print "The folder has been changed to: " + currentdir$()  
end function
;; When you want to do real sound panning effects, this is the command you'll  use. This will allow you to pan the sound channels on a 'per channel' basis  between the left and right speakers. This command makes it very easy to produce  some really killer stereophonic effects!
;; The pan value is between -1 and 1 with 0 being perfect center. -1 is full left,  and 1 is full right. To make it somewhere in between, try -.5 for 50% left or  .75 for 75% right.
;;param channel_handle = variable assigned to the channel when played
;;param pan# = panning floating value to denote channel playback
function ChannelPan channel_handle, pan#
; Channel examples  
Print "Loading sound ..."  
; Load the sample - you'll need to point this to a sound on your computer  
; For best results, make it about 5-10 seconds...  
sndWave=LoadSound("level1.wav")  
; Prepare the sound for looping  
LoopSound sndWave  
chnWave=PlaySound(sndWave)  
Print "Playing sound for 2 seconds ..."  
Delay 2000  
Print "Pausing sound for 2 seconds ..."  
PauseChannel chnWave  
Delay 2000  
Print "Restarting sound ..."  
ResumeChannel chnWave  
Delay 2000  
Print "Changing Pitch of sound ..."  
ChannelPitch chnWave, 22000  
Delay 2000  
Print "Playing new pitched sound ..."  
Delay 2000  
Print "Left speaker only"  
ChannelPan chnWave,-1  
Delay 2000  
Print "Right speaker only"  
ChannelPan chnWave,1  
Delay 2000  
Print "All done!"  
StopChannel chnWave  
end function
;; You can alter the pitch of a sound channel that is playing (or in use and just paused). I'm sure you can think of numerous uses for this command!  Use the frequency of your sound as the 'baseline' for pitch change. So if your  sample is at 11025 hertz, increase the pitch to 22050 to make the pitch twice as high, 8000 to make it lower, etc. While similar to SoundPitch,  this command let's you change the pitch individually of each and every channel  in use.
;;param channel_handle = variable assigned to the channel when played
;;param hertz = pitch to apply to the channel (try 8000-44000)
function ChannelPitch channel_handle, hertz
; Channel examples  
Print "Loading sound ..."  
; Load the sample - you'll need to point this to a sound on your computer  
; For best results, make it about 5-10 seconds...  
sndWave=LoadSound("level1.wav")  
; Prepare the sound for looping  
LoopSound sndWave  
chnWave=PlaySound(sndWave)  
Print "Playing sound for 2 seconds ..."  
Delay 2000  
Print "Pausing sound for 2 seconds ..."  
PauseChannel chnWave  
Delay 2000  
Print "Restarting sound ..."  
ResumeChannel chnWave  
Delay 2000  
Print "Changing Pitch of sound ..."  
;StopChannel chnWave  
ChannelPitch chnWave, 22000  
Delay 2000  
Print "Playing new pitched sound ..."  
Delay 2000  
Print "Left speaker only"  
ChannelPan chnWave,-1  
Delay 2000  
Print "Right speaker only"  
ChannelPan chnWave,1  
Delay 2000  
Print "All done!"  
StopChannel chnWave  
end function
;; Often you will need to know if a sound channel has completed playing or  not. This command will return 1 if the sound is still playing or 0 if it has  stopped. Use this to restart your background music or some other sound that  might have stopped unintentionally.
;; Note: This command currently doesn't seem to work with a channel assigned to  CD track playback.
;;param channel_handle = variable assigned to the channel when played
function ChannelPlaying (channel_handle)
; Channel examples  
Print "Loading sound ..."  
; Load the sample - you'll need to point this to a sound on your computer  
; For best results, make it about 5-10 seconds...  
sndWave=LoadSound("level1.wav")  
Print "Playing full sample until sound is done ..."  
chnWave=PlaySound(sndWave)  
While ChannelPlaying(chnWave)  
Wend  
Print "All done!"  
end function
;; While SoundVolume happily changes the volume  of the entire program, this command will let you adjust volume rates on a 'per  channel' basis. Extremely useful.
;; The volume value is a floating point value between 0 and 1 (0 = silence, .5  = half volume, 1= full volume). You can do other cool stuff like ChannelPitch and ChannelPan  too!
;;param channel_handle = variable assigned to the channel when played
;;param volume# = volume level floating value between 0 and 1
function ChannelVolume channel_handle, volume#
; Channel examples  
Print "Loading sound ..."  
; Load the sample - you'll need to point this to a sound on your computer  
; For best results, make it about 5-10 seconds...  
sndWave=LoadSound("level1.wav")  
; Prepare the sound for looping  
LoopSound sndWave  
chnWave=PlaySound(sndWave)  
Print "Playing sound for 2 seconds ..."  
Delay 2000  
Print "Pausing sound for 2 seconds ..."  
PauseChannel chnWave  
Delay 2000  
Print "Restarting sound ..."  
ResumeChannel chnWave  
Delay 2000  
Print "Changing volume of sound ..."  
ChannelVolume chnWave, .5  
Delay 2000  
Print "Playing new half-volume sound ..."  
Delay 2000  
Print "Left speaker only"  
ChannelPan chnWave,-1  
Delay 2000  
Print "Right speaker only"  
ChannelPan chnWave,1  
Delay 2000  
Print "All done!"  
StopChannel chnWave  
end function
;; Use this command to convert a known ASCII code (for example 65) to its character  string equivelant (i.e. the letter "A").
;;param integer = valid ASCII code integer value
function Chr$ (integer)
Print " The character for ASCII value 65 is: " + Chr$(65)  
end function
;; Once you are finished with NextFile$ on the  directory previously opened for read with the ReadDir  command, use this command to close the directory. This is good programming practice!
;; See also: ReadDir, NextFile$
;;param filehandle = valid filehandle assigned from the ReadDir command
function CloseDir filehandle
; ReadDir/NextFile$/CloseDir example  
; Define what folder to start with ...  
folder$="C:"  
; Open up the directory, and assign the handle to myDir  
myDir=ReadDir(folder$)  
; Let's loop forever until we run out of files/folders to list!  
Repeat  
; Assign the next entry in the folder to file$  
file$=NextFile$(myDir)  
; If there isn't another one, let's exit this loop  
If file$="" Then Exit  
; Use FileType to determine if it is a folder (value 2) or a file and print  results  
If FileType(folder$+"\"+file$) = 2 Then  
Print "Folder:" + file$  
Else  
Print "File:" + file$  
End If  
Forever  
; Properly close the open folder  
CloseDir myDir  
; We're done!  
Print "Done listing files!"  
end function
;; Use this command to close a file previously opened. You should always close  a file as soon as you have finished reading or writing to it.
;;param filehandle = variable defined with the WriteFile or OpenFile command
function CloseFile filehandle
; Reading and writing custom types to files using ReadFile, WriteFile and  CloseFile  
; Initialise some variables for the example  
Type HighScore  
Field Name$  
Field Score%  
Field Level%  
End Type  
Best.HighScore = New HighScore  
BestName = "Mark"  
BestScore = 11657  
BestLevel = 34  
; Open a file to write to  
fileout = WriteFile("mydata.dat")  
; Write the information to the file  
WriteString( fileout, BestName )  
WriteInt( fileout, BestScore )  
WriteByte( fileout, BestLevel )  
; Close the file  
CloseFile( fileout )  
; Open the file to Read  
filein = ReadFile("mydata.dat")  
; Lets read the Greatest score from the file  
Greatest.HighScore = New HighScore  
GreatestName$ = ReadString$( filein )  
GreatestScore = ReadInt( filein )  
GreatestLevel = ReadByte( filein )  
; Close the file once reading is finished  
CloseFile( filein )  
Print "High score record read from - mydata.dat "  
Print  
Write "Name = "  
Print GreatestName  
Write "Score = "  
Print GreatestScore  
Write "Level = "  
Print GreatestLevel  
WaitKey()  
end function
;; Closes a movie that you have opened with <a class=small href=../2d_commands/OpenMovie.htm>OpenMovie</a> .
;; See also: <a class=small href=OpenMovie.htm>OpenMovie</a>, <a class=small href=DrawMovie.htm>DrawMovie</a>, <a class=small href=MoviePlaying.htm>MoviePlaying</a>, <a class=small href=MovieWidth.htm>MovieWidth</a>, <a class=small href=MovieHeight.htm>MovieHeight</a>.
;;param movie - movie handle
function CloseMovie movie
; Movie Commands Example  
; ======================  
; This demonstrates the following commands:  
;  
;	OpenMovie  
;	MovieHeight  
;	MovieWidth  
;	MoviePlaying  
;	DrawMovie  
; Some constants to start with  
Const WIDTH = 640  
Const HEIGHT = 480  
; First of all, set up the graphics  
Graphics WIDTH, HEIGHT  
SetBuffer BackBuffer()  
ClsColor 0,0,0  
Color 0,255,0  
; Next, open the movie file.  Feel free to change this to an AVI or MPEG file.  
movie=OpenMovie("media/hat.gif")  
; check to see if it loaded okay  
If movie=0 Then RuntimeError "Error - Movie not loaded!"  
If Not(MoviePlaying(movie)) Then RuntimeError "Error - Movie not playing!"  
;Now determine the size of the movie  
w=MovieWidth(movie)     ; the width of the movie  
h=MovieHeight(movie)    ; the height of the movie  
; Now set up the starting position and timing variables  
x=(WIDTH-w)/2           ; the x position of the movie on screen  
y=(HEIGHT-h-100)/2      ; the y position of the movie on screen  
period=100              ; the interval between frames  
time=MilliSecs()        ; the time of the last frame update  
; And here's the main loop  
Repeat  
; Wait for the specified period  
; GIFs have no timing info, and as such will redraw the next frame on each call to DrawMovie.  
; AVIs and MPEGs do have timing info, and as such will redraw the most recent frame on each call to DrawMovie.  
; Ergo, this time limiter only has an impact, and is only required for GIFs.  
Repeat  
; do nothing  
Until MilliSecs()-time>=period  
time=MilliSecs()    ; save the current time for the next frame  
; Handle keyboard inputs  
; CONTROL adjusts the speed with which we do stuff  
If KeyDown(29) Or KeyDown(157) Then  
change=5  
Else  
change=1  
End If  
; SHIFT means we're dealing with the size  
If KeyDown(42) Or KeyDown(54) Then  
If KeyDown(203) And w>change-1 Then w=w-change  
If KeyDown(205) And x+w+change < WIDTH Then w=w+change  
If KeyDown(200) And h>change-1 Then h=h-change  
If KeyDown(208) And y+h+change < HEIGHT Then h=h+change  
Else  
; otherwise it's the position that we're changing  
If KeyDown(203) And x>change-1 Then x=x-change  
If KeyDown(205) And x+w+change < WIDTH Then x=x+change  
If KeyDown(200) And y>change-1 Then y=y-change  
If KeyDown(208) And y+h+change < HEIGHT Then y=y+change  
EndIf  
; +/- to change the animation speed  
If ( KeyDown(13) Or KeyDown(78) ) And period>change Then period=period-change  
If ( KeyDown(12) Or KeyDown(74) ) And period < 500 Then period=period+change  
; Redraw the screen, by...  
Cls                         ; clear the screen  
DrawMovie movie,x,y,w,h     ; draw the movie  
; draw the instructions  
Text 0,0,"Use the arrow keys to reposition the movie."  
Text 0,20,"Hold SHIFT with the arrow keys to resize."  
Text 0,40,"Use + or - or control the animation speed."  
Text 0,60,"Hold CONTROL to resize, move, or change speed faster."  
Text 0,80,"Press ESCAPE to exit."  
Text 0,100,"Current Command Syntax: DrawMovie(movie, " + x + ","+ y + "," + w + "," + h + ")"  
; Flip the buffers  
Flip  
Until KeyHit(1) ; Escape to exit  
; Remove the movie from memory before closing down  
CloseMovie(movie)  
End ; bye!  
end function
;; Closes a TCP/IP server previously created with the CreateTCPServer command.
;;param serverhandle = handle assigned when the server was created.
function CloseTCPServer serverhandle
; CreateTCPServer, CloseTCPServer, AcceptTCPStream Example  
; This code is in two parts, and needs to be run seperately on the same machine  
; --- Start first code set ---  
; Create a server and listen for push  
svrGame=CreateTCPServer(8080)  
If svrGame<>0 Then  
Print "Server started successfully."  
Else  
Print "Server failed to start."  
End  
End If  
While Not KeyHit(1)  
strStream=AcceptTCPStream(svrGame)  
If strStream Then  
Print ReadString$(strStream)  
Delay 2000  
End  
Else  
Print "No word from Apollo X yet ..."  
Delay 1000  
End If  
Wend  
End  
; --- End first code set ---  
; --- Start second code set ---  
; Copy this code to another instance of Blitz Basic  
; Run the above code first, then run this ... they will 'talk'  
; Create a Client and push data  
strmGame=OpenTCPStream("127.0.0.1",8080)  
If strmGame<>0 Then  
Print "Client Connected successfully."  
Else  
Print "Server failed to connect."  
WaitKey  
End  
End If  
; write stream to server  
WriteString strmGame,"Mission Control, this is Apollo X ..."  
Print "Completed sending message to Mission control..."  
; --- End second code set ---  
end function
;; Once you've completed the use of your TCP/IP stream, close the connection  you opened with OpenTCPStream with this command.
;;param streamhandle = handle assigned when the stream was opened.
function CloseTCPStream streamhandle
; OpenTCPStream/CloseTCPStream Example  
Print "Connecting..."  
tcp=OpenTCPStream( "www.blitzbasement.com",80 )  
If Not tcp Print "Failed.":WaitKey:End  
Print "Connected! Sending request..."  
WriteLine tcp,"GET http://www.blitzbasement.com HTTP/1.0"  
WriteLine tcp,Chr$(10)  
If Eof(tcp) Print "Failed.":WaitKey:End  
Print "Request sent! Waiting for reply..."  
While Not Eof(tcp)  
Print ReadLine$( tcp )  
Wend  
If Eof(tcp)=1 Then Print "Success!" Else Print "Error!"  
CloseTCPStream tcp  
WaitKey  
End  
end function
;; None
;; Click <a href=http://www.blitzbasic.co.nz/b3ddocs/command.php?name=CloseUDPStream&ref=comments target=_blank>here</a> to view the latest version of this page online</body>
;; </html>
;;param udp_stream - UDP stream handle
function CloseUDPStream udp_stream
end function
;; This command will wipe the current drawing buffer clean of any graphics  or text present and reset the drawing buffer back to the color defined in the ClsColor command
;;param None.
function Cls
;set ClsColor to red  
ClsColor 255,0,0  
;set current drawing buffer to the color set by the ClsColor command  
Cls  
end function
;; This changes the color for subsequent CLS calls. Use  this command when you need CLS to 'clear' the screen with some other color than  black.
;;param red, green and blue = number between 0 and 255
function ClsColor red,green,blue
;set ClsColor to red  
ClsColor 255,0,0  
;set current drawing buffer to the color set by the ClsColor command  
Cls  
end function
;; This command sets the drawing color (using RGB values) for all subsequent  drawing commands (Line, Rect, Text, etc.) You must be in Graphics  mode to execute this command.
;;param red = value of red component (0-255)
;;param green = value of green component (0-255)
;;param blue = value of blue component (0-255)
function Color red,green,blue
; Color, ColorRed(), ColorBlue(), ColorGreen() Example  
; Gotta be in graphics mode  
Graphics 640,480  
; Change the random seed  
SeedRnd MilliSecs()  
; Let's set the color to something random  
Color Rnd(0,255),Rnd(0,255),Rnd(0,255)  
; Now let's see what they are!  
While Not KeyHit(1)  
Text 0,0,"This Text is printed in Red=" + ColorRed() + " Green=" + ColorGreen()  + " Blue=" + ColorBlue() + "!"  
Wend  
end function
;; Use this command to return the blue component of the RGB color of the current  drawing color. Use ColorRed() and ColorGreen() for the other two color components.
;;param None.
function ColorBlue()
; Color, ColorRed(), ColorBlue(), ColorGreen() Example  
; Gotta be in graphics mode  
Graphics 640,480  
; Change the random seed  
SeedRnd MilliSecs()  
; Let's set the color to something random  
Color Rnd(0,255),Rnd(0,255),Rnd(0,255)  
; Now let's see what they are!  
While Not KeyHit(1)  
Text 0,0,"This Text is printed in Red=" + ColorRed() + " Green=" + ColorGreen()  + " Blue=" + ColorBlue() + "!"  
Wend  
end function
;; Use this command to return the green component of the RGB color of the current  drawing color. Use ColorRed() and ColorBlue() for the other two color components.
;;param None.
function ColorGreen()
; Color, ColorRed(), ColorBlue(), ColorGreen() Example  
; Gotta be in graphics mode  
Graphics 640,480  
; Change the random seed  
SeedRnd MilliSecs()  
; Let's set the color to something random  
Color Rnd(0,255),Rnd(0,255),Rnd(0,255)  
; Now let's see what they are!  
While Not KeyHit(1)  
Text 0,0,"This Text is printed in Red=" + ColorRed() + " Green=" + ColorGreen()  + " Blue=" + ColorBlue() + "!"  
Wend  
end function
;; Use this command to return the red component of the RGB color of the current  drawing color. Use ColorBlue() and ColorGreen() for the other two color components.
;;param None.
function ColorRed()
; Color, ColorRed(), ColorBlue(), ColorGreen() Example  
; Gotta be in graphics mode  
Graphics 640,480  
; Change the random seed  
SeedRnd MilliSecs()  
; Let's set the color to something random  
Color Rnd(0,255),Rnd(0,255),Rnd(0,255)  
; Now let's see what they are!  
While Not KeyHit(1)  
Text 0,0,"This Text is printed in Red=" + ColorRed() + " Green=" + ColorGreen()  + " Blue=" + ColorBlue() + "!"  
Wend  
end function
;; If you are writing an application or game that allows starting with special  parameters on the command line, you can use this command to retrieve the parameters.
;; For example, you might want to start the program with a debug variable set so  you can track stuff during execution. So, you could offer the ability to run  the executatble with a /debug parameter. If they execute the program with the  parameter, then you can set a flag inside your game.
;; To simulate the command line passing in the editor, select PROGRAM->PROGRAM  COMMAND LINE from the pulldowns and enter a value to be passed at runtime.
;; See the example.
;;param None.
function CommandLine$()
; CommandLine$() Example  
; Be sure to use PROGRAM->PROGRAM COMMAND LINE from the  
; pull down and put /debug in there to test with.  
a$=CommandLine$()  
If a$="/debug" Then  
Print "Debug mode is on!"  
debug=1  
Else  
Print "No debugging activated."  
debug=0  
End If  
end function
;; This declares a variable as a constant (a variable whose value will never  change and cannot be changed) and assigns the value to it.
;; Assign constants to values you know will not change in your game (screen width,  height - scoring values - etc). This reduces the load of the variable memory,  since Blitz knows it will never change size unlike other variables which can  grow and shrink in size based on what value it holds.
;; See also: <a class=small href=Global.htm>Global</a>, <a class=small href=Local.htm>Local</a>, <a class=small href=Dim.htm>Dim</a>.
;;param variablename - any valid variable name and its assignment
function Const variablename
; CONST Example  
Const scrWidth=640  
Const scrHeight=480  
Const alienscore=100  
Graphics scrWidth,scrHeight  
Print "The point value for shooting any alien is always " + alienscore  
end function
;; Copies data from one memory bank to another.
;;param src_bank = handle of source memory bank
;;param src_offset = offset location to start copying from
;;param dest_bank = handle of destination memory bank
;;param dest_offset = offset location to start writing to
;;param count = how many bytes to copy
function CopyBank src_bank,src_offset,dest_bank,dest_offset,count
; BankSize, ResizeBank, CopyBank Example  
; create a bank  
bnkTest=CreateBank(5000)  
; Fill it with rand Integers  
For t = 0 To 4999  
PokeByte bnkTest,t,Rand(9)  
Next  
; Resize the bank  
ResizeBank bnkTest,10000  
; Copy the first half of the bank to the second half  
CopyBank bnkTest,0,bnkTest,5000,5000  
; Print final banksize  
Print BankSize(bnkTest)  
end function
;; Use this command to copy a file from one location to another. Perhaps you'll  write your own installer and need to copy files from the installation folder  to the installed location folder. Make sure you do your own validation to ensure  that the files/paths are valid and accurate before executing this command.
;;param from$ = valid path/filename to the file to be copied
;;param to$ = valid path/filename to copy the file to
function CopyFile from$, to$
file$="c:autoexec.bat"  
destination$="a:autoexec.bat"  
Print "Press any key to copy your Autoexec.bat file to floppy"  
WaitKey()  
CopyFile file$,destination$  
end function
;; Instead of loading a graphic twice in two different handles, you can load  the image ONCE then use the CopyImage command to make as many copies in memory  as you want.
;; Why would you want to do this? So you still have a copy of the original image  in case you want to alter a copy later for another purpose.
;;param handle=the variable you gave the handle to when you loaded the image
function CopyImage (handle)
; CopyImage Example  
; Load an image and give its handle to gfxOld variable  
gfxOld=LoadImage("mypicture.bmp")  
; Duplicate the gfxOld image to a new handle variable  
gfxNew=CopyImage(gfxOld)  
end function
;; You can use this command  on a locked buffer for a slight speed-up. See LockBuffer.
;; See also: CopyPixelFast.
;;param src_x - x-coordinate of source pixel to copy from
;;param src_y - y-coordinate of source pixel to copy from
;;param src_buffer - name of buffer to copy from, e.g. ImageBuffer()
;;param dest_x - x-coordinate of destination pixel to copy to
;;param dest_y - y-coordinate of destination pixel to copy to
;;param dest_buffer (optional) - name of buffer to copy to, e.g. BackBuffer()
function CopyPixel src_x,src_y,src_buffer,dest_x,dest_y,[dest_buffer]
; CopyPixel/CopyPixelFast Example  
; -------------------------------  
Graphics 640,480,16  
Print "Press a key to use CopyPixel to copy the top half of an image to the  frontbuffer"  
WaitKey()  
; Load an image - can be anything  
pic=LoadImage("media/blitz_pic.bmp")  
; Use CopyPixel to copy the top half of the image to the frontbuffer  
For y=0 To ImageHeight(pic)/2  
For x=0 To ImageWidth(pic)  
CopyPixel x,y,ImageBuffer(pic),x,y  
Next  
Next  
Locate 0,GraphicsHeight()/2  
Print "Press a key to use CopyPixelFast to copy the bottom half of the image"  
Print "Once this has finished, you can then press a key to end the program"  
WaitKey()  
; Lock buffer before using CopyPixelFast  
LockBuffer  
; Use CopyPixelFast to copy the bottom half of the image to the frontbuffer  
For y=0 To (ImageHeight(pic)/2)+ImageHeight(pic)  
For x=0 To ImageWidth(pic)  
CopyPixelFast x,y,ImageBuffer(pic),x,y  
Next  
Next  
; Unlock buffer after using CopyPixelFast  
UnlockBuffer  
WaitKey()  
end function
;; IMPORTANT:
;; You *must* use this command on a locked buffer, otherwise the command will  fail. See LockBuffer.
;; Also, you must make sure that the coordinates that you are copying from and  to are valid, otherwise you will end up overwriting other areas of memory.
;; WARNING:
;; By not following the above advice, you may cause your computer to crash.
;; See also: CopyPixel.
;;param src_x - x-coordinate of source pixel to copy from
;;param src_y - y-coordinate of source pixel to copy from
;;param src_buffer - name of buffer to copy from, e.g. ImageBuffer()
;;param dest_x - x-coordinate of destination pixel to copy to
;;param dest_y - y-coordinate of destination pixel to copy to
;;param dest_buffer (optional) - name of buffer to copy to, e.g. BackBuffer()
function CopyPixelFast src_x,src_y,src_buffer,dest_x,dest_y,[dest_buffer]
; CopyPixel/CopyPixelFast Example  
; -------------------------------  
Graphics 640,480,16  
Print "Press a key to use CopyPixel to copy the top half of an image to the  frontbuffer"  
WaitKey()  
; Load an image - can be anything  
pic=LoadImage("media/blitz_pic.bmp")  
; Use CopyPixel to copy the top half of the image to the frontbuffer  
For y=0 To ImageHeight(pic)/2  
For x=0 To ImageWidth(pic)  
CopyPixel x,y,ImageBuffer(pic),x,y  
Next  
Next  
Locate 0,GraphicsHeight()/2  
Print "Press a key to use CopyPixelFast to copy the bottom half of the image"  
Print "Once this has finished, you can then press a key to end the program"  
WaitKey()  
; Lock buffer before using CopyPixelFast  
LockBuffer  
; Use CopyPixelFast to copy the bottom half of the image to the frontbuffer  
For y=0 To (ImageHeight(pic)/2)+ImageHeight(pic)  
For x=0 To ImageWidth(pic)  
CopyPixelFast x,y,ImageBuffer(pic),x,y  
Next  
Next  
; Unlock buffer after using CopyPixelFast  
UnlockBuffer  
WaitKey()  
end function
;; Copies a rectangle of graphics from one buffer to another. If a buffer is  omitted, the current buffer is used.
;;param src_x = source top left x location to begin copying from
;;param src_y = source top left y location to begin copying from
;;param src_width = width of source area to copy
;;param src_height = height of source area to copy
;;param dest_x = destination top left x location to copy to
;;param dest_y = destination top left y location to copy to
;;param src_buffer = handle to the source image buffer (optional)
;;param dest_buffer = handle to the destination image buffer (optional)
function CopyRect src_x,src_y,src_width,src_height,dest_x,dest_y,[src_buffer],[dest_buffer]
; CopyRect Example  
; Turn on graphics mode  
Graphics 800,600,16  
; create a blank image  
gfxBlank=CreateImage (300,300)  
; Fill the screen with random boxes in random colors  
For t = 1 To 1000  
Rect Rand(800),Rand(600),Rand(100),Rand(100),Rand(0,1)  
Color Rand(255),Rand(255),Rand(255)  
Next  
; Wait a couple of seconds so the user can see it  
Delay 2000  
; Copy graphics randomly from the front buffer to the blank image  
CopyRect Rand(800),Rand(600),300,300,0,0,FrontBuffer(),ImageBuffer(gfxBlank)  
; Clear the screen, draw the copied to image, wait for user to hit a key  
Cls  
DrawImage gfxBlank,0,0  
WaitKey  
end function
;; Copies a stream.
;; Click <a href=http://www.blitzbasic.co.nz/b3ddocs/command.php?name=CopyStream&ref=comments target=_blank>here</a> to view the latest version of this page online</body>
;; </html>
;;param src_stream - source stream
;;param dest_stream - destination stream
;;param buffer_size (optional) - buffer size of stream
function CopyStream src_stream,dest_stream[,buffer_size]
end function
;; For angles between 0 and 90 degrees this is defined by the sides of a right triangle. The cosine is the side adjacent to the angle divided by the hypotenuse.
;; Outside of 0 to 90 the definition uses a circle with radius=1. The angle is placed at the center of the circle, with one side on the positive x-axis. The other side hits the circle at some point. The x coordinate of this point is the cosine of the angle.
;; The positive y-axis corresonds to +90 degrees. This is a common source of confusion in Blitz. With screen coordinates ( pixels ) the y-axis points downward. But in the 3d world the y-axis typically points upward.
;; Another possible snag is the size of the angle. In principle, the cosine function repeats every 360 degrees. So Cos(-360), Cos(0), Cos(360), Cos(720) etc. should all be exactly the same. But in practice the accuracy decreases as the angle gets farther away from zero.
;; See also ASin, Cos, ACos, Tan, Atan, ATan2
;;param degrees# = angle in degrees.
function Cos# ( degrees# )
; Sin / Cos / Tan example.  
; Left/Right arrow keys change angle. Escape quits.  
Const width = 640, height = 480  
Const radius# = .2 * height  
Const KEY_ESC = 1, KEY_LEFT = 203, KEY_RIGHT = 205  
Graphics width, height  
SetBuffer BackBuffer( )  
Origin width / 3, height / 2  
angle# = 0.0  
While Not KeyDown( KEY_ESC )  
; NOTE: It is usually best to avoid very large angles.  
;       The 'If angle...' lines show one way to do this.  
;		Mod is another possibility.  
If KeyDown( KEY_LEFT ) Then angle = angle - .5  
;	If angle < 0.0 Then angle = angle + 360  
If KeyDown( KEY_RIGHT ) Then angle = angle + .5  
;	If angle >= 360.0 Then angle = angle - 360  
Cls  
Color 80, 80, 0		; pale yellow circle  
Oval -radius, -radius, 2 * radius, 2 * radius, False  
For a# = 0.0 To Abs( angle Mod 360 ) Step .5  
x# = radius * Cos( a )	; (x,y) is a point on the circle  
y# = radius * Sin( a )	; corresponding to angle a.  
If ( angle Mod 360 < 0 ) Then y = -y	; reverse for negative angle  
WritePixel x, y, $ffff00		; bright yellow  
Next  
Color 255, 255, 0		; yellow  
Line 0, 0, radius * Cos( angle ), radius * Sin( angle )  
Color 0, 255, 0			; green  
Line 0, 0, radius * Cos( angle ), 0  
Text radius * 1.5,  10, "Cos( angle ) = " + Cos( angle )  
Color 255, 0, 0			; red  
Line radius * Cos( angle ), 0, radius * Cos( angle ), radius * Sin( angle )  
Text radius * 1.5, -10, "Sin( angle ) = " + Sin( angle )  
Color 255, 255, 255  
Text radius * 1.5, -30, "     angle =   " + angle  
Text radius * 1.5,  30, "Tan( angle ) = " + Tan( angle )  
Flip  
Wend  
End  
end function
;; Some computers may have more than one video card and/or video driver installed  (a good example is a computer system with a primary video card and a Voodoo2  or other pass-through card). Once you know how many drivers there are, you can  iterate through them with GfxDriverName$ and  display them for the user to choose from. Once the user has chosen (or you decide),  you can set the graphics driver with SetGfxDriver.  Normally, this won't be necessary with 2D programming.
;;param None.
function CountGfxDrivers()
; GfxDriver Examples  
; Count how many drivers there are  
totalDrivers=CountGfxDrivers()  
Print "Choose a driver to use:"  
; Go through them all and print their names (most people will have only 1)  
For t = 1 To totalDrivers  
Print t+") " + GfxDriverName$(t)  
Next  
; Let the user choose one  
driver=Input("Enter Selection:")  
; Set the driver!  
SetGfxDriver driver  
Print "Your driver has been selected!"  
end function
;; Use this command to return the number of video modes the user's video card  can display in. Use the GFXModeWidth, GFXModeHeight, and GFXModeDepth with each number of video mode to  determine the width, height, and color depth capabilities of each mode. See  example.
;;param None.
function CountGFXModes()
; CountGFXModes()/GfxModeWidth/GfxModeHeight/GfxModeDepth example  
intModes=CountGfxModes()  
Print "There are " + intModes + "graphic modes available:"  
; Display all modes including width, height, and color depth  
For t = 1 To intModes  
Print "Mode " + t + ":  
Print "Width=" + GfxModeWidth(t)  
Print "Height=" + GfxModeHeight(t)  
Print "Height=" + GfxModeDepth(t)  
Next  
end function
;; None.
;; Click <a href=http://www.blitzbasic.co.nz/b3ddocs/command.php?name=CountHostIPs&ref=comments target=_blank>here</a> to view the latest version of this page online</body>
;; </html>
;;param host_name$ - name of host
function CountHostIPs( host_name$ )
end function

end function
;; The bank commands allow  you to perform high-speed data operations on a block of memory. This is useful  for writing your own compression/decompression routines, passing and receiving  data to and from a DLL and more.  Banks start at 0 and finish at size-1.
;; The data types available for use with a bank are:
;; Byte - takes up one byte. Values can be in the range 0 to 255.
;; Short - takes up two bytes. Values can be in the range 0 to 65535.
;; Int - takes up four bytes. Values can be in the range -2147483647 to 2147483647.
;; Float - takes up four bytes.
;; See also: <a class=small href=FreeBank.htm>FreeBank</a>.
;;param size - size of memory bank in bytes (default is 0 bytes)
function CreateBank ([size])
; Bank Commands Example  
; ---------------------  
bnkTest=CreateBank(12)  
PokeByte bnkTest,0,Rand(255)  
PokeShort bnkTest,1,Rand(65535)  
PokeInt bnkTest,3,Rand(-2147483648,2147483647)  
PokeFloat bnkTest,7,0.5  
Print PeekByte(bnkTest,0)  
Print PeekShort(bnkTest,1)  
Print PeekInt(bnkTest,3)  
Print PeekFloat(bnkTest,7)  
FreeBank bnkTest  
end function
;; Creates a directory (file folder) at the destination specified. Do not use  a trailing slash at the end of the path/name parameter. You cannot be sure the  directory was created with this command, so you will need to verify its existance  yourself (use the FILETYPE command).
;;param path/name = full path and name for new directory
function CreateDir path/name
; CREATEDIR example  
fldr$="c:winntsystem32myfolder"  
createDir fldr$  
Print "Folder created!"  
end function
;; Sometimes you want to create a completely new graphic on the fly (using  other images, drawing commands, etc.) instead of loading one. This command will  let you create a new image with a single frame or multiple frames for animation.  You specify the width, height, and optional number of frames. The example should  be pretty self-explainatory.
;;param width=width of the new image (or its frames)
;;param height=height of the new image
;;param frames= optional number of frames (assumed to be a single frame)
function CreateImage (width,height[,frames])
; CreateImage/TileImage/ImageBuffer example  
; Again, we'll use globals even tho we don't need them here  
; One variable for the graphic we'll create, one for a timer  
Global gfxStarfield, tmrScreen  
; Declare graphic mode  
Graphics 640,480,16  
; Create a blank image that is 32 pixels wide and 32 high with 10 frames of  32x32  
gfxStarfield=CreateImage(32,32,10)  
; loop through each frame of the graphic we just made  
For t = 0 To 9  
; Set the drawing buffer to the graphic frame so we can write on it  
SetBuffer ImageBuffer(gfxStarfield,t)  
; put 50 stars in the frame at random locations  
For y = 1 To 50  
Plot Rnd(32),Rnd(32)  
Next  
Next  
; Double buffer mode for smooth screen drawing  
SetBuffer BackBuffer()  
; Loop until ESC is pressed  
While Not KeyHit(1)  
; Only update the screen every 300 milliseconds. Change 300 for faster or  
; slower screen updates  
If MilliSecs() > tmrScreen+300 Then  
Cls ; clear the screen  
; Tile the screen with a random frame from our new graphic starting at  
; x=0 and y=0 location.  
TileImage gfxStarfield,0,0,Rnd(9)  
Flip ; Flip the screen into view  
tmrScreen=MilliSecs() ; reset the time  
End If  
Wend  
end function
;; Creates a new local player. This also causes a special message to be sent  to all remote machines (see NetMsgType). This returns  an integer player number to be used in sending/receiving messages. Note that  you must create at least one player before you can send and receive messages.
;;param name$ = any valid string holding the player's name.
function CreateNetPlayer (name$)
; CreateNetPlayer example  
newGame = StartNetGame()  
; Check the status of the new game.  
If newGame = 0 Then  
Print "Could not start or join net game."  
End  
ElseIf newGame = 1  
Print "Successfully joined the network game"  
ElseIf newGame = 2  
Print "A new network game was started!"  
EndIf  
; Create a random player name  
name$="Player" + Rand(100)  
; Get a unique player id number for the player  
; and create the player  
playerID=CreateNetPlayer(name$)  
If playerID = 0 Then  
Print "Player could not be created!"  
Else  
Print "Player " + name$ + " was created and given ID#" + playerID  
End If  
WaitKey()  
end function
;; Creates a TCP/IP server with the designated port. Use this for communications  between other clients and the local box. See OpenTCPStream, CloseTCPServer, and CloseTCPStream for more information.
;; Returns a TCP/IP server handle if successful or 0 if not.
;;param port = the port to use when creating the server
function CreateTCPServer (port)
; CreateTCPServer, CloseTCPServer, AcceptTCPStream Example  
; This code is in two parts, and needs to be run seperately on the same machine  
; --- Start first code set ---  
; Create a server and listen for push  
svrGame=CreateTCPServer(8080)  
If svrGame<>0 Then  
Print "Server started successfully."  
Else  
Print "Server failed to start."  
End  
End If  
While Not KeyHit(1)  
strStream=AcceptTCPStream(svrGame)  
If strStream Then  
Print ReadString$(strStream)  
Delay 2000  
End  
Else  
Print "No word from Apollo X yet ..."  
Delay 1000  
End If  
Wend  
End  
; --- End first code set ---  
; --- Start second code set ---  
; Copy this code to another instance of Blitz Basic  
; Run the above code first, then run this ... they will 'talk'  
; Create a Client and push data  
strmGame=OpenTCPStream("127.0.0.1",8080)  
If strmGame<>0 Then  
Print "Client Connected successfully."  
Else  
Print "Server failed to connect."  
WaitKey  
End  
End If  
; write stream to server  
WriteString strmGame,"Mission Control, this is Apollo X ..."  
Print "Completed sending message to Mission control..."  
; --- End second code set ---  
end function
;; Use this command in conjunction with the WaitTimer command to control the  speed of program execution (fps). You will use this in your main screen redraw  loop to control the playback speed to match the proper speed. This will prevent  your games from playing back too fast on computers faster than yours. Use of  this system is VERY GOOD practice, as your game will be played on a variety  of computers.
;;param frequency = usually a framerate like 50 or 60
function CreateTimer (frequency)
; Create the timer to track speed  
frameTimer=CreateTimer(60)  
; Your main screen draw loop  
While Not KeyHit(1)  
WaitTimer(frameTimer) ; Pause until the timer reaches 60  
Cls  
; Draw your screen stuff  
Flip  
Wend  
end function
;; If no port is specified, a free port will be allocated and you can then use UDPStreamPort() to find out the allocated port.
;; Click <a href=http://www.blitzbasic.co.nz/b3ddocs/command.php?name=CreateUDPStream&ref=comments target=_blank>here</a> to view the latest version of this page online</body>
;; </html>
;;param port (optional) - port number
function CreateUDPStream( [port] )
end function
;; Returns the current date in the format: DD MON YYYY (i.e. 10 DEC 2000).
;;param None
function CurrentDate$()
; Print the current date to the screen  
Print "The date is:" + CurrentDate$()  
end function
;; This command will return the currently selected directory for disk operations,  useful for advanced file operations. Use CHANGEDIR  to change the current directory. The value returned doesn't have a trailing  slash - aside from the root directory of the drive.
;;param None.
function CurrentDir$()
; CurrentDir$() example  
; Print the current directory until ESC key  
While Not KeyHit(1)  
Print CurrentDir$()  
Wend  
end function
;; Returns the current time in the format: HH:MM:SS (i.e. 14:31:57).
;;param None
function CurrentTime$()
; Print the current time to the screen  
Print "The Time is:" + CurrentTime$()  
end function
;; Data is used to create neat, comma delimited lists of values of a constant  nature that you will be reading (and probably reusing) during the execution  of your game. You may store level information (number of enemies, stars, etc)  there or just about anything you can think of! You can easily mix datatypes  (strings, integers, floats) in your Data statements, as long as they are read  properly with the Read command later. You will need to  use the Restore command to point to the .Label that begins your Data statements. See the related  commands for more information and more examples.
;; See also: <a class=small href=Read.htm>Read</a>, <a class=small href=Restore.htm>Restore</a>.
;;param list_of_values = a list of comma delimited values (strings must be inside  quotes)
function Data list_of_values
Print "Here we go!"  
; Restore to the start of the Data statements  
Restore startData  
; Get the first number which is the total number of users  
Read Users  
; Print them all!  
For T = 1 To Users  
Read firstname$  
Read age  
Read accuracy#  
Read lastname$  
Print firstname$ + " " + lastname$ + " is " + age + " years old with " + accuracy#  + " accuracy!"  
Next  
While Not KeyHit(1)  
Wend  
End  
.startData  
Data 3  
Data "Shane", 31, 33.3333, "Monroe"  
Data "Bob", 28, 12.25, "Smith"  
Data "Roger", 54, 66.66, "Rabbit"  
end function
;; You power programmers will just love this. You have your own debug log to write to!  The DebugLog can be viewed from the debugger window during program execution only.
;; For those not familiar to this sort of thing, think of the Debug log like your  own private 'in program notepad'. Use this to write messages to yourself during program execution. For example, you could write the graphic modes that the user has on his system, or just little alerts to let you know your code execution  made it to a certain point in the execution without interrupting it. I'm sure  you'll find a lot of uses for this! See the example if you're still lost.
;; See also: <a class=small href=Stop.htm>Stop</a>.
;;param message = message text string value
function DebugLog message
; DebugLog Example  
; Let's start graphics mode  
Graphics 640,480,16  
; Now, let's load an image that doesn't exist!  
gfxPlayer=LoadImage("noimagefound.jpg")  
If gfxPlayer=0 Then  
DebugLog "Player's Graphics failed to load!"  
End If  
; This is supposed to generate an error. Press F9 to see the log!  
While Not KeyHit(1)  
DrawImage gfxPlayer,100,100  
Wend  
end function
;; In a SELECT structure, you may wish to execute  code if none of the cases you specify are met. All code after DEFAULT to END SELECT will be executed if no case is met.  See SELECT, CASE, and the example  for more.
;; See also: <a class=small href=Select.htm>Select</a>, <a class=small href=Case.htm>Case</a>, <a class=small href=End Select.htm>End Select</a>.
;;param None.
function Default
; SELECT/CASE/DEFAULT/END SELECT Example  
; Assign a random number 1-10  
mission=Rnd(1,10)  
; Start the selection process based on the value of 'mission' variable  
Select mission  
; Is mission = 1?  
Case 1  
Print "Your mission is to get the plutonium and get out alive!"  
; Is mission = 2?  
Case 2  
Print "Your mission is to destroy all enemies!"  
; Is mission = 3?  
Case 3  
Print "Your mission is to steal the enemy building plans!"  
; What do do if none of the cases match the value of mission  
Default  
Print "Missions 4-10 are not available yet!"  
; End the selection process  
End Select  
end function
;; This command stops all program execution for the designated time period.  All execution stops. If you need program execution to continue, consider trapping  time elapsed with a custom timer function using Millisecs().
;;param milliseconds = the amount of milliseconds to delay. 1000=1 second
function Delay milliseconds
; Delay for 5 seconds  
Delay 5000  
end function
;; If you haven't read up on the TYPE command, you might  want to do so before continuing.
;; Use the Delete command to remove an object from the Type collection. Use  the commands FIRST, LAST, BEFORE, and NEXT to move the object pointer to the  object you want to delete, then issue the Delete command with the custom Type  name.   If you wish to delete all objects of a certain type, you can use DELETE EACH <TYPE>.
;; This is often used in a FOR ... EACH loop when a collision happens and you  wish to remove the object (an alien ship object for example) from the collection.
;; See also: <a class=small href=Type.htm>Type</a>, <a class=small href=New.htm>New</a>, <a class=small href=Before.htm>Before</a>, <a class=small href=After.htm>After</a>, <a class=small href=First.htm>First</a>, <a class=small href=Last.htm>Last</a>, <a class=small href=Each.htm>Each</a>, <a class=small href=Insert.htm>Insert</a>.
;;param custom_type_name = the custom name of an existing object
function Delete custom_type_name
; Move them all over 1 (like the description example from TYPE command)  
; If the chair isn't on the screen anymore, delete that chair object from the  
; collection.  
For room.chair = Each chair  
room\x = room\x + 1  
if room\x > 640 then  
Delete room  
Next  
end function
;; Deletes a specified folder/directory from the device. Note: This only works  on EMPTY directories - you cannot delete a folder with other folders or files  inside with this command. Do not apply a trailing slash.
;;param directory/path = full path/name of directory
function DeleteDir directory/path
; DeleteDir example  
DeleteDir "C:	est"  
end function
;; Deletes a specified file from the drive. You will need to make sure the  file exists before execution and be sure its been deleted AFTER execution. Use FILETYPE to determine this.
;;param path/filename = full path/filename to the file to delete
function DeleteFile path/filename
; DELETEFILE example  
DeleteFile "C:	estmyfile.bb"  
end function
;; Using the playerID generated by the CreateNetPlayer  command, this command will remove the designated player from the network game.
;; This also causes a special message to be sent to all remote machines (see NetMsgType).
;;param playerID = value assigned when player was created with CreateNetPlayer
function DeleteNetPlayer playerID
; DeleteNetPlayer example  
newGame = StartNetGame()  
; Check the status of the new game. If newGame = 0 Then  
Print "Could not start or join net game."  
End  
ElseIf newGame = 1  
Print "Successfully joined the network game"  
ElseIf newGame = 2  
Print "A new network game was started!"  
EndIf  
; Create a random player name  
name$="Player" + Rand(100)  
; Get a unique player id number for the player  
; and create the player  
playerID=CreateNetPlayer(name$)  
If playerID = 0 Then  
Print "Player could not be created!"  
Else  
Print "Player " + name$ + " was created and given ID#" + playerID  
WaitKey()  
; delete the player!  
DeleteNetPlayer playerID  
Print "The local player was deleted!"  
End If  
waitkey()  
end function
;; Creates an array of the specified type. For example, Dim tiles(10) creates  an integer array, Dim tiles#(10) creates a float array and Dim tile$(10) creates  a string array.
;; The contents of an array can be accessed using the index notation:  0 - indexn, giving indexn+1 number of elements for that particular index range. You should not attempt to access a non-existent element of the array.  In debug mode this will cause an error stating 'index out of bounds'.  With debug off however, you may get 'illegal memory access' errors, or worse no immediate errors at all.
;; Arrays are global, and must be defined in the main program.
;; Arrays can be re-dimmed by using the Dim statement again with the same array  name, but the contents of the array will be lost.
;; -----------------------------------------------------------
;; *NEW* BLITZ ARRAYS *NEW*
;; Since a recent Blitz update you can now do what are called 'blitz arrays'.  These are very different to a Dim'd array, in the following ways:
;; They use square brackets [] instead of the normal round ones ().
;; You declare them like you do Local or Global variables, example: Local myArray[10]
;; They cannot be multi-dimensional, and cannot be resized.
;; They can be stored in Type objects.
;; They can be passed to functions.
;; -----------------------------------------------------------
;; See also: <a class=small href=Global.htm>Global</a>, <a class=small href=Local.htm>Local</a>.
;;param array_name - array name
;;param index1 - index number of last variable to be created within that particular  index-range
;;param index2 (optional) - index number of last variable to be created within that  particular index-range
;;param index3 (optional) - index number of last variable to be created within that  particular index-range
;;param ... (optional) - and so on
function Dim array_name(index1[,index2][,index3][,...])
; Dim Example  
; Create a collection of 100 random numbers  
; Create array  
Dim nums(99)  
; Fill each element with a random number  
For i = 0 to 99  
nums(i) = Rand(1,1000)  
Next  
end function
;; None.
;;param IP - integer IP address
function DottedIP$( IP )
; First call CountHostIPs (blank infers the local machine)  
n = CountHostIPs("")  
; n now contains the total number of known host machines.  
; Obtain the internal id for the IP address  
ip = HostIP(1)  
; Convert it to human readable IP address  
ipaddress$ = DottedIP$(ip)  
Print "Dotted IP Test"  
Print "=============="  
Print ""  
Print "Internal Host IP ID:" + ip  
Print "Dotted IP Address:" + ipaddress$  
Print ""  
Print "Press any key to continue"  
WaitKey()  
End  
end function
;; This is similar to the DrawImage command except  that any transparency or MaskImage is ignored and  the entire image (including masked colors) is drawn. The frame is optional.
;; See also: DrawImage
;;param image = variable of the image pointer
;;param x = x location to draw the image
;;param y = y location to draw the image
;;param frame = image's frame to draw (optional - default is 0)
function DrawBlock image, x,y [,frame]
; DrawBlock Example  
; Turn on graphics mode  
Graphics 640,480,16  
; Create new empty graphic to store our circle in  
gfxCircle=CreateImage(50,50)  
; Draw the circle image  
; Set drawing operations to point to our new empty graphic  
SetBuffer ImageBuffer(gfxCircle)  
Color 255,0,0  
; Note the extra space between the circle and the edge of the graphic  
Oval 10,10,30,30,1  
; Let's not forget to put the drawing buffer back!  
SetBuffer BackBuffer()  
; Set the CLS color to white  
ClsColor 255,255,255  
; Let the user move the circle graphic around a white screen  
; putting the graphic at the MouseX,Y coordinates  
While Not KeyHit(1)  
Cls  
DrawBlock gfxCircle,MouseX(),MouseY()  
Flip  
Wend  
end function
;; This command will let you draw a rectangular PORTION of an image to the  designated location on the screen. The transparent/masked portions of the original  image will be ignored and drawn with the image.
;; This is handy if you are doing something like revealing part of a screen when  the player uncovers something (think QIX). You could load the 'picture' into  an image, then when the player clears a rectangular region, you could paste  that portion of the final image onto the playfield. If you want to draw the  image portion with transparency or masking, use  DrawImageRect instead.
;;param image = variable holding the image handle
;;param x = x location on the screen to draw the image
;;param y = y location on the screen to draw the image
;;param rect_x = starting x location within the image to draw
;;param rect_y = starting y location within the image to draw
;;param rect_width = the height of the area to draw
;;param rect_height = the width of the area to draw
;;param frame = optional frame number of image to draw
function DrawBlockRect image,x,y,rect_x,rect_y,rect_width,rect_height,[frame]
; DrawBlockRect Example  
; Turn on graphics mode  
Graphics 640,480,16  
; Create new empty graphic to store our circle in  
gfxCircle=CreateImage(50,50)  
; Draw the circle image  
; Set drawing operations to point to our new empty graphic  
SetBuffer ImageBuffer(gfxCircle)  
Color 255,0,0  
; Note the extra space between the circle and the edge of the graphic  
Oval 10,10,30,30,1  
SetBuffer FrontBuffer()  
; Set the screen to white so you can see the transparent areas  
ClsColor 255,255,255  
Cls  
; Let's draw portions of the circle randomly  
While Not KeyHit(1)  
; We take random sized portions of the circle and put them  
; at a random location ... wash, rinse, and repeat  
DrawBlockRect gfxCircle,Rnd(640),Rnd(480),0,0,Rnd(50),Rnd(50),0  
Delay 100  
Wend  
end function
;; This command draws a previously loaded image. This command draws both single image graphics (loaded with the LoadImage  command) as well as animated images (loaded with the LoadAnimImage command).
;; You specify where on the screen you wish the image to appear. You can actually  'draw' off the screen as well by using negative values or positive values that  are not visible 'on the screen'.
;; Finally, if you are using an animated image (loaded with the LoadAnimImage), you can specify which frame  of the imagestrip is displayed with the DrawImage command.
;; One of the most common problems new Blitz programmers face when using drawing  commands is that even though they draw the graphic to the screen, they never  see it!
;; Remember, Blitz Basic is fast ... too fast for the human eye. You will have  to either continuously draw the image over and over again (like the way a cartoon  or TV works), clearing the screen each time a change is made (this is called  double buffering); or you will need to delay Blitz's execution long enough in  'human time' to let you SEE the picture. We will do the double buffering approach.
;; See also: DrawBlock
;;param image = variable image pointer previously designated
;;param x = the 'x' location of the screen to display the graphic
;;param y = the 'y' location of the screen to display the graphic
;;param frame = the frame number of the AnimImage to display (optional: default is 0)
function DrawImage image, x,y [,frame]
; LoadImage and DrawImage example  
; Declare a variable to hold the graphic file handle  
Global gfxPlayer  
; Set a graphics mode  
Graphics 640,480,16  
; Set drawing operations for double buffering  
SetBuffer BackBuffer()  
; Load the image and assign its file handle to the variable  
; - This assumes you have a graphic called player.bmp in the  
; same folder as this source code  
gfxPlayer=LoadImage("player.bmp")  
; Let's do a loop where the graphic is drawn wherever the  
; mouse is pointing. ESC will exit.  
While Not KeyHit(1)  
Cls ; clear the screen  
DrawImage gfxPlayer,MouseX(),MouseY() ; Draw the image!  
Flip ; flip the image into view and clear the back buffer  
Wend  
end function
;; This command will let you draw a rectangular PORTION of an image to the  designated location on the screen. The transparent/masked portions of the original  image will be drawn transparent, just as you normally would draw an image.
;; This is handy if you are doing something like revealing part of a screen when  the player uncovers something (think QIX). You could load the 'picture' into  an image, then when the player clears a rectangular region, you could paste  that portion of the final image onto the playfield. If you want to draw the  image portion with no transparency or mask, use  DrawBlockRect instead.
;;param image = variable holding the image handle
;;param x = x location on the screen to draw the image
;;param y = y location on the screen to draw the image
;;param rect_x = starting x location within the image to draw
;;param rect_y = starting y location within the image to draw
;;param rect_width = the height of the area to draw
;;param rect_height = the width of the area to draw
;;param frame = optional frame number of image to draw
function DrawImageRect image,x,y,rect_x,rect_y,rect_width,rect_height,[frame]
; DrawImageRect Example  
; Turn on graphics mode  
Graphics 640,480,16  
; Create new empty graphic to store our circle in  
gfxCircle=CreateImage(50,50)  
; Draw the circle image  
; Set drawing operations to point to our new empty graphic  
SetBuffer ImageBuffer(gfxCircle)  
Color 255,0,0  
; Note the extra space between the circle and the edge of the graphic  
Oval 10,10,30,30,1  
SetBuffer FrontBuffer()  
; Let's draw portions of the circle randomly  
While Not KeyHit(1)  
; We take random sized portions of the circle and put them  
; at a random location ... wash, rinse, and repeat  
DrawImageRect gfxCircle,Rnd(640),Rnd(480),0,0,Rnd(50),Rnd(50),0  
Delay 100  
Wend  
end function
;; Viewport and origin are not taken into account, and the movie must be positioned entirely 'on screen', otherwsie nothing will be drawn.
;; Movie support relies on DirectShow, so you will need to ensure the correct drivers are installed.  Movies will typically playback fastest at their natural size.
;; When playing MPEG or AVI files, the movie is played back at normal speed 'behind the scenes', and DrawMovie simply draws the most recent frame.  For animated GIFs, the DrawMovie command simply draws the next frame of the animation each time it is called, so you need to manage your animation timing manually.  See the example below for details.
;; See also: <a class=small href=OpenMovie.htm>OpenMovie</a>, <a class=small href=CloseMovie.htm>CloseMovie</a>, <a class=small href=MoviePlaying.htm>MoviePlaying</a>, <a class=small href=MovieWidth.htm>MovieWidth</a>, <a class=small href=MovieHeight.htm>MovieHeight</a>.
;;param movie - movie handle
;;param x (optional) - position of top left point of movie.  Defaults to 0.
;;param y (optional) - position of top left point of movie.  Defaults to 0.
;;param width (optional) - width of movie. Defaults to movie width.
;;param height (optional) - height of movie. Defaults to movie height.
function DrawMovie movie,[x,y][,width,height]
; Movie Commands Example  
; ======================  
; This demonstrates the following commands:  
;  
;	OpenMovie  
;	MovieHeight  
;	MovieWidth  
;	MoviePlaying  
;	DrawMovie  
; Some constants to start with  
Const WIDTH = 640  
Const HEIGHT = 480  
; First of all, set up the graphics  
Graphics WIDTH, HEIGHT  
SetBuffer BackBuffer()  
ClsColor 0,0,0  
Color 0,255,0  
; Next, open the movie file.  Feel free to change this to an AVI or MPEG file.  
movie=OpenMovie("media/hat.gif")  
; check to see if it loaded okay  
If movie=0 Then RuntimeError "Error - Movie not loaded!"  
If Not(MoviePlaying(movie)) Then RuntimeError "Error - Movie not playing!"  
;Now determine the size of the movie  
w=MovieWidth(movie)     ; the width of the movie  
h=MovieHeight(movie)    ; the height of the movie  
; Now set up the starting position and timing variables  
x=(WIDTH-w)/2           ; the x position of the movie on screen  
y=(HEIGHT-h-100)/2      ; the y position of the movie on screen  
period=100              ; the interval between frames  
time=MilliSecs()        ; the time of the last frame update  
; And here's the main loop  
Repeat  
; Wait for the specified period  
; GIFs have no timing info, and as such will redraw the next frame on each call to DrawMovie.  
; AVIs and MPEGs do have timing info, and as such will redraw the most recent frame on each call to DrawMovie.  
; Ergo, this time limiter only has an impact, and is only required for GIFs.  
Repeat  
; do nothing  
Until MilliSecs()-time>=period  
time=MilliSecs()    ; save the current time for the next frame  
; Handle keyboard inputs  
; CONTROL adjusts the speed with which we do stuff  
If KeyDown(29) Or KeyDown(157) Then  
change=5  
Else  
change=1  
End If  
; SHIFT means we're dealing with the size  
If KeyDown(42) Or KeyDown(54) Then  
If KeyDown(203) And w>change-1 Then w=w-change  
If KeyDown(205) And x+w+change < WIDTH Then w=w+change  
If KeyDown(200) And h>change-1 Then h=h-change  
If KeyDown(208) And y+h+change < HEIGHT Then h=h+change  
Else  
; otherwise it's the position that we're changing  
If KeyDown(203) And x>change-1 Then x=x-change  
If KeyDown(205) And x+w+change < WIDTH Then x=x+change  
If KeyDown(200) And y>change-1 Then y=y-change  
If KeyDown(208) And y+h+change < HEIGHT Then y=y+change  
EndIf  
; +/- to change the animation speed  
If ( KeyDown(13) Or KeyDown(78) ) And period>change Then period=period-change  
If ( KeyDown(12) Or KeyDown(74) ) And period < 500 Then period=period+change  
; Redraw the screen, by...  
Cls                         ; clear the screen  
DrawMovie movie,x,y,w,h     ; draw the movie  
; draw the instructions  
Text 0,0,"Use the arrow keys to reposition the movie."  
Text 0,20,"Hold SHIFT with the arrow keys to resize."  
Text 0,40,"Use + or - or control the animation speed."  
Text 0,60,"Hold CONTROL to resize, move, or change speed faster."  
Text 0,80,"Press ESCAPE to exit."  
Text 0,100,"Current Command Syntax: DrawMovie(movie, " + x + ","+ y + "," + w + "," + h + ")"  
; Flip the buffers  
Flip  
Until KeyHit(1) ; Escape to exit  
; Remove the movie from memory before closing down  
CloseMovie(movie)  
End ; bye!  
end function
;; If you haven't read up on the TYPE command, you might  want to do so before continuing.
;; The For .. Each loop allows you to walk through each object in the Type collection.  This is perfect for updating a large group of objects (such as a group of alien  invaders). Do look over the Type command.
;; See also: <a class=small href=Type.htm>Type</a>, <a class=small href=New.htm>New</a>, <a class=small href=Before.htm>Before</a>, <a class=small href=After.htm>After</a>, <a class=small href=First.htm>First</a>, <a class=small href=Last.htm>Last</a>, <a class=small href=Insert.htm>Insert</a>, <a class=small href=Delete.htm>Delete</a>.
;;param type_variable = A previously declared TYPE object
function Each type_variable
; Move them all over 1 (like the description example from TYPE command)  
For room.chair = Each chair  
room\x = room\x + 1  
Next  
end function
;; During a standard IF ... THEN conditional structure, you may wish to check another condition if the original condition fails. This 'nested IF' situation can get WAY out of hand, and once you have more than two nested conditionals, you should consider a SELECT/CASE structure. See the example.
;; See also: <a class=small href=ElseIf.htm>ElseIf</a>.
;;param None.
function Else If
; ELSEIF Example  
; Input the user's name  
name$=Input$("What is your name? ")  
; Doesn't the person's name equal SHANE?  
If name$ = "Shane" Then  
Print "You are recognized, Shane! Welcome!"  
ElseIf name$="Ron" Then  
Print "You are recognized too, Ron! Welcome!"  
Else  
Print "Sorry, you don't belong here!"  
; End of the condition checking  
End If  
end function
;; There are times during an IF ...  THEN conditional that you will want code to execute in the event that the  conditional is NOT met. The ELSE command begins that block of code, and is terminated  by the END IF command. See example.
;; See also: <a class=small href=If.htm>If</a>, <a class=small href=Then.htm>Then</a>, <a class=small href=ElseIf.htm>ElseIf</a>, <a class=small href=EndIf.htm>EndIf</a>, <a class=small href=Select.htm>Select</a>.
;;param None.
function Else
; ELSE example  
name$=Input$("What is your name?")  
If name$="Shane" then  
Print "Hello Shane!"  
Else  
Print "I have NO clue who you are!"  
End If  
end function
;; During a standard IF ... THEN  conditional structure, you may wish to check another condition if the original  condition fails. This 'nested IF' situation can get WAY out of hand, and once  you have more than two nested conditionals, you should consider aSELECT/CASE structure. See the  example.
;; See also: <a class=small href=If.htm>If</a>, <a class=small href=Then.htm>Then</a>, <a class=small href=Else.htm>Else</a>, <a class=small href=ElseIf.htm>ElseIf</a>, <a class=small href=EndIf.htm>EndIf</a>, <a class=small href=Select.htm>Select</a>.
;;param None
function ElseIf
; ELSEIF Example  
; Input the user's name  
name$=Input$("What is your name? ")  
; Doesn't the person's name equal SHANE?  
If name$ = "Shane" Then  
Print "You are recognized, Shane! Welcome!"  
ElseIf name$="Ron" Then  
Print "You are recognized too, Ron! Welcome!"  
Else  
Print "Sorry, you don't belong here!"  
; End of the condition checking  
End If  
end function
;; This line terminates a FUNCTION structure. Upon  reaching this line, Blitz will branch program execution to the next command  following the original call to the function. See the  FUNCTION command for more information.
;;param None.
function End Function
; End Function Example  
; Get the user's name  
name$=Input$("Enter Your Name:")  
; Call a function to print how many letters the name has  
numletters(name$);  
;The program basically ends here, because functions don't run unless called.  
; The actual function  
Function numletters(passedname$)  
Print "Your name has " + Len(passedname$) + " letters in it."  
End Function  
end function
;; END IF signals the end of an IF THEN condition check. See IF for more information. You can also use ENDIF as a single  word command - it functions identically.
;;param None.
function End If
; IF THEN Example  
; Input the user's name  
name$=Input$("What is your name? ")  
; Doesn't the person's name equal SHANE?  
If name$ = "Shane" Then  
Print "You are recognized, Shane! Welcome!"  
Else  
Print "Sorry, you don't belong here!"  
; End of the condition checking  
End If  
end function
;; This command ends the SELECT structure. If you  are using DEFAULT commands, this is the ending point  of that command set's execution. See SELECT, CASE, and DEFAULT for more  information.
;;param None.
function End Select
; SELECT/CASE/DEFAULT/END SELECT Example  
; Assign a random number 1-10  
mission=Rnd(1,10)  
; Start the selection process based on the value of 'mission' variable  
Select mission  
; Is mission = 1?  
Case 1  
Print "Your mission is to get the plutonium and get out alive!"  
; Is mission = 2?  
Case 2  
Print "Your mission is to destroy all enemies!"  
; Is mission = 3?  
Case 3  
Print "Your mission is to steal the enemy building plans!"  
; What do do if none of the cases match the value of mission  
Default  
Print "Missions 4-10 are not available yet!"  
; End the selection process  
End Select  
end function
;; If you haven't read up on the TYPE command, you might  want to do so before continuing.
;; After assigning all the Field variables in your Type  object, use this to finish the creation of the Type.
;;param None
function End Type
; Define the CHAIR Type  
Type CHAIR  
Field X  
Field Y  
Field HEIGHT  
End Type  
end function
;; Use this command to stop your program running. You will be returned to the editor if running from there, or the program will completely exit if running  a compiled EXE version.
;; See also: <a class=small href=Stop.htm>Stop</a>.
;;param None
function End
; If the game is over, then quit  
if gameOver=1 then End  
end function
;; Returns a program to a non-Graphics mode state. This is the same state that a program starts up in, before the Graphics command is used.
;; One possible use for EndGraphics is to switch between full-screen and windowed states.
;; See also: <a class=small href=Graphics.htm>Graphics</a>.
;;param None.
function EndGraphics
Graphics 640,480,0,1  
SetBuffer BackBuffer()  
Text 0,0,"EndGraphics Example Stage 1/2"  
Text 0,20,"Currently in Graphics mode"  
Text 0,40,"Press a key to deactive Graphics mode with EndGraphics command"  
Flip  
WaitKey()  
EndGraphics  
Print "EndGraphics Example Stage 2/2"  
Print "Currently in non-Graphics mode"  
WaitKey()  
End  
end function
;; Terminates an IF ... THEN condition  structure. See END IF for more information.
;; See also: <a class=small href=If.htm>If</a>, <a class=small href=Then.htm>Then</a>, <a class=small href=Else.htm>Else</a>, <a class=small href=ElseIf.htm>ElseIf</a>, <a class=small href=Select.htm>Select</a>.
;;param None.
function EndIf
; IF THEN Example  
; Input the user's name  
name$=Input$("What is your name? ")  
; Doesn't the person's name equal SHANE?  
If name$ = "Shane" Then  
Print "You are recognized, Shane! Welcome!"  
Else  
Print "Sorry, you don't belong here!"  
; End of the condition checking  
EndIf  
end function
;; Checks to see if the End of File of an opened file or stream has been reached.  Use this to determine if you should continue to pull more information from a  file/stream or not. Use this to read a text file of unknown length (say a README.TXT)  and display it. See example.
;; Eof returns 1 if eof has been reached or, in the case of a TCP stream, the stream  has been 'nicely' closed.
;; Eof returns -1 if something has gone wrong during stream processing.
;; Streams can only be used in Blitz Basic v1.52 or greater.
;;param filehandle/stream = a valid variable set with the OpenFile, ReadFile command,  or OpenTCPStream (v1.52+)
function Eof (filehandle/stream)
; Eof sample  
file$="c:autoexec.bat"  
filein = ReadFile(file$)  
Print "Here is your Autoexec.bat file ..."  
; Loop this until we reach the end of file  
While Not Eof(filein)  
Print ReadLine$(filein)  
Wend  
end function
;; The usefulness of this command is really mostly for calling some system level command, launching a browser, etc.
;; Note: This command uses ShellExecute to allow you to 'open' any file (like a  .doc or .txt) file with its default associated program.
;; Also: if the filename path has spaces in it you may will need to chenge the call like so:
;; ExecFile (Chr$(34)+"example file name.exe"+Chr$(34))
;; This adds the " (quotation mark) character to the beginning and end of the text string.
;;param file$ - the file to be executed
function ExecFile( file$ )
; ExecFile sample - RUN THIS WINDOWED!  
Graphics 320,240,0,2  
filename$ = Chr$(34) + "calc.exe" + Chr$(34)  
Text 5,5, "press any key to run CALC.EXE!"  
Flip  
WaitKey  
ExecFile(filename$)  
Text 5,25, "Press any key to quit."  
Flip  
WaitKey  
End  
end function
;; This command will allow you to leave a For .. Next loop as well as many other types of loops prematurely  (assuming a condition was met or other planned event). It exits the IMMEDIATE  loop - you'll need one for each 'nest' of loops you want to exit from.
;; See also: <a class=small href=For.htm>For</a>, <a class=small href=While.htm>While</a>, <a class=small href=Repeat.htm>Repeat</a>.
;;param None
function Exit
; EXIT Command sample  
For t = 1 To 100  
Print t  
If t = 50 Then Exit  
Next  
end function
;; This is e^x where e = 2.71828...
;; For the curious, e is defined by the infinite sum:
;; 2 + 1/(2) + 1/(2*3) + 1/(2*3*4) + 1/(2*3*4*5) + ...
;; See also Log()
;;param x = any number.
function Exp# ( x# )
; Exp example  
; Exp(x) is the same as e^x.  
; Actually, due to the approximate nature of floating point arithmetic  
; these will not be exactly equal. But in the following example  
; the difference is so small you can't see it.  
e# = 2.71828182845905  ; over-specified, might help and can't hurt.  
x# = .125  ; = 1/8  
Print "   x        Exp(x)       e^x  "  
Print "=======   =========   ========="  
For k = 1 To 7  
Write " " + LSet( x , 7 )  
Write RSet( Exp( x ), 10 )  
Print RSet( e^x , 12 )  
x = 2 * x  
Next  
WaitKey() : End  
end function
;; FALSE is a keyword to denote a negative result in a conditional statement.  Often times, FALSE is implied and doesn't need to be directly referenced - or  a NOT command is used in the comparison. FALSE can also  be used as a RETURN value from aFUNCTION. Also see the TRUE  command. See the example.
;; See also: <a class=small href=True.htm>True</a>, <a class=small href=If.htm>If</a>, <a class=small href=Select.htm>Select</a>, <a class=small href=While.htm>While</a>, <a class=small href=Repeat.htm>Repeat</a>.
;;param None.
function False
; FALSE example  
; Assign test a random number of 0 or 1  
test= Rnd(0,1)  
; FALSE is implied because of the NOT  
If not test=1 Then  
Print "Test was valued at 0"  
End If  
; Let's set test to be false  
test=False  
; Pointlessly test it  
If test=False Then  
Print "Test is false"  
else  
print "Test is true"  
End If  
end function
;; If you haven't read up on the TYPE command, you might  want to do so before continuing.
;; When you define a custom Type, you need to assign some variables to track  within in. Using the Field command within the Type .. End Type commands, you set a variable that  can be used for EACH object created with the NEW command.
;; See also: <a class=small href=Type.htm>Type</a>, <a class=small href=End Type.htm>End Type</a>.
;;param variable = any legal variable
function Field variable
; Define the CHAIR Type  
Type CHAIR  
Field X  
Field Y  
Field HEIGHT  
End Type  
; Create 100 new chairs using FOR ... NEXT using the collection name of ROOM  
For tempx = 1 to 10  
For tempy = 1 to 10  
room.chair = New Chair  
room\x = tempx  
room\y = tempy  
room\height = Rnd(0,10) ; set a random height 0 to 10  
Next  
Next  
; Move them all over 1 (like the description example from TYPE command)  
For room.chair = Each chair  
room\x = room\x + 1  
Next  
end function
;; This command returns the current position within a file that is being processed  following ReadFile, WriteFile or OpenFile. The returned integer is the offsets  in bytes from the start of the file to the current read/write position. Note,  this is zero when pointing to the first byte of the file.
;; By using FilePos and SeekFile the position within the file that is being read  or written can be determined and also changed. This allows a file to be read  and updated without having to make a new copy of the file or working through  the whole file sequentially. This could be useful if you have created a database  file and you want to find and update just a few records within it. It is also  possible to create an index file that contains pointers to where each record  starts in a data file.
;;param filehandle = the variable returned by the Readfile WriteFile or OpenFile  when the file was opened. The value returned is the offset from the start of  the file. ( 0 = Start of the file )
function FilePos (filehandle)
; Changing part of a file using OpenFile, SeekFile, FilePos  
; note FilePos is used in the SearchFile function at the end of this example  
; Open/create a file to Write  
fileout = WriteFile("mydata.dat")  
; Write the information to the file  
WriteInt( fileout, 1 )  
WriteInt( fileout, 2 )  
WriteInt( fileout, 3 )  
WriteInt( fileout, 4 )  
WriteInt( fileout, 5 )  
; Close the file  
CloseFile( fileout )  
DisplayFile( "The file as originally written", mydata.dat" )  
Position = SearchFile( 4 , "mydata.dat" )  
Write "Value 4 was found "  
Write Position  
Print " bytes from the start."  
Print  
; Open the file and change the value 3 to 9999  
file = OpenFile("mydata.dat")  
SeekFile( file, Position ) ; Move to the found location  
WriteInt( file, 9999 ) ; Replace the original value with 9999  
CloseFile( file )  
DisplayFile( "The file after being modified", "mydata.dat" )  
WaitKey()  
End ; End of program  
; **** Function Definitions follow ****  
; Read the file and print it  
Function DisplayFile( Tittle$, Filename$ )  
Print tittle$  
file = ReadFile( Filename$ )  
While Not Eof( file )  
Number = ReadInt( file )  
Print Number  
Wend  
CloseFile( file )  
Print  
End Function  
; Search a file of integers for the Wanted data value  
; Note the need to subtract 4 from the location since having read it  
; we are now pointing at the next Integer also Return() was placed  
; after closing the file so it is closed properly  
Function SearchFile( Wanted, Filename$ )  
file = ReadFile( Filename$ )  
While Not Eof( file )  
If ReadInt( file ) = Wanted Then Location = FilePos( file ) - 4  
Wend  
CloseFile( file )  
Return( Location )  
End Function  
end function
;; Often it will be useful to return the size of a file. File size is important  for copying, reading, and other file evolutions.
;;param filename$ = any valid variable with path/filename
function FileSize (filename$)
; Windows 2000/XP users will need to change location of calc.exe  
filename$="c:\windows\calc.exe"  
Print "The size of the file is: " + FileSize(filename$)  
Print "Press any key to quit."  
WaitKey()  
end function
;; This command checks the filename you pass and determines if it exists and  whether or not it is a valid filename or if it is a directory. Here are the  values it returns:
;; 1 = The filename exists
;; 0 = The filename doesn't exist
;; 2 = The filename is not a file - but a directory
;; Use this to validate that a file exists before you do something to it.
;;param filename$ = any valid variable with path/filename
function FileType (filename$)
; Windows 9x users will need to change location of calc.exe  
filename$="c:winntsystem32calc.exe"  
if fileType(filename$)=1 then Print "The file exists!"  
if fileType(filename$)=0 then Print "File not found!"  
if fileType(filename$)=2 then Print "This is a directory!"  
Print "Press any key to quit."  
WaitKey()  
end function

end function
;; If you haven't read up on the TYPE command, you might  want to do so before continuing.
;; Use this to assign a custom Type object to the first object in the collection.  See the example.
;; See also: <a class=small href=Type.htm>Type</a>, <a class=small href=New.htm>New</a>, <a class=small href=Before.htm>Before</a>, <a class=small href=After.htm>After</a>, <a class=small href=Last.htm>Last</a>, <a class=small href=Each.htm>Each</a>, <a class=small href=Insert.htm>Insert</a>, <a class=small href=Delete.htm>Delete</a>.
;;param type_variable = the actual Type name, not the custom Type name
function First type_variable
; Define a crafts Type  
Type crafts  
Field x  
Field y  
Field dead  
Field graphic  
End Type  
; Create 100 crafts, with the unique name of alien  
For t = 1 To 100  
alien.crafts = New crafts  
alien\x = Rnd(0,640)  
alien\y = Rnd(0,480)  
alien\dead = 0  
alien\graphic = 1  
Next  
; Move to the first object  
alien.crafts = First crafts  
Print alien\x  
Print alien\y  
Print alien\dead  
Print alien\graphic  
; move to the next alien object  
alien = After alien  
Print alien\x  
Print alien\y  
Print alien\dead  
Print alien\graphic  
; move to the last alien object  
alien.crafts = Last crafts  
Print alien\x  
Print alien\y  
Print alien\dead  
Print alien\graphic  
; move to the second to the last alien object  
alien = Before alien  
Print alien\x  
Print alien\y  
Print alien\dead  
Print alien\graphic  
end function
;; Flip will switch the FrontBuffer() and BackBuffer().
;; This command should be used when you are using Double Buffering.  Double Buffering is a technique used to ensure that screen updates are not visible to the user.  If you draw directly to the FrontBuffer, the display may appear flickery as the updates are drawn directly to the screen.  If you draw to the BackBuffer, the updates are drawn in memory.  Flip is then used to make the BackBuffer the FrontBuffer, and hence show the updates on screen in one go.  At the same time, the FrontBuffer becomes the BackBuffer, allowing you to draw the next screen update on the BackBuffer before Flipping again.
;; The vwait parameter defines whether or not the graphics card will wait for the vertical blank before flipping the screen.  The vertical blank is an event that occurs at the frequency of the monitors refresh rate, and corresponds to the point in time when the last line on the screen has been drawn, and the top line is about to be drawn by the monitor.  By waiting until the vertical blank, you ensure smooth updates to the screen as the image being drawn by the monitor will change only once the entire screen has been drawn by the monitor, and the screen is just about to start being refreshed.
;; However, it is worth noting that this setting applies to the graphics card only, and some graphics cards allow the user to disable the vertical blank event.  Therefore, if you rely on Flip alone, the display may be corrupted on certain setups.  The VWait command may be useful to you in  this respect, as it forces the CPU to wait for the vertical blank (as opposed to the graphics card), and this cannot be disabled.  Hence true silky smooth updates are best achieved using "VWait : Flip False".
;; See also: <a class=small href=FrontBuffer.htm>FrontBuffer</a>, <a class=small href=BackBuffer.htm>BackBuffer</a>, <a class=small href=VWait.htm>VWait</a>, <a class=small href=ScanLine.htm>ScanLine</a>.
;;param vwait = set to FALSE to disable frame syncing, defaults to TRUE which waits for vertical blank to finish
function Flip [vwait]
; Flip/Backbuffer()/Rect Example  
; Set Graphics Mode  
Graphics 640,480  
; Go double buffering  
SetBuffer BackBuffer()  
; Setup initial locations for the box  
box_x = -20 ; negative so it will start OFF screen  
box_y = 100  
While Not KeyHit(1)  
Cls ; Always clear screen first  
Rect box_x,box_y,20,20,1 ; Draw the box in the current x,y location  
Flip ; Flip it into view  
box_x = box_x + 1 ; Move the box over one pixel  
If box_x = 640 Then box_x=-20 ; If it leaves the Right edge, reset its x location  
Wend  
end function
;; This is the same as Blitz's automatic type conversion.
;; So the two commands...
;; y# = value
;; y# = Float( value )
;; ... do exactly the same thing.
;; If Float is applied to a string it converts as much as possible:
;; Float( "10" ) ...... result is 10.0
;; Float( "3junk" ) .. result is 3.0
;; Float( "junk3" ) .. result is 0.0
;; In the latter two examples Float() stops converting when it sees "j".
;; The typical use is to force floating point arithmetic:
;; y# = 12 / 5 .............. result is 2.0 because 12 / 5 = 2 ( integer division )
;; y# = float( 12 ) / 5 .... result is 2.4
;;param value = a number, or a string which represents a number
function Float#( value )
; Float example  
; =============  
Print "Enter some text to be converted using Float()."  
Print "Hit ENTER to exit."  
Repeat  
s$ = Input$(">")  
If s$<>"" Then  
Print "Float("+Chr$(34)+s$+Chr$(34)+")=" + Float(s$)  
End If  
Until s$=""  
End  
end function
;; Rounds downward, i.e. in the direction of -Infinity.
;; It is a common mistake to think this simply sets everything after the decimal point to zero.
;; But this is true only for positive numbers:
;; Floor(  1.75 ) ....... 1.0
;; Floor( -1.75 ) .... -2.0
;; See also Ceil and Int for other types of rounding.
;;param y = any number
function Floor# ( y# )
; Ceil / Floor / Int example, three kinds of rounding.  
; Move mouse. Escape quits.  
Graphics 640, 480  
Const KEY_ESC = 1  
SetBuffer BackBuffer()  
Origin 320, 240  
MoveMouse 320, 240  :  HidePointer  
While Not KeyDown( KEY_ESC )  
Cls  
my = MouseY() - 240  
Color 100, 100, 0  
Line -320, my, 319, my  
DrawNumberLine  
y# = Float( -my ) / 32  
Text 100, 50, "          y = "  + y  
Text 100, 70, "  Ceil( y ) = "  + Ceil( y )  
Text 100, 90, " Floor( y ) = "  + Floor( y )  
Text 100, 110, "   Int( y ) = " + Int( y )  
Flip  
Wend  
End  
Function DrawNumberLine( )  ; vertical line with numeric labels  
Color 255, 255, 255  
Line 0, -240, 0, 239  
For n = -7 To 7  
yn = -32 * n  
Line -2, yn, 2, yn  
Text -30, yn - 6, RSet( n, 2 )  
Next  
End Function  
end function
;; There are many times when you aren't interested in the dozens of possible  joystick button pressed the player might have made before you are checking for  one in particular. Or perhaps you want to pause the game and wait for any joystick  button to be hit, but you don't want a 'queued' button press bypassing this.  Use this command before you specifically want to poll a joystick button hit  from the user.
;;param None.
function FlushJoy
; FlushJoy sample  
FlushJoy  
Print "Press a joystick button to exit!"  
WaitJoy()  
End  
end function
;; This command 'resets' or 'empties out' the queue holding the keyboard inputs.  Can't make it much easier than that.
;;param None.
function FlushKeys
; clear all keystrokes from the queue  
FlushKeys  
end function
;; There are many times when you aren't interested in the dozens of possible  mouse button pressed the player might have made before you are checking for  one in particular. Or perhaps you want to pause the game and wait for any mouse  button to be hit, but you don't want a 'queued' button press bypassing this.  Use this command before you specifically want to poll a mouse button hit from  the user.
;;param None.
function FlushMouse
; Flushmouse sample  
FlushMouse  
Print "Press a mouse button to exit!"  
WaitMouse()  
End  
end function
;; This returns the height, in pixels, of the currently selected font (using SetFont - previously loaded with LoadFont).
;;param None.
function FontHeight()
; FontWidth()/FontHeight example  
; enable graphics mode  
Graphics 800,600,16  
; Set global on font variable  
Global fntArial  
;Load fonts to a file handle variables  
fntArial=LoadFont("Arial",13,False,False,False)  
; set the font and print sizes  
SetFont fntArial  
Text 400,0,"The font width of the widest character is:"+ FontWidth(),True,False  
Text 400,30,"The height of the font is:"+ FontHeight(),True,False  
; Standard 'wait for ESC' from user  
While Not KeyHit(1)  
Wend  
; Clear all the fonts from memory!  
FreeFont fntArial  
end function
;; This returns the width, in pixels, of the currently selected font (using SetFont - previously loaded with LoadFont). This command returns the width of the  WIDEST character of the font.
;;param None.
function FontWidth()
; FontWidth()/FontHeight example  
; enable graphics mode  
Graphics 800,600,16  
; Set global on font variable  
Global fntArial  
;Load fonts to a file handle variables  
fntArial=LoadFont("Arial",13,False,False,False)  
; set the font and print sizes  
SetFont fntArial  
Text 400,0,"The font width of the widest character is:"+ FontWidth(),True,False  
Text 400,30,"The height of the font is:"+ FontHeight(),True,False  
; Standard 'wait for ESC' from user  
While Not KeyHit(1)  
Wend  
; Clear all the fonts from memory!  
FreeFont fntArial  
end function
;; The first command of the FOR ... NEXT loop, this command is used to assign  a variable to a range of numbers in sequence and execute a set of code that  many times. Using the STEP command allows you to skip a certain value between each loop of the code.  Note that the STEP amount cannot be a variable.
;; This is frequently used when a specific pattern of numbers is needed to perform  an evolution (moving something from point A to point B, adding a value to a  score incrementally, etc). This allows you to assign a variable with the current  value of the loop. See the example for more.
;; Note: Unlike many BASIC languages, the NEXT command does  NOT use the FOR command's variable as an identifier. If you have nested FOR  ... NEXT commands, the language will automatically match the NEXT with the nearest  FOR command.
;; See also: <a class=small href=To.htm>To</a>, <a class=small href=Step.htm>Step</a>, <a class=small href=Each.htm>Each</a>, <a class=small href=Next.htm>Next</a>, <a class=small href=Exit.htm>Exit</a>, <a class=small href=While.htm>While</a>, <a class=small href=Repeat.htm>Repeat</a>.
;;param variable = any valid variable name
function For variable
; Print the values 1 through 10  
For t = 1 To 10  
Print t  
Next  
; Print the values 1,3,5,7,9  
For t = 1 To 10 Step 2  
Print t  
Next  
end function
;; Replace the UNTIL command in aREPEAT ... UNTIL loop to make the loop run forever.  Remember, the program execution will continue indefinately until either you  break out of it programmatically! This is often known as 'the infinate loop'.  Once more for the cheap seats: "Make sure you provide a means for breaking out  of the loop". Use EXIT (to leave the loop) or END to quit the program.
;; See also: <a class=small href=Repeat.htm>Repeat</a>, <a class=small href=Until.htm>Until</a>, <a class=small href=Exit.htm>Exit</a>, <a class=small href=While.htm>While</a>, <a class=small href=For.htm>For</a>.
;;param None.
function Forever
; FOREVER Example  
Repeat  
If KeyHit(1) Then Exit  
Print "You are trapped in an infinate loop! Press ESC!"  
Forever  
Print "The infinate loop has ended!"  
end function
;; Frees a bank.
;; See also: <a class=small href=CreateBank.htm>CreateBank</a>.
;;param bank - bank handle
function FreeBank bank
; Bank Commands Example  
; ---------------------  
bnkTest=CreateBank(12)  
PokeByte bnkTest,0,Rand(255)  
PokeShort bnkTest,1,Rand(65535)  
PokeInt bnkTest,3,Rand(-2147483648,2147483647)  
PokeFloat bnkTest,7,0.5  
Print PeekByte(bnkTest,0)  
Print PeekShort(bnkTest,1)  
Print PeekInt(bnkTest,3)  
Print PeekFloat(bnkTest,7)  
FreeBank bnkTest  
end function
;; This removes a TrueType font previously loaded into memory (though the LoadFont command).
;; Note: Blitz doesn't work with SYMBOL fonts, like Webdings and WingDings.
;;param fonthandle = A handle to a previously loaded font.
function FreeFont fonthandle
; LoadFont/SetFont/FreeFont example  
; enable graphics mode  
Graphics 800,600,16  
; Set global on font variables  
Global fntArial,fntArialB,fntArialI,fntArialU  
;Load fonts to a file handle variables  
fntArial=LoadFont("Arial",24,False,False,False)  
fntArialB=LoadFont("Arial",18,True,False,False)  
fntArialI=LoadFont("Arial",32,False,True,False)  
fntArialU=LoadFont("Arial",14,False,False,True)  
; set the font and print text  
SetFont fntArial  
Text 400,0,"This is just plain Arial 24 point",True,False  
SetFont fntArialB  
Text 400,30,"This is bold Arial 18 point",True,False  
SetFont fntArialI  
Text 400,60,"This is italic Arial 32 point",True,False  
SetFont fntArialU  
Text 400,90,"This is underlined Arial 14 point",True,False  
; Standard 'wait for ESC' from user  
While Not KeyHit(1)  
Wend  
; Clear all the fonts from memory!  
FreeFont fntArial  
FreeFont fntArialB  
FreeFont fntArialI  
FreeFont fntArialU  
end function
;; When you are done with an image, use this command to delete the image from memory and free up that memory for other use. Good housekeeping. Get in the habit!
;; Note that the variable to the Image is not reset to 0 upon using FreeImage, nor will any other variables pointing to the same Image.  If you try to access the handle again, it will result in an error.  It is good practice to set the handle to zero immediately after freeing the image, as you can then check whether an image has already been freed or not.
;; See also: <a class=small href=LoadImage.htm>LoadImage</a>, <a class=small href=SaveImage.htm>SaveImage</a>.
;;param handle=variable that holds the image handle of a previously loaded image
function FreeImage handle
; FreeImage command  
; Global, as always, for graphics  
Global gfxPlayer  
; Enter graphics mode and start double buffering  
Graphics 640,480,16  
SetBuffer BackBuffer()  
; Load the image-assign the handle to gfxPlayer  
gfxPlayer=LoadImage("player.bmp")  
; draw the image at random until ESC pressed  
While Not KeyHit(1)  
Cls  
DrawImage gfxPlayer,Rnd(640),Rnd(480)  
Flip  
Wend  
; Erase the image from memory!  
FreeImage gfxPlayer  
end function
;; When you are finished using a sound effect, you should free up the memory  its using and delete the sound. This command will delete a sound instance variable  created with the LoadSound command.
;; Why would you want to do this? Perhaps you have different sets of sounds for  different levels of your game? Perhaps your music loop changes from level to  level. You want to do the RIGHT thing and manage your own resources. Just because  you CAN load every sample for the whole game at once, consider someone that  doesn't have as much memory as you do. You want to ensure that your game appeals  to the widest audience possible. Note: You don't have to manually free these  resources when your program terminates - Blitz does this automatically.
;;param sound_variable - and valid variable previously created with the LoadSound  command.
function FreeSound sound_variable
; Load a sound into memory  
sndOneUp=LoadSound("audio1up.wav")  
; Free the memory up and delete the sound  
FreeSound sndOneUp  
end function
;; This command will destroy a timer variable created with the with the CreateTimer command and free the memory it was  using. It is a good practice to destroy elements in your game you are no longer  using.
;;param timer = any valid timer variable created with the CreateTimer command.
function FreeTimer (timer_variable)
; Create the timer to track speed  
frameTimer=CreateTimer(60)  
; Your main screen draw loop  
While Not KeyHit(1)  
WaitTimer(frameTimer) ; Pause until the timer reaches 60  
Cls  
; Draw your screen stuff  
Flip  
Wend  
; Kill the timer  
FreeTimer(frameTimer)  
end function
;; It's important to understand buffers when writing a game.
;; What the player can see at any given time is usually the front buffer. Anything you draw to this buffer is IMMEDIATELY visible to the player. This sounds fast (and it is) but the problem is that when you are drawing to the front buffer - like a piece of paper and pencil - anything you draw on the screen overwrites anything else that exists in the same space. So, if you want to 'save' any portion of the screen from being overwritten by another drawing operation, YOU - the programmer - have to copy the area 'under' the location of the new operation  to an image so you can replace it later. Imagine taking a piece of paper with a picture of some mountains, and making an airplane pass in front of them, inch by inch. Every time the plane moves, you have to draw the new area that will be under the plane next on another sheet of paper (so you know what it looked  like) then draw the plane over the new place. Next time you move, you will repeat  this, then draw the image back in the OLD plane location. This process is labor-intensive  and largely unnecessary thanks to a process called DOUBLE BUFFERING (see BackBuffer(). Double buffering is used for pretty  much all games for high-action with lots of objects on the screen.
;; So, if double buffering rocks so much, why would you WANT to ever draw to the front buffer? Sometimes, you just want to draw stuff to the screen, without caring what you overwrite. You don't have to worry about redrawing the screen over and over again in double buffering in this case. Just set the buffer to FrontBuffer() and you can write directly to the screen in real time.
;;param None
function FrontBuffer()
; FrontBuffer()/Rect Example  
; Engage graphics mode  
Graphics 640,480,16  
; Set the drawing buffer to front - instant drawing ops!  
SetBuffer FrontBuffer()  
; Repeat this until user presses ESC  
While Not KeyHit(1)  
; Set a random color  
Color Rnd(255),Rnd(255),Rnd(255)  
; Draw a rectangle at a random location, with a random width and height  
; plus randomly choose if the rectangle is solid or just an outline  
Rect Rnd(640),Rnd(480),Rnd(50),Rnd(50),Rnd(0,1)  
; Blitz is so dang fast, we need a delay so you can watch it draw!  
Delay 10  
Wend  
end function
;; A function is a routine of commands you may choose to call frequently within  your program. Functions are considered 'proper' practice in this situation,  instead of using GOSUB commands.
;; Functions are independant of your 'main code' and will only execute if they  are called. They have their own namespace, and variables created outside a function  are NOT available within the function (this goes for TYPE  structures as well) unless you declare the variable or Type structure as GLOBAL.
;; You can pass variables into functions, as well as RETURN  values back to the calling code. To return floats or strings from a function ensure your function name has a # or $ suffix.
;; The practical use of functions is to help seperate your code into managable  chunks for subsequent perfection of a routine. You may put all your screen updates  into a single function, for example. Or perhaps your scoring system where you  pass it what alien has been destroyed and it updates the player's score.
;; Once the function reaches the END FUNCTION command, it returns program execution to the point immediately following the function call.
;; Functions can be a bit daunting until you realize they really are their own  little programs within your program. See the example for more.
;; Note that if you want to be really clever, you can replace Blitz functions with your own function.  This can be useful if you wish to add some error handling or additional processing around every instance of a Blitz function.  Note that once you have replaced a Blitz function, you cannot call the real function - for example you cannot wrap a LoadImage command within a LoadImage function as it will recursively call itself!
;; See also: <a class=small href=End Function.htm>End Function</a>, <a class=small href=Return.htm>Return</a>, <a class=small href=Gosub.htm>Gosub</a>, <a class=small href=Goto.htm>Goto</a>.
;;param name = any valid name text that is not a keyword of Blitz.
function Function name
; Function Example  
; Get the user's name  
name$=Input$("Enter Your Name:")  
; Call a function to print how many letters the name has  
numletters(name$);  
; Let's get something BACK from the function  
thefirst$=firstletter(name$)  
; Now print results  
Print "Was the first letter an 'S'? (1=True/0=False)" + thefirst$  
;The program basically ends here, because functions don't run unless called.  
; The actual function  
Function numletters(passedname$)  
Print "Your name has " + Len(passedname$) + " letters in it."  
End Function  
; Function to see if the first letter is S  
Function firstletter(passedname$)  
; If the first letter is an 'S' then return from the function a true value  
If Left$(passedname$,1) = "S" Then  
Return True  
; Otherwise, return false  
Else  
Return False  
End If  
End Function  
end function
;; Returns the current output blue component of the gamma tables for the specified input blue component.
;; Click <a href=http://www.blitzbasic.co.nz/b3ddocs/command.php?name=GammaBlue&ref=comments target=_blank>here</a> to view the latest version of this page online</body>
;; </html>
;;param blue - an integer color component in the range 0 to 255.
function GammaBlue ( blue )
end function
;; Returns the current output green component of the gamma tables for the specified input green component.
;; Click <a href=http://www.blitzbasic.co.nz/b3ddocs/command.php?name=GammaGreen&ref=comments target=_blank>here</a> to view the latest version of this page online</body>
;; </html>
;;param green - an integer color component in the range 0 to 255.
function GammaGreen ( green )
end function
;; Returns the current output red component of the gamma tables for the specified input red component.
;; Click <a href=http://www.blitzbasic.co.nz/b3ddocs/command.php?name=GammaRed&ref=comments target=_blank>here</a> to view the latest version of this page online</body>
;; </html>
;;param red - an integer color component in the range 0 to 255.
function GammaRed ( red )
end function
;; This command works like a 'color picker' in your favorite paint program.  By specifying
;;param x = x coordinate of pixel
;;param y = y coordinate of pixel
function GetColor x, y
; GetColor Example  
Graphics 320,200  
SetBuffer BackBuffer()  
For t = 1 To 1000  
Color Rnd(255), Rnd(255), Rnd(255)  
Rect Rnd(320),Rnd(200),10,10,1  
Next  
GetColor 100,100  
Print "Box at 100,100 is RGB:" + ColorRed() + "," + ColorGreen() + "," + ColorBlue()  + "!"  
Flip  
While Not KeyHit(1)  
Wend  
end function
;; Returns the value of the specified environment variable.
;; Unlike the SetEnv command, which is only capable of setting environment variables local to Blitz, the GetEnv command is capable of getting Windows environment variables.
;; Windows environment variables tell your computer what kind of machine it is, and where to install programs.
;; If you're curious to find out what environment variables are set on your Windows install, then you can find out in WindowsXP by running 'cmd', and then typing 'set'.
;; See also: SetEnv
;;param env_var$ - the name of the environment variable to 'get'
function GetEnv$ ( env_var$ )
; GetEnv Example  
; --------------  
Print "PROCESSOR_ARCHITECTURE: "+GetEnv$("PROCESSOR_ARCHITECTURE")  
Print "ProgramFiles: "+GetEnv$("ProgramFiles")  
Print "SystemDrive: "+GetEnv$("SystemDrive")  
Print "TEMP: "+GetEnv$("TEMP")  
WaitKey()  
end function
;; Unlike the other similar commands (JoyDown and JoyHit), this command doesn't need to know which button  you are trying to test for. It looks for any joystick button, then returns the  number the user pressed. Since you are polling all the buttons instead of just  a specific one, this may be a tad less efficient than using JoyDown or JoyHit.  Use this command in conjunction with Select/Case  for maximum efficiency!
;;param port = optional joystick port to read
function GetJoy ([port])
; GetJoy Example  
While Not KeyHit(1)  
button=GetJoy()  
If button <> 0 Then  
Print "You pressed joystick button #" + button  
End If  
Wend  
end function
;; This command will check to see if a key has been pressed and will return  its ASCII value. Not all keys have ASCII values - if you need to trap SHIFT,  ALT, or other non-ASCII compliant key, try KeyHit or KeyDown.
;;param None
function GetKey()
; GetKey Example  
Print "Please press any ASCII key ..."  
While Not value  
value=GetKey()  
Wend  
Print "You pressed key with an ASCII value of:" + value  
end function
;; Unlike the other similar commands (MouseDown  and MouseHit), this command doesn't need to know  which button you are trying to test for. It looks for any mouse button, then  returns the number the user clicked. Since you are polling all the mouse buttons  instead of just a specific one, this may be a tad less efficient than using  MouseDown or MouseHit. Use this command in conjunction with Select/Case for maximum efficiency!
;;param None.
function GetMouse()
; GetMouse Example  
While Not KeyHit(1)  
button=GetMouse()  
If button <> 0 Then  
Print "You pressed mouse button #" + button  
End If  
Wend  
end function
;; Some computers may have more than one video card and/or video driver installed  (a good example is a computer system with a primary video card and a Voodoo2  or other pass-through card).
;; Once you know how many drivers there are using CountGfxDrivers(), you can iterate through  them with this command and display them for the user to choose from. Once the  user has chosen (or you decide), you can set the graphics driver with SetGfxDriver.
;; Normally, this won't be necessary with 2D programming.
;;param index = index number obtained with CountGfxDrivers command
function GfxDriverName$ (index)
; GfxDriver Examples  
; Count how many drivers there are  
totalDrivers=CountGfxDrivers()  
Print "Choose a driver to use:"  
; Go through them all and print their names (most people will have only 1)  
For t = 1 To totalDrivers  
Print t+") " + GfxDriverName$(t)  
Next  
; Let the user choose one  
driver=Input("Enter Selection:")  
; Set the driver!  
SetGfxDriver driver  
Print "Your driver has been selected!"  
end function
;; Once you determine the video modes available by the video card using CountGFXModes(), you can iterate through them  and determine the width, height, and color depth capabilities of each mode.  Use this command to get the color depth of the mode. Use the GFXModeWidth and  GFXModeHeight to get the remaining parameters.
;;param None.
function GFXModeDepth (mode)
; CountGFXModes()/GfxModeWidth/GfxModeHeight/GfxModeDepth example  
intModes=CountGfxModes()  
Print "There are " + intModes + "graphic modes available:"  
; Display all modes including width, height, and color depth  
For t = 1 To intModes  
Print "Mode " + t + ":  
Print "Width=" + GfxModeWidth(t)  
Print "Height=" + GfxModeHeight(t)  
Print "Height=" + GfxModeDepth(t)  
Next  
end function
;; Use this command to verify whether or not the user's video card can use  this graphic mode. Returns TRUE if the mode exists, FALSE if not. If you want  to know what mode number this mode is, use FindGFXMode.
;;param width = width, in pixels (i.e. 640)
;;param height = height, in pixels (i.e. 480)
;;param depth = color depth (i.e. 16, 24, 32)
function GfxModeExists (width,height,depth)
; GFXModeExists example  
; If there is a mode, tell user  
mode=GfxModeExists(800,800,16)  
If mode=1 Then  
Print "The mode you requested exists!"  
Else  
Print "Sorry, that mode doesn't exist."  
End If  
; Wait for ESC press from user  
While Not KeyHit(1)  
Wend  
end function
;; Once you determine the video modes available by the video card using CountGFXModes(), you can iterate through them  and determine the width, height, and color depth capabilities of each mode.  Use this command to get the height of the mode. Use the GFXModeWidth and GFXModeDepth  to get the remaining parameters.
;;param None.
function GFXModeHeight (mode)
; CountGFXModes()/GfxModeWidth/GfxModeHeight/GfxModeDepth example  
intModes=CountGfxModes()  
Print "There are " + intModes + "graphic modes available:"  
; Display all modes including width, height, and color depth  
For t = 1 To intModes  
Print "Mode " + t + ":  
Print "Width=" + GfxModeWidth(t)  
Print "Height=" + GfxModeHeight(t)  
Print "Height=" + GfxModeDepth(t)  
Next  
end function
;; Once you determine the video modes available by the video card using CountGFXModes(), you can iterate through them  and determine the width, height, and color depth capabilities of each mode.  Use this command to get the width of the mode. Use the GFXModeHeight and  GFXModeDepth to get the remaining parameters.
;;param None.
function GFXModeWidth (mode)
; CountGFXModes()/GfxModeWidth/GfxModeHeight/GfxModeDepth example  
intModes=CountGfxModes()  
Print "There are " + intModes + "graphic modes available:"  
; Display all modes including width, height, and color depth  
For t = 1 To intModes  
Print "Mode " + t + ":  
Print "Width=" + GfxModeWidth(t)  
Print "Height=" + GfxModeHeight(t)  
Print "Height=" + GfxModeDepth(t)  
Next  
end function
;; There are two types of variables in Blitz Basic; local variables and global variables. Global variables can be utilized anywhere in your program (i.e. the main program look and all functions. Use global variables when you need to track a value across your entire program (player score, lives, etc). You can also define TYPEs as global as well.
;; See also: <a class=small href=Local.htm>Local</a>, <a class=small href=Dim.htm>Dim</a>, <a class=small href=Const.htm>Const</a>.
;;param variable = any valid variable/TYPE name
function Global variable
Global player1score ; Declare player's score as global  
Global graph.Cursor ; Declare the Cursor TYPE as global  
end function
;; This branches the flow of the program to a designated label, with the understanding  that there will be a Return later on in the called code to resume execution  of the program where the Gosub was called. With the use of Functions inside  of Blitz, it isn't very practical to use Gosubs, but you may still find it useful.  If you do not require the need to return execution back to the Gosub statement,  you may use Goto instead. See the example.
;; See also: <a class=small href=Return.htm>Return</a>, <a class=small href=Goto.htm>Goto</a>, <a class=small href=Function.htm>Function</a>.
;;param label = any valid exisiting label
function Gosub label
Print "The program starts ..."  
Gosub label1  
Print "The Program ends ..."  
; wait for ESC key before ending  
While Not KeyHit(1)  
Wend  
End  
.label1  
Print "We could do all sorts of things in this part of the program..."  
Print "But, we'll just go back to the original code, instead ..."  
Return  
end function
;; This branches the flow of the program to a designated label. With the use  of Functions inside of Blitz, it isn't very practical to use Gotos, but you  may still find it useful. If you require the need to return execution back to  the calling statement, you may use Gosub instead. See  the example.
;; See also: <a class=small href=Gosub.htm>Gosub</a>, <a class=small href=Function.htm>Function</a>.
;;param label = any valid exisiting label
function Goto label
Print "The program starts ..."  
Goto label1  
Print "This line never gets printed .."  
End  
.label1  
Print "We just jumped here!"  
; wait for ESC key before ending  
While Not KeyHit(1)  
Wend  
end function
;; Quite possibly one of the most useful yet underdocumented, confusing commands  in the Blitz Basic language is GrabImage.
;; This command allows you to grab a portion of the current drawing buffer (this  could be an image too if you've set it as an ImageBuffer)  and stick it into an image.
;; There are a million and one uses for this command, so I'll let you use your  imagination. However, the command doesn't make much sense as it is documented,  so let me clarify.
;; First, you must use CreateImage to create a new  blank image in which to grab to. Whatever size you create the image, THAT is  how much of the buffer will be grabbed from the x,y location you specify in  GrabImage.
;; For example, you create a new image with a size of 50 pixels by 50 pixels. When  you call GrabImage, you choose x=100, y=100. The area you will grab and put  into your new image will be 100,100 to 150,150. If you attempt to GrabImage  into an image variable that hasn't been created yet, you will get an error.
;; Note: You can REPLACE an existing image with a grabbed one.
;; See the example for a more practical look.
;;param image = variable of image handle
;;param x = starting x location to grab
;;param y = starting y location to grab
;;param frame = optional frame to insert the grabbed image into
function GrabImage image,x,y,[frame]
; GrabImage example  
; Turn on graphics  
Graphics 640,480,16  
; We'll be drawing right to the front buffer  
SetBuffer FrontBuffer()  
; You must create an empty image to grab from first  
gfxGrab=CreateImage(100,100)  
; Draw random rectangles on the screen until the  
; user presses ESC  
While Not KeyHit(1)  
; random color  
Color Rnd(255),Rnd(255),Rnd(255)  
; super random rectangles  
Rect Rnd(640),Rnd(480),Rnd(100),Rnd(100),Rnd(1)  
Delay 50  
Wend  
; Now, grab an image, starting at 100,100 and put it in gfxGrab  
GrabImage gfxGrab,100,100  
; Clear screen and show user the grabbed image  
Cls  
Text 0,0, "Here is your grabbed image! Press a mouse key ..."  
DrawImage gfxgrab,50,50  
; Wait for a mouse press  
WaitMouse()  
end function
;; This command sets Blitz into 'graphics' mode with the specified width, height, and color depth (in bits). This command must be executed before any graphic related commands can be used. Every time this command is used, any images loaded will be lost, and all handles to images will become invalid.
;; Color depth is OPTIONAL and should be left blank or set to zero if possible - Blitz will then determine the best color mode automatically for the user's video card.  In general 16 bit should be sufficient for most games and should be used wherever possible to ensure greatest compatibility.
;; Valid graphic width and height varies GREATLY from card to card so to be sure  your users can display the mode you wish, use the GfxModeExists command to be sure before you  try and set the mode yourself. Common resolutions that are probably safe are  640x480 and 800x600. Try to avoid odd screen modes like 640x400 as MANY MANY  cards cannot display them. Chances are if you can set your Windows screen resolution  to a particular resolution, then Blitz will behave in that resolution also.
;; Remember, each step higher in resolution and color depth mark a large jump in  system requirements and may be inversely proportionate to system performance.  If you use the lowest resolution and depth possible to meet your desired game  parameters, it will allow more people with lesser systems than your own still  play and enjoy your game. This is why many games still support a 640x480 mode  :)
;; An extra parameter is used at the end of the command after the color depth.  Here are the parameters:
;; 0 : auto - windowed in debug, fullscreen in non-debug (this is the default)
;; 1 : fullscreen mode
;; 2 : windowed mode
;; 3 : scaled window mode
;; See also: <a class=small href=EndGraphics.htm>EndGraphics</a>, <a class=small href=Graphics3D.htm>Graphics3D</a>.
;;param width = width of screen in pixels (640, 800, etc)
;;param height = height of screen in pixels (480, 600, etc)
;;param color depth = depth in bits (0, 16, 24, or 32 bit)
;;param mode = Video mode (see description); Optional
function Graphics width, height, color depth,[mode]
;GRAPHICS Example  
; Set The Graphic Mode  
Graphics 800,600  
; Now print something on the graphic screen  
Text 0,0, "This is some text printed on the graphic screen (and a white box)!  Press ESC ..."  
; Now for a box  
Rect 100,100,200,200,1  
While Not KeyHit(1)  
Wend  
end function
;; Use this command to get which buffer Blitz is currently writing to.
;;param None
function GraphicsBuffer()
; GraphicsWidth(), GraphicsHeight(), GraphicsDepth(), GraphicsBuffer() example  
; Set a graphics mode and buffer  
Graphics 640,480,16  
SetBuffer FrontBuffer()  
; Print the details  
Print "Screen width is: " + GraphicsWidth()  
Print "Screen height is: " + GraphicsHeight()  
Print "Screen color depth is: " + GraphicsDepth()  
Print "Current buffer handle is: " + GraphicsBuffer()  
; Wait for ESC before exiting  
While Not KeyHit(1)  
Wend  
end function
;; This command will tell you the color depth of the current graphics mode.
;;param None
function GraphicsDepth()
; GraphicsWidth(), GraphicsHeight(), GraphicsDepth(), GraphicsBuffer() example  
; Set a graphics mode and buffer  
Graphics 640,480,16  
SetBuffer FrontBuffer()  
; Print the details  
Print "Screen width is: " + GraphicsWidth()  
Print "Screen height is: " + GraphicsHeight()  
Print "Screen color depth is: " + GraphicsDepth()  
Print "Current buffer handle is: " + GraphicsBuffer()  
; Wait for ESC before exiting  
While Not KeyHit(1)  
Wend  
end function
;; This command will tell you the height, in pixels, of the current graphics  mode.
;;param None
function GraphicsHeight()
; GraphicsWidth(), GraphicsHeight(), GraphicsDepth(), GraphicsBuffer() example  
; Set a graphics mode and buffer  
Graphics 640,480,16  
SetBuffer FrontBuffer()  
; Print the details  
Print "Screen width is: " + GraphicsWidth()  
Print "Screen height is: " + GraphicsHeight()  
Print "Screen color depth is: " + GraphicsDepth()  
Print "Current buffer handle is: " + GraphicsBuffer()  
; Wait for ESC before exiting  
While Not KeyHit(1)  
Wend  
end function
;; This command will tell you the width, in pixels, of the current graphics  mode.
;;param None
function GraphicsWidth()
; GraphicsWidth(), GraphicsHeight(), GraphicsDepth(), GraphicsBuffer() example  
; Set a graphics mode and buffer  
Graphics 640,480,16  
SetBuffer FrontBuffer()  
; Print the details  
Print "Screen width is: " + GraphicsWidth()  
Print "Screen height is: " + GraphicsHeight()  
Print "Screen color depth is: " + GraphicsDepth()  
Print "Current buffer handle is: " + GraphicsBuffer()  
; Wait for ESC before exiting  
While Not KeyHit(1)  
Wend  
end function
;; When an image is loaded with LoadImage, the  image handle (the location within the image where the image is 'drawn from')  is always defaulted to the top left corner (coordinates 0,0). This means if  you draw an image that is 50x50 pixels at screen location 200,200, the image  will begin to be drawn at 200,200 and extend to 250,250.
;; This command moves the image handle from the 0,0 coordinate of the image to  the specified x and y location in the image. You can retrieve an image's current  location handle using the ImageXHandle and ImageYHandle. Finally, you can make all images  automatically load with the image handle set to middle using the AutoMidHandle command.
;; Note about the term 'handle'. There are two types of 'handles' we discuss in  these documents. One is the location within an image - as discussed in this  command. The other is a 'file handle', a variable used to hold an image, sound,  or font loaded with a command. See LoadImage for  more information about file handles.
;; Also See: MidHandle
;;param image = variable holding the file handle to the image
;;param x = x location of the new image handle location
;;param y = y location of the new image handle location
function HandleImage image,x,y
;HandleImage Example  
Graphics 800,600,16  
gfxPlayer=LoadImage("player.bmp")  
HandleImage gfxPlayer,20,20  
DrawImage gfxPlayer,0,0  
WaitKey  
end function
;; Converts integer values into hexidecimal values. If you don't know what  hex is, you don't need to know this command :)
;;param integer = any valid integer or integer variable
function Hex$ (integer)
intValue="64738"  
Print "The hexidecimal value of "+intValue+" is: " + hex$(intValue)  
end function
;; HidePointer is for use in windowed display modes, and simply hides the Windows  pointer when it is moved over your game's window. You can bring it back via ShowPointer. It has no effect in full-screen modes.
;; See also: <a class=small href=ShowPointer.htm>ShowPointer</a>.
;;param None.
function HidePointer
; HidePointer / ShoPointer Example  
; draw a simple screen, cut in half by a white line  
Graphics 800,600,0,2  
Color 255,255,255  
Line 400,0,400,600  
Text 200,300,"ShowPointer",True,True  
Text 600,300,"HidePointer",True,True  
; and a simple loop in which we hide / show the pointer dependent on  
; which side of the screen the mouse is on!  
Repeat  
If MouseX()<400 Then  
ShowPointer  
Else  
HidePointer  
End If  
If KeyHit(1) Then Exit ; ESCAPE to exit  
Forever  
End ; bye!  
end function
;; Returns an integer IP address for the specified host. host_index must be in the range 1...CountHostIPs().
;;param host_index - index number of host
function HostIP( host_index )
; First call CountHostIPs (blank infers the local machine)  
n = CountHostIPs("")  
; n now contains the total number of known host machines.  
; Obtain the internal id for the IP address  
ip = HostIP(1)  
; Convert it to human readable IP address  
ipaddress$ = DottedIP$(ip)  
Print "Dotted IP Test"  
Print "=============="  
Print ""  
Print "Internal Host IP ID:" + ip  
Print "Dotted IP Address:" + ipaddress$  
Print ""  
Print "Press any key to continue"  
WaitKey()  
End  
end function
;; This allows you to bypass the 'standard' networked game dialog box (normally  using StartNetGame) and start a hosted game directly.  A value of 2 is returned if the hosted game has started successfully.
;;param gamename$ = string value designating the game's name
function HostNetGame (gamename$)
; HostNetGame example  
joinResults=HostNetGame("ShaneGame")  
Select joinResults  
Case 2  
Print "Successfully started host game!"  
Default  
Print "Game was unable to start!"  
End Select  
waitkey()  
end function
;; Use this to check the value of a variable or to see if a condition is true  or false. The code between IF and END IF (or ENDIF) is executed if the condition  is true. Using NOT, you can also act if the condition is NOT true - or use ELSE  to perform a different set of commands than if the condition is met. Lastly,  you can use 'nested' or multiple IFs through the use of ELSE IF (or ELSEIF)  to do LOTS of condition checking. If you get too deep in condition checking,  consider using SELECT structures instead.
;; See also: <a class=small href=Then.htm>Then</a>, <a class=small href=Else.htm>Else</a>, <a class=small href=ElseIf.htm>ElseIf</a>, <a class=small href=EndIf.htm>EndIf</a>, <a class=small href=True.htm>True</a>, <a class=small href=False.htm>False</a>, <a class=small href=Select.htm>Select</a>.
;;param None.
function If
; IF THEN Example  
; Input the user's name  
name$=Input$("What is your name? ")  
; Doesn't the person's name equal SHANE?  
If name$ = "Shane" Then  
Print "You are recognized, Shane! Welcome!"  
Else  
Print "Sorry, you don't belong here!"  
; End of the condition checking  
End If  
end function
;; There are 1000 reasons for this command. Simply put, you may want to 'draw'  on an existing image you've loaded (LoadImage or LoadAnimImage) or created (CreateImage).  You could, for example, have a blank wall graphic and you want to add 'graffiti'  to it based on the user action (Jet Grind Radio baybeee! Sorry...). Instead  of trying to draw a dozen images all over the wall, just use the SetBuffer command to denote the wall graphic as  the 'target' buffer, and draw away! Next time you display that graphic (DrawImage),  you will see your changes! This is a powerful command!
;;param handle=variable holding the image's handle
;;param frame=optional frame to draw to if using an imagestrip image
function ImageBuffer (handle[,frame])
; CreateImage/TileImage/ImageBuffer example  
; Again, we'll use globals even tho we don't need them here  
; One variable for the graphic we'll create, one for a timer  
Global gfxStarfield, tmrScreen  
; Declare graphic mode  
Graphics 640,480,16  
; Create a blank image that is 320 pixels wide and 32 high with 10 frames of  32x32  
gfxStarfield=CreateImage(32,32,10)  
; loop through each frame of the graphic we just made  
For t = 0 To 9  
; Set the drawing buffer to the graphic frame so we can write on it  
SetBuffer ImageBuffer(gfxStarfield,t)  
; put 50 stars in the frame at random locations  
For y = 1 To 50  
Plot Rnd(32),Rnd(32)  
Next  
Next  
; Double buffer mode for smooth screen drawing  
SetBuffer BackBuffer()  
; Loop until ESC is pressed  
While Not KeyHit(1)  
; Only update the screen every 300 milliseconds. Change 300 for faster or  
; slower screen updates  
If MilliSecs() > tmrScreen+300 Then  
Cls ; clear the screen  
; Tile the screen with a random frame from our new graphic starting at  
; x=0 and y=0 location.  
TileImage gfxStarfield,0,0,Rnd(9)  
Flip ; Flip the screen into view  
tmrScreen=MilliSecs() ; reset the time  
End If  
Wend  
end function
;; Use this command and ImageWidth to return the  size of the given image (using the handle assigned when the image was loaded  with LoadImage) in pixels.
;;param image handle = variable assigned when the image was loaded
function ImageHeight (image handle)
; ImageHeight/ImageWidth Example  
; Global, as always, for graphics  
Global gfxPlayer  
; Enter graphics mode and start double buffering  
Graphics 640,480,16  
SetBuffer BackBuffer()  
; Load the image-assign the handle to gfxPlayer  
gfxPlayer=LoadImage("player.bmp")  
; Print the image dimensions  
Print "The image height is: " + ImageHeight(gfxPlayer)  
Print "The image width is: " + ImageWidth(gfxPlayer)  
; Wait until ESC is pressed so you can see the output  
While Not KeyHit(1)  
Wend  
end function
;; There are many times when you need to see if an image has collided with  (or is touching) a specific rectangular area of the screen. This command performs  pixel perfect accurate collision detection between the image of your choice  and a specified rectangle on the screen.
;; The usefulness of this comes into play when you think of a game like Monkey  Island - when you might have a backdrop on the screen showing a room that has  items in it the player can interact with using a mouse pointer graphic. In some  cases, the items on the screen you wish to interact with will be seperate (often  animated or moving) images of their own. For this situation, you would be better  off using ImagesCollide or ImagesOverlap to detect the collision between  pointer graphic and the image.
;; Howevever, should your program just need to detect a graphic (like a mouse pointer)  over at a particular location/region of the screen (often called a 'hot spot'),  this command works great!
;; As with any collision in Blitz, you will need to know the PRECISE location of  the graphic you wish to test collision with, as well as the x, y, width, and  height of the screen area (rect) you wish to test.
;; The example blatently uses graphics that are much smaller than their container  to show you how accurate this command really is. The ImageRectOverlap example is identical to  this one - and shows how inaccurate the overlapping method can be with graphics  of this nature.
;;param image = Image to test collision against
;;param x = image's x location
;;param y = image's y location
;;param frame = image's frame
;;param rect x = x location start of the rect to test
;;param rect y = y location start of the rect
;;param rect width = width of the rect
;;param rect height = height of the rect
function ImageRectCollide (image,x,y,frame,rect x,rect y,rect width,rect height)
; ImageRectCollide Example  
; Turn on graphics mode  
Graphics 640,480,16  
; Create new empty graphic to store our circle in  
gfxCircle=CreateImage(50,50)  
; Draw the circle image  
; Set drawing operations to point to our new empty graphic  
SetBuffer ImageBuffer(gfxCircle)  
Color 255,0,0  
; Note the extra space between the circle and the edge of the graphic  
Oval 10,10,30,30,1  
; Let's not forget to put the drawing buffer back!  
SetBuffer BackBuffer()  
Color 0,0,255  
; Locate our box to a random, visible screen location  
hotX=Rnd(50,610)  
hotY=Rnd(50,430)  
hotW=Rnd(20,100)  
hotH=Rnd(20,100)  
; Repeat the loop until we've had a collision  
Repeat  
; Attach our mouse to the circle to move it  
circleX=MouseX()  
circleY=MouseY()  
; Standard double buffer technique; clear screen first  
Cls  
; Draw our rectangle  
Rect hotX,hotY,hotW,hotH,0  
DrawImage gfxCircle,circleX,circleY  
; Standard double buffer technique; flip after all drawing is done  
Flip  
; We test the locations of our rectangle area and circle to see if they have  pixel collided  
Until ImageRectCollide (gfxCircle,circleX,circleY,0,hotX,hotY,hotW,hotH)  
; Loop is over, we must've collided!  
Text 0,0, "WE'VE HAD A COLLISION! PRESS A MOUSE BUTTON"  
; Can't see the text until we flip ..  
Flip  
; Wait for a mouse click  
WaitMouse()  
; End our graphics mode  
EndGraphics  
end function
;; There are many times when you need to see if an image has collided with  (or is touching) a specific rectangular area of the screen. This command performs  a collision detection between the image of your choice and a specified rectangle  on the screen. Transparent pixels are ignored during the collision process,  making this command a bit inaccurate for odd shaped graphics. See ImageRectCollide for pixel perfect collisions  between an image and rectangular area of the screen.
;; The usefulness of this comes into play when you think of a game like Monkey  Island - when you might have a backdrop on the screen showing a room that has  items in it the player can interact with using a mouse pointer graphic. In some  cases, the items on the screen you wish to interact with will be seperate (often  animated or moving) images of their own. For this situation, you would be better  off using ImagesCollide or ImagesOverlap to detect the collision between  pointer graphic and the image.
;; Howevever, should your program just need to detect a graphic (like a mouse pointer)  over at a particular location/region of the screen (often called a 'hot spot'),  this command works great!
;; As with any collision in Blitz, you will need to know the PRECISE location of  the graphic you wish to test collision with, as well as the x, y, width, and  height of the screen area (rect) you wish to test.
;; The example blatently uses graphics that are much smaller than their container  to show you how inaccurate this command can really be - if not used carefully.  The ImageRectCollide example is identical  to this one - and shows how accurate the pixel-perfect collision method can  be with graphics of this nature.
;;param image = Image to test collision against
;;param x = image's x location
;;param y = image's y location
;;param rect x = x location start of the rect to test
;;param rect y = y location start of the rect
;;param rect width = width of the rect
;;param rect height = height of the rect
function ImageRectOverlap (image,x,y,rect x,rect y,rect width,rect height)
; ImageRectOverlap Example  
; Turn on graphics mode  
Graphics 640,480,16  
; Create new empty graphic to store our circle in  
gfxCircle=CreateImage(50,50)  
; Draw the circle image  
; Set drawing operations to point to our new empty graphic  
SetBuffer ImageBuffer(gfxCircle)  
Color 255,0,0  
; Note the extra space between the circle and the edge of the graphic  
Oval 10,10,30,30,1  
; Let's not forget to put the drawing buffer back!  
SetBuffer BackBuffer()  
Color 0,0,255  
; Locate our box to a random, visible screen location  
hotX=Rnd(50,610)  
hotY=Rnd(50,430)  
hotW=Rnd(20,100)  
hotH=Rnd(20,100)  
; Repeat the loop until we've had a collision  
Repeat  
; Attach our mouse to the circle to move it  
circleX=MouseX()  
circleY=MouseY()  
; Standard double buffer technique; clear screen first  
Cls  
; Draw our rectangle  
Rect hotX,hotY,hotW,hotH,0  
DrawImage gfxCircle,circleX,circleY  
; Standard double buffer technique; flip after all drawing is done  
Flip  
; We test the locations of our rectangle area and circle to see if they have  overlapped  
Until ImageRectOverlap (gfxCircle,circleX,circleY,hotX,hotY,hotW,hotH)  
; Loop is over, we must've collided!  
Text 0,0, "WE'VE HAD A COLLISION! PRESS A MOUSE BUTTON"  
; Can't see the text until we flip ..  
Flip  
; Wait for a mouse click  
WaitMouse()  
; End our graphics mode  
EndGraphics  
end function
;; This is THE command to get pixel-perfect collisions between images. It will  not consider transparent pixels during the collision check (basically, only  the 'meat' of the image will invoke a collision). This makes it perfect for  most situations where you have odd-shaped graphics to text against.
;; The ImagesOverlap command is MUCH faster, however,  but can only determine if ANY of the two images have overlapped (this INCLUDES  transparent pixels). This method works if you have graphics that completely  fill their container and/or you don't plan on needing pinpoint accuracy.
;; As with any collision detection system in Blitz, you will need to know the variable  names of the two images, and their X and Y locations at the moment collision  checking occurs.
;; The example blatently uses graphics that are much smaller than their container  to show you how accurate this command really is. The ImagesOverlap example is identical to this one - and shows how inaccurate the overlapping method can be with graphics of this nature.
;;param image1 - first image to test
;;param x1 - image1's x location
;;param y1 - image1's y location
;;param frame1 - image1's frame to test (optional)
;;param image2 - second image to test
;;param x2 - image2's x location
;;param y2 - image2's y location
;;param frame2 - image2's frame to test (optional)
function ImagesCollide (image1,x1,y1,frame1,image2,x2,y2,frame2)
; ImagesCollide Example  
; Turn on graphics mode  
Graphics 640,480,16  
; Create two new empty graphics to store our circle and box in  
gfxBox=CreateImage(50,50)  
gfxCircle=CreateImage(50,50)  
; Draw the box image first  
; Set drawing operations to point to our new empty graphic  
SetBuffer ImageBuffer(gfxBox)  
; Change drawing color to blue  
Color 0,0,255  
;Draw our box (note that it has a 10 pixel space around it)  
Rect 10,10,30,30,1  
; Repeat for the circle graphic  
SetBuffer ImageBuffer(gfxCircle)  
Color 255,0,0  
; Note the extra space between the circle and the edge of the graphic  
Oval 10,10,30,30,1  
; Let's not forget to put the drawing buffer back!  
SetBuffer BackBuffer()  
; Locate our box to a random, visible screen location  
boxX=Rnd(50,610)  
boxY=Rnd(50,430)  
; Repeat the loop until we've had a collision  
Repeat  
; Attach our mouse to the circle to move it  
circleX=MouseX()  
circleY=MouseY()  
; Standard double buffer technique; clear screen first  
Cls  
; Draw our objects at the designated location  
DrawImage gfxBox,boxX,boxY  
DrawImage gfxCircle,circleX,circleY  
; Standard double buffer technique; flip after all drawing is done  
Flip  
; We test the locations of our box and circle to see if they have pixel collided  
Until ImagesCollide (gfxBox,boxX,boxY,0,gfxCircle,circleX,circleY,0)  
; Loop is over, we must've collided!  
Text 0,0, "WE'VE HAD A COLLISION! PRESS A MOUSE BUTTON"  
; Can't see the text until we flip ..  
Flip  
; Wait for a mouse click  
WaitMouse()  
; End our graphics mode  
EndGraphics  
end function
;; This is a very fast, simple collision type command that will allow you to  determine whether or not two images have overlapped each other. This does not  take into account any transparent pixels (see ImagesCollide).
;; As with any collision detection system in Blitz, you will need to know the variable  names of the two images, and their X and Y locations at the moment collision  checking occurs.
;; In many cases, you might be able to get away with using this more crude, yet  quite fast method of collision detection. For games where your graphics are  very squared off and pixel-perfect accuracy isn't a must, you can employ this  command to do quick and dirty overlap checking.
;; The example blatently uses graphics that are much smaller than their container  to show you how inaccurate this command can be - if not used wisely. The ImagesCollide example is identical to this one  - and shows how pixel-perfect collision works.
;; You might be able to get away with this on some more classical games like Robotron,  Defender, Dig Dug, etc.
;;param image1 = first image to test
;;param x1 = image1's x location
;;param y1 = image1's y location
;;param image2 = second image to test
;;param x2 = image2's x location
;;param y2 = image2's y location
function ImagesOverlap (image1,x1,y1,image2,x2,y2)
; ImagesOverlap Example  
; Turn on graphics mode  
Graphics 640,480,16  
; Create two new empty graphics to store our circle and box in  
gfxBox=CreateImage(50,50)  
gfxCircle=CreateImage(50,50)  
; Draw the box image first  
; Set drawing operations to point to our new empty graphic  
SetBuffer ImageBuffer(gfxBox)  
; Change drawing color to blue  
Color 0,0,255  
;Draw our box (note that it has a 10 pixel space around it)  
Rect 10,10,30,30,1  
; Repeat for the circle graphic  
SetBuffer ImageBuffer(gfxCircle)  
Color 255,0,0  
; Note the extra space between the circle and the edge of the graphic  
Oval 10,10,30,30,1  
; Let's not forget to put the drawing buffer back!  
SetBuffer BackBuffer()  
; Locate our box to a random, visible screen location  
boxX=Rnd(50,610)  
boxY=Rnd(50,430)  
; Repeat the loop until we've had a collision  
Repeat  
; Attach our mouse to the circle to move it  
circleX=MouseX()  
circleY=MouseY()  
; Standard double buffer technique; clear screen first  
Cls  
; Draw our objects at the designated location  
DrawImage gfxBox,boxX,boxY  
DrawImage gfxCircle,circleX,circleY  
; Standard double buffer technique; flip after all drawing is done  
Flip  
; We test the locations of our box and circle to see if they have overlapped  
Until ImagesOverlap (gfxBox,boxX,boxY,gfxCircle,circleX,circleY)  
; Loop is over, we must've collided!  
Text 0,0, "WE'VE HAD A COLLISION! PRESS A MOUSE BUTTON"  
; Can't see the text until we flip ..  
Flip  
; Wait for a mouse click  
WaitMouse()  
; End our graphics mode  
EndGraphics  
end function
;; Use this command and ImageHeight to return  the size of the given image (using the handle assigned when the image was loaded  with LoadImage) in pixels.
;;param image handle = variable assigned when the image was loaded
function ImageWidth (image handle)
; ImageHeight/ImageWidth Example  
; Global, as always, for graphics  
Global gfxPlayer  
; Enter graphics mode and start double buffering  
Graphics 640,480,16  
SetBuffer BackBuffer()  
; Load the image-assign the handle to gfxPlayer  
gfxPlayer=LoadImage("player.bmp")  
; Print the image dimensions  
Print "The image height is: " + ImageHeight(gfxPlayer)  
Print "The image width is: " + ImageWidth(gfxPlayer)  
; Wait until ESC is pressed so you can see the output  
While Not KeyHit(1)  
Wend  
end function
;; It is occasionally useful to determine the location of an image's image  handle. This command returns the X coordinate. Use ImageYHandle to get the Y coordinate. Please  see MidHandle for more information on the image's  image handle.
;; Note about the term 'handle'. There are two types of 'handles' we discuss in  these documents. One is the location within an image - as discussed in this  command. The other is a 'file handle', a variable used to hold an image, sound,  or font loaded with a command. See LoadImage for  more information about file handles.
;;param image = variable holding the image's file handle
function ImageXHandle image
; MidHandle/ImageXHandle()/ImageYHandle()/AutoMidHandle  
; Initiate Graphics Mode  
Graphics 640,480,16  
; Set up the image file handle as a global  
Global gfxBall  
; Load the image - you may need to change the location of the file  
gfxBall=LoadImage ("C:Program FilesBlitz Basicsamplesall.bmp")  
; Until the user presses ESC key ...  
While Not KeyHit(1)  
Text 0,0,"Default Image Handle for gfxBall... Press ESC ..."  
Text 0,14,"X handle-" + ImageXHandle(gfxBall) ; Print the location of the image  handle x location  
Text 0,28,"Y handle-" + ImageYHandle(gfxBall) ; Print the location of the image  handle y location  
DrawImage gfxBall,200,200,0 ; draw the image at 200,200  
Wend  
; Clear the screen  
Cls  
; Set the ball's handle to the center of the image  
MidHandle gfxBall  
; Until the user presses ESC key ... show the new information  
While Not KeyHit(1)  
Text 0,0,"New Image Handle for gfxBall... Press ESC ..."  
Text 0,14,"X handle-" + ImageXHandle(gfxBall)  
Text 0,28,"Y handle-" + ImageYHandle(gfxBall)  
DrawImage gfxBall,200,200,0  
Wend  
; Makes all images load up with their handles in the center of the image  
AutoMidHandle True  
Cls  
; Load the image again  
gfxBall=LoadImage ("C:Program FilesBlitz Basicsamplesall.bmp")  
; Until the user presses ESC key ... show the new information  
While Not KeyHit(1)  
Text 0,0,"Automatic image handle of gfxBall... Press ESC ..."  
Text 0,14,"X handle-" + ImageXHandle(gfxBall)  
Text 0,28,"Y handle-" + ImageYHandle(gfxBall)  
DrawImage gfxBall,200,200,0  
Wend  
end function
;; It is occasionally useful to determine the location of an image's image  handle. This command returns the Y coordinate. Use ImageXHandle to get the X coordinate. Please  see MidHandle for more information on the image's  image handle.
;; Note about the term 'handle'. There are two types of 'handles' we discuss in  these documents. One is the location within an image - as discussed in this  command. The other is a 'file handle', a variable used to hold an image, sound,  or font loaded with a command. See LoadImage for  more information about file handles.
;;param image = variable holding the image's file handle
function ImageYHandle image
; MidHandle/ImageXHandle()/ImageYHandle()/AutoMidHandle  
; Initiate Graphics Mode  
Graphics 640,480,16  
; Set up the image file handle as a global  
Global gfxBall  
; Load the image - you may need to change the location of the file  
gfxBall=LoadImage ("C:Program FilesBlitz Basicsamplesall.bmp")  
; Until the user presses ESC key ...  
While Not KeyHit(1)  
Text 0,0,"Default Image Handle for gfxBall... Press ESC ..."  
Text 0,14,"X handle-" + ImageXHandle(gfxBall) ; Print the location of the image  handle x location  
Text 0,28,"Y handle-" + ImageYHandle(gfxBall) ; Print the location of the image  handle y location  
DrawImage gfxBall,200,200,0 ; draw the image at 200,200  
Wend  
; Clear the screen  
Cls  
; Set the ball's handle to the center of the image  
MidHandle gfxBall  
; Until the user presses ESC key ... show the new information  
While Not KeyHit(1)  
Text 0,0,"New Image Handle for gfxBall... Press ESC ..."  
Text 0,14,"X handle-" + ImageXHandle(gfxBall)  
Text 0,28,"Y handle-" + ImageYHandle(gfxBall)  
DrawImage gfxBall,200,200,0  
Wend  
; Makes all images load up with their handles in the center of the image  
AutoMidHandle True  
Cls  
; Load the image again  
gfxBall=LoadImage ("C:Program FilesBlitz Basicsamplesall.bmp")  
; Until the user presses ESC key ... show the new information  
While Not KeyHit(1)  
Text 0,0,"Automatic image handle of gfxBall... Press ESC ..."  
Text 0,14,"X handle-" + ImageXHandle(gfxBall)  
Text 0,28,"Y handle-" + ImageYHandle(gfxBall)  
DrawImage gfxBall,200,200,0  
Wend  
end function
;; Includes the contents of the specified .bb file in the current source code file.
;; Useful for when you're code gets too big, or you just want to organise functions that you've finalised and functions that you're working on.
;; The Include command effectively "cuts and pastes" the contents of the .bb file to be included into the current file at the point of the Include function call, temporarily, before being passed to the compiler to Execute.
;; Note that each .bb file can only be included once.
;;param filename$ - name of .bb file to be 'Include(d)' in quotes.
function Include filename$
; Include Example  
; ---------------  
;include finished code files  
Include "include_globals.bb"  
Include "include_menu.bb"  
Include "include_game.bb"  
Include "include_music.bb"  
Include "include_sound.bb"  
Include "include_ai.bb"  
;Main program loop here  
;End program  
end function
;; This command will retrieve a string value from the user with an optional  prompt on the screen (if not in a graphic mode) or on the current drawing buffer  being used by the program. Usually you will assign this command's value to a  string for later use.
;;param prompt$ = any valid string (optional)
function Input$ (prompt$)
; Get the user's name and print a welcome  
name$=Input$("What is your name?")  
Write "Hello there, " + name$ + "!"  
end function
;; I'm not sure the practical usage of this command, but basically, you can  control where you INSERT the current TYPE object into  the TYPE collection. When you create a new Type object with the NEW command, it is automatically appended to the END of  the collection. Using INSERT along with BEFORE and AFTER (and electively FIRST  and LAST) to put the Type object exactly where you want  it. Sounds confusing - and chances are likely you'll never need this ability.  But its here if you need it. Check the example.
;; See also: <a class=small href=Type.htm>Type</a>, <a class=small href=New.htm>New</a>, <a class=small href=Before.htm>Before</a>, <a class=small href=After.htm>After</a>, <a class=small href=First.htm>First</a>, <a class=small href=Last.htm>Last</a>, <a class=small href=Each.htm>Each</a>, <a class=small href=Delete.htm>Delete</a>.
;;param None.
function Insert
; INSERT example  
; Define a CHAIR type with a created field to track what order it was created  in.  
Type CHAIR  
Field created  
End Type  
; Create 10 chairs, setting created field to the order of creation  
For t = 1 To 10  
room.chair= New Chair  
room\created = t  
Next  
; Make a NEW chair (the 11th)  
room.chair= New Chair  
; Set its created value to 11  
room\created=11  
; Now, let's insert this chair BEFORE the first one in the collection  
Insert room Before First Chair  
; Let's iterate through all the chairs, and show their creation order  
For room.chair = Each chair  
Print room\created  
Next  
end function
;; This command will allow you to search for an occurance of a string within  another string. The command returns the location (number of characters from  the left) of the string you are looking for. Command returns a Zero if no matches  are found.
;;param string1$ = the string you wish to search
;;param string2$ = the string to find
;;param offset = valid integer starting position to being search (optional)
function Instr (string1$, string2$, offset)
name$="Shane R. Monroe"  
location = Instr( name$,"R.",1)  
Print "Your string contains 'R.' at position number " + location + "!"  
end function
;; Converts the value to the nearest integer.
;; This is the same as Blitz's automatic type conversion.
;; So the two commands...
;; n = value
;; n = Int( value )
;; ... do exactly the same thing when n is an integer variable.
;; If Int is applied to a string it converts as much as possible:
;; Int( "10" ) ........ result is 10
;; Int( "3.7" ) ....... result is 3, stops at "." which can't be part of an integer
;; Int( "junk3" ) .... result is 0, stops at "j"
;; Int converts floating point numbers by rounding to the nearest integer.
;; NOTE: This is not the traditional meaning of Int in Basic.
;; What about numbers exactly halfway between integers?
;; The rounding is to the nearest even integer:
;; Int( 2.5 ) ... produces 2
;; Int( 3.5 ) ... produces 4
;; See also Floor and Ceil for other types of rounding.
;;param value = a number, or a string which represents a number
function Int( value )
; Ceil / Floor / Int example, three kinds of rounding.  
; Move mouse. Escape quits.  
Graphics 640, 480  
Const KEY_ESC = 1  
SetBuffer BackBuffer()  
Origin 320, 240  
MoveMouse 320, 240  :  HidePointer  
While Not KeyDown( KEY_ESC )  
Cls  
my = MouseY() - 240  
Color 100, 100, 0  
Line -320, my, 319, my  
DrawNumberLine  
y# = Float( -my ) / 32  
Text 100, 50, "          y = "  + y  
Text 100, 70, "  Ceil( y ) = "  + Ceil( y )  
Text 100, 90, " Floor( y ) = "  + Floor( y )  
Text 100, 110, "   Int( y ) = " + Int( y )  
Flip  
Wend  
End  
Function DrawNumberLine( )  ; vertical line with numeric labels  
Color 255, 255, 255  
Line 0, -240, 0, 239  
For n = -7 To 7  
yn = -32 * n  
Line -2, yn, 2, yn  
Text -30, yn - 6, RSet( n, 2 )  
Next  
End Function  
end function
;; Use this command to join a network game, bypassing the dialog box normally  endured with the StartNetGame command.
;; This returns 0 if the command failed, or 1 if the game was joined successfully.
;;param gamename$ = valid string containing game name to join
;;param serverIP$ = IP address of computer hosting game
function JoinNetGame (gamename$,serverIP$)
; JoinNetGame example  
; Note; run the HostNetGame example code on the other computer  
; you wish to join with  
gamename$="ShaneGame"  
; Change this to match the other computer's IP!  
serverIP$="0.0.0.0"  
; Make the join attempt  
joinResults=JoinNetGame(gamename$,serverIP$)  
Select joinResults  
Case 1  
Print "Joined the game successfully!"  
Default  
Print "Joining the game was unsuccessful."  
End Select  
WaitKey()  
end function
;; This command (and its counterparts KeyDown and MouseDown) is used to detect if a joystick button  is being held down. You must check for each joystick button independantly with  its corresponding number (unlike KeyDown which returns WHICH key is being held  down). Also see JoyHit.
;;param button = number of joystick button to check
;;param port = number of joystick port to check (optional)
function JoyDown (button,[port])
; JoyDown Example  
; Until user presses ESC, show the mouse button pressed  
While Not KeyHit(1)  
button$="No"  
For t = 1 To 5  
If JoyDown(t) Then button$=Str(t)  
Print button$ + " joystick button pressed!"  
Next  
Wend  
end function
;; JoyHat returns the state of a joystick 'hat' or 'pov' (point-of-view) control.
;; If the returned value is -1, the hat is currently centred.
;; Otherwise, the returned value gives the direction as an angle in the range 0 to 360 relative to 'up'.
;;param port (optional) - an integer value representing the port to be checked for joystick data
function JoyHat ( [port] )
While Not KeyHit(1)  
Print JoyHat()  
Wend  
End  
end function
;; This command returns the number of times a specified joystick button has  been hit since the last time you called the JoyHit() command. Also see KeyHit and MouseHit.
;;param button = number of joystick button to check
;;param port = number of joystick port to check (optional)
function JoyHit (button,[port])
; JoyHit Example  
; Set up the timer  
current=MilliSecs()  
Print "Press FireButton 1 a bunch of times for five seconds..."  
; Wait 5 seconds  
While MilliSecs() < current+5000  
Wend  
; Print the results  
Print "Pressed button " + JoyHit(1) + " times."  
end function
;; Returns the pitch value of a joystick.
;; The optional port parameter allows you to select which joystick to get output from should you have multiple controllers connected to your PC.
;;param port (optional) - an integer value representing the port to be checked for joystick data
function JoyPitch#([port])
; JoyPitch Example  
; ----------------  
While Not KeyDown(1)  
; Get various joystick values  
ju#=JoyU()  
judir=JoyUDir()  
jv#=JoyV()  
jvdir=JoyVDir()  
jyaw#=JoyYaw()  
jpitch#=JoyPitch()  
jroll#=JoyRoll()  
; Output joystick values  
Text 0,0,"Move joystick to output values onto screen"  
Text 0,20,"JoyU(): "+ju#  
Text 0,40,"JoyUDir(): "+judir  
Text 0,60,"JoyV(): "+jv#  
Text 0,80,"JoyVDir(): "+jvdir  
Text 0,100,"JoyYaw(): "+jyaw#  
Text 0,120,"JoyPitch(): "+jpitch#  
Text 0,140,"JoyRoll(): "+jroll#  
Flip  
Cls  
Wend  
end function
;; Returns the roll value of a joystick.
;; The roll value of a joystick usually corresponds to a joystick's 'twistable stick' or 'rudder' feature, although this may vary depending on the joystick, and will not be available with all joysticks.
;; The optional port parameter allows you to select which joystick to get output from should you have multiple controllers connected to your PC.
;;param port (optional) - an integer value representing the port to be checked for joystick data
function JoyRoll#([port])
; JoyRoll Example  
; ---------------  
While Not KeyDown(1)  
; Get various joystick values  
ju#=JoyU()  
judir=JoyUDir()  
jv#=JoyV()  
jvdir=JoyVDir()  
jyaw#=JoyYaw()  
jpitch#=JoyPitch()  
jroll#=JoyRoll()  
; Output joystick values  
Text 0,0,"Move joystick to output values onto screen"  
Text 0,20,"JoyU(): "+ju#  
Text 0,40,"JoyUDir(): "+judir  
Text 0,60,"JoyV(): "+jv#  
Text 0,80,"JoyVDir(): "+jvdir  
Text 0,100,"JoyYaw(): "+jyaw#  
Text 0,120,"JoyPitch(): "+jpitch#  
Text 0,140,"JoyRoll(): "+jroll#  
Flip  
Cls  
Wend  
end function
;; This command returns the type of joystick that is currently connected to  the computer. It returns 0 if there is none, 1 for digital, and 2 for analog.
;;param port = number of joystick port to check (optional)
function JoyType ([port])
; JoyType() example  
; Check to see what stick is present - print the proper message  
Select JoyType()  
Case 0  
Print "Sorry, no joystick attached to system!"  
Case 1  
Print "Digital joystick is attached to system!"  
Case 2  
Print "Analog joystick is attched to system!"  
End Select  
; Wait for user to hit ESC  
While Not KeyHit(1)  
Wend  
end function
;; Returns a float value between -1 and 1 that represents the U value of a joystick's output.
;; The U value of a joystick usually corresponds to a joystick's 'slider' or 'throttle' feature, although this may vary depending on the joystick, and will not be available with all joysticks.
;; The optional port parameter allows you to select which joystick to get output from should you have multiple controllers connected to your PC.
;;param port (optional) - an integer value representing the port to be checked for joystick data
function JoyU#([port])
; JoyU Example  
; ------------  
While Not KeyDown(1)  
; Get various joystick values  
ju#=JoyU()  
judir=JoyUDir()  
jv#=JoyV()  
jvdir=JoyVDir()  
jyaw#=JoyYaw()  
jpitch#=JoyPitch()  
jroll#=JoyRoll()  
; Output joystick values  
Text 0,0,"Move joystick to output values onto screen"  
Text 0,20,"JoyU(): "+ju#  
Text 0,40,"JoyUDir(): "+judir  
Text 0,60,"JoyV(): "+jv#  
Text 0,80,"JoyVDir(): "+jvdir  
Text 0,100,"JoyYaw(): "+jyaw#  
Text 0,120,"JoyPitch(): "+jpitch#  
Text 0,140,"JoyRoll(): "+jroll#  
Flip  
Cls  
Wend  
end function
;; Returns an integer value between -1 and 1 that represents the U value of a joystick's output.
;; The U value of a joystick usually corresponds to a joystick's 'slider' or 'throttle' feature, although this may vary depending on the joystick, and will not be available with all joysticks.
;; The optional port parameter allows you to select which joystick to get output from should you have multiple controllers connected to your PC.
;;param port (optional) - an integer value representing the port to be checked for joystick data
function JoyUDir([port])
JoyUDir Example  
---------------  
While Not KeyDown(1)  
; Get various joystick values  
ju#=JoyU()  
judir=JoyUDir()  
jv#=JoyV()  
jvdir=JoyVDir()  
jyaw#=JoyYaw()  
jpitch#=JoyPitch()  
jroll#=JoyRoll()  
; Output joystick values  
Text 0,0,"Move joystick to output values onto screen"  
Text 0,20,"JoyU(): "+ju#  
Text 0,40,"JoyUDir(): "+judir  
Text 0,60,"JoyV(): "+jv#  
Text 0,80,"JoyVDir(): "+jvdir  
Text 0,100,"JoyYaw(): "+jyaw#  
Text 0,120,"JoyPitch(): "+jpitch#  
Text 0,140,"JoyRoll(): "+jroll#  
Flip  
Cls  
Wend  
end function
;; Returns a float value between -1 and 1 that represents the V value of a joystick's output.
;; The optional port parameter allows you to select which joystick to get output from should you have multiple controllers connected to your PC.
;;param port (optional) - an integer value representing the port to be checked for joystick data
function JoyV#([port])
; JoyV Example  
; ------------  
While Not KeyDown(1)  
; Get various joystick values  
ju#=JoyU()  
judir=JoyUDir()  
jv#=JoyV()  
jvdir=JoyVDir()  
jyaw#=JoyYaw()  
jpitch#=JoyPitch()  
jroll#=JoyRoll()  
; Output joystick values  
Text 0,0,"Move joystick to output values onto screen"  
Text 0,20,"JoyU(): "+ju#  
Text 0,40,"JoyUDir(): "+judir  
Text 0,60,"JoyV(): "+jv#  
Text 0,80,"JoyVDir(): "+jvdir  
Text 0,100,"JoyYaw(): "+jyaw#  
Text 0,120,"JoyPitch(): "+jpitch#  
Text 0,140,"JoyRoll(): "+jroll#  
Flip  
Cls  
Wend  
end function
;; Returns an integer value between -1 and 1 that represents the V value of a joystick's output.
;; The optional port parameter allows you to select which joystick to get output from should you have multiple controllers connected to your PC.
;;param port (optional) - an integer value representing the port to be checked for joystick data
function JoyVDir([port])
; JoyVDir Example  
; ---------------  
While Not KeyDown(1)  
; Get various joystick values  
ju#=JoyU()  
judir=JoyUDir()  
jv#=JoyV()  
jvdir=JoyVDir()  
jyaw#=JoyYaw()  
jpitch#=JoyPitch()  
jroll#=JoyRoll()  
; Output joystick values  
Text 0,0,"Move joystick to output values onto screen"  
Text 0,20,"JoyU(): "+ju#  
Text 0,40,"JoyUDir(): "+judir  
Text 0,60,"JoyV(): "+jv#  
Text 0,80,"JoyVDir(): "+jvdir  
Text 0,100,"JoyYaw(): "+jyaw#  
Text 0,120,"JoyPitch(): "+jpitch#  
Text 0,140,"JoyRoll(): "+jroll#  
Flip  
Cls  
Wend  
end function

end function
;; This command returns the value of the x-axis of the joystick. The range  is -1 to 1 (full left to full right). The value returned is a floating point  number. See the example.
;;param port = number of joystick port to check (optional)
function JoyX ([port])
; JoyX()/JoyY() example  
While Not KeyHit(1)  
Cls  
Text 0,0,"Joy X Value: " + JoyX() + " - Joy Y Value:" + JoyY()  
Wend  
end function
;; This command returns the direction of the x-axis of the joystick being pressed.  The value is -1 (left) or 1 (right). The value returned is an integer number.  See the example. Perfect for digital joysticks.
;; As with any joystick command, you MUST have a DirectX compatible joystick plugged  in and properly configured within Windows for it to work. See your joystick  documentation for more information.
;;param port = number of joystick port to check (optional)
function JoyXDir ([port])
; JoyXDir() example  
While Not KeyHit(1)  
Cls  
Text 0,0,"Joy X Direction: " + JoyXDir()  
Wend  
end function
;; This command returns the value of the x-axis of the joystick. The range  is -1 to 1 (full up to full down). The value returned is a floating point number.  See the example.
;;param port = number of joystick port to check (optional)
function JoyY ([port])
; JoyX()/JoyY() example  
While Not KeyHit(1)  
Cls  
Text 0,0,"Joy X Value: " + JoyX() + " - Joy Y Value:" + JoyY()  
Wend  
end function
;; Returns the yaw value of a joystick.
;; The optional port parameter allows you to select which joystick to get output from should you have multiple controllers connected to your PC.
;;param port (optional) - an integer value representing the port to be checked for joystick data
function JoyYaw#([port])
; JoyYaw Example  
; --------------  
While Not KeyDown(1)  
; Get various joystick values  
ju#=JoyU()  
judir=JoyUDir()  
jv#=JoyV()  
jvdir=JoyVDir()  
jyaw#=JoyYaw()  
jpitch#=JoyPitch()  
jroll#=JoyRoll()  
; Output joystick values  
Text 0,0,"Move joystick to output values onto screen"  
Text 0,20,"JoyU(): "+ju#  
Text 0,40,"JoyUDir(): "+judir  
Text 0,60,"JoyV(): "+jv#  
Text 0,80,"JoyVDir(): "+jvdir  
Text 0,100,"JoyYaw(): "+jyaw#  
Text 0,120,"JoyPitch(): "+jpitch#  
Text 0,140,"JoyRoll(): "+jroll#  
Flip  
Cls  
Wend  
end function
;; This command returns the direction of the Y-axis of the joystick being pressed.  The value is -1 (up) or 1 (down). The value returned is an integer number. See  the example. Perfect for digital joysticks.
;; As with any joystick command, you MUST have a DirectX compatible joystick plugged  in and properly configured within Windows for it to work. See your joystick  documentation for more information.
;;param port = number of joystick port to check (optional)
function JoyYDir ([port])
; JoyYDir() example  
While Not KeyHit(1)  
Cls  
Text 0,0,"Joy Y Direction: " + JoyYDir()  
Wend  
end function
;; This command returns the value of the x-axis of the joystick. The range  is -1 to 1 (Max to none). The value returned is a floating point number. See  the example.
;; As with any joystick command, you MUST have a DirectX compatible joystick plugged  in and properly configured within Windows for it to work. See your joystick  documentation for more information.
;;param port = number of joystick port to check (optional)
function JoyZ ([port])
; JoyZ() example  
While Not KeyHit(1)  
Cls  
Text 0,0,"Joy Z Value: " + JoyZ()  
Wend  
end function
;; This command returns the direction of the Z-axis of the joystick being pressed.  The value is -1 (up) or 1 (down). The value returned is an integer number. See  the example. Perfect for digital joysticks.
;; As with any joystick command, you MUST have a DirectX compatible joystick plugged  in and properly configured within Windows for it to work. See your joystick  documentation for more information.
;;param port = number of joystick port to check (optional)
function JoyZDir ([port])
; JoyZDir() example  
While Not KeyHit(1)  
Cls  
Text 0,0,"Joy Z Direction: " + JoyZDir()  
Wend  
end function
;; This command (similar to its counterparts MouseDown  and JoyDown) is used to detect if a key is being held  down. This command returns a 0 if the key is not held down, a 1 if the key is  held down.
;; See Also: ScanCodes
;;param scancode = corresponding key scancode
function KeyDown (scancode)
; KeyDown() example  
Print "Hold down ENTER key!"  
Delay 3000  
While Not KeyHit(1)  
If KeyDown(28) Then  
Print "Enter is being pressed!"  
Else  
Print  
End If  
Wend  
end function
;; This command returns the number of times a specified key has been hit since  the last time you called the KeyHit() command. Check the ScanCodes for a complete listing of scancodes.
;;param scancode = the scancode for the key to test
function KeyHit (scancode)
; KeyHit Example  
; Set up the timer  
current=MilliSecs()  
Print "Press ESC a bunch of times for five seconds..."  
; Wait 5 seconds  
While MilliSecs() < current+5000  
Wend  
; Print the results  
Print "Pressed ESC " + KeyHit(1) + " times."  
end function
;; If you haven't read up on the TYPE command, you might  want to do so before continuing.
;; Use this to assign a custom Type object to the last object in the collection.  See the example.
;; See also: <a class=small href=Type.htm>Type</a>, <a class=small href=New.htm>New</a>, <a class=small href=Before.htm>Before</a>, <a class=small href=After.htm>After</a>, <a class=small href=First.htm>First</a>, <a class=small href=Each.htm>Each</a>, <a class=small href=Insert.htm>Insert</a>, <a class=small href=Delete.htm>Delete</a>.
;;param type_variable = the actual Type name, not the custom Type name
function Last type_variable
; Define a crafts Type  
Type crafts  
Field x  
Field y  
Field dead  
Field graphic  
End Type  
; Create 100 crafts, with the unique name of alien  
For t = 1 To 100  
alien.crafts = New crafts  
alien\x = Rnd(0,640)  
alien\y = Rnd(0,480)  
alien\dead = 0  
alien\graphic = 1  
Next  
; Move to the first object  
alien.crafts = First crafts  
Print alien\x  
Print alien\y  
Print alien\dead  
Print alien\graphic  
; move to the next alien object  
alien = After alien  
Print alien\x  
Print alien\y  
Print alien\dead  
Print alien\graphic  
; move to the last alien object  
alien.crafts = Last crafts  
Print alien\x  
Print alien\y  
Print alien\dead  
Print alien\graphic  
; move to the second to the last alien object  
alien = Before alien  
Print alien\x  
Print alien\y  
Print alien\dead  
Print alien\graphic  
end function
;; Use this command to get a certain number of the leftmost letters of a string.  You will use this to truncate strings to make them fit somewhere, or to control  the number of characters input.
;;param string$ = any valid string variable
;;param length = a valid integer value up to the length of the string.
function Left$ (string$, length)
name$="Shane Monroe"  
Print "The left 3 letters of your name are: " + Left$(name$,3)  
end function
;; name$="Shane Monroe"
;; Print "There are " + Len(name$) + " characters in your name!"
;; Click <a href=http://www.blitzbasic.co.nz/b3ddocs/command.php?name=Len&ref=comments target=_blank>here</a> to view the latest version of this page online</body>
;; </html>
;;param string$ = any valid string variable
function Len (string$)
end function
;; This command draws a line, in the current drawing color, from one point  on the screen to another (from the x,y to x1,y1 location). See example.
;;param x=starting x location of the line
;;param y=starting y location of the line
;;param x1=ending x location of the line
;;param y1=ending y location of the line
function Line x,y,x1,y1
; Line example  
Graphics 800,600,16  
; Wait for ESC to hit  
While Not KeyHit(1)  
; Set a random color  
Color Rnd(255),Rnd(255),Rnd(255)  
; Draw a random line  
Line Rnd(800),Rnd(600),Rnd(800),Rnd(600)  
Wend  
end function
;; While similar to LoadImage, the LoadAnimImage  loads a single image that is made up of 'frames' of seperate images (presumably  to be used as frames of a graphic animation).
;; Like the LoadImage command, this command returns  a file handle - a unique number to denote the graphic. Use a variable (usually GLOBAL) to contain this number, as you will need it  to actually DRAW the image with the DrawImage command.  See LoadImage command for more details.
;; The imagestrip itself consists of 2 or more frames, arranged in a single graphic image. There is no spaces between the frames, and each frame must be the same width and height. When loaded, the frames will be indexed in a left-to-right, top-to-bottom fashion, starting in the top left corner. For examples, look at the file kaboom.bmp  or sparks.bmp included in the C:Program FilesBlitzBasicsamplesgraphics folder  of your computer. There are some free utilities floating around to help you  do this.
;; When drawing the image to the screen with the DrawImage  command, you specify which frame to draw with the frame parameter.
;; To actually make your image animate, you'll need to cycle through the frames  (like a flip book, cartoon, or any other video) quickly to give the illusion  of motion. Our example will show you how to use one of the sample imagestrips  and make it animate. While it may seem confusing, we are going to do some timer  work as well as a little weird math.
;; Please look over the example (if your like me, over and over :). Note: You may  need to change the location of the file to suit your system.
;; See also: <a class=small href=LoadImage.htm>LoadImage</a>.
;;param filename = string designating full path and filename to image.
;;param width=width in pixels of each frame in the image.
;;param height=height in pixels of each frame in the image.
;;param first=the frame to start with (usually 0)
;;param count=how many frames you are using of the imagestrip
function LoadAnimImage (filename,width,height,first,count)
; LoadAnimImage/MaskImage Example  
; With animation timers  
; Even though we don't have any functions, let's do variables global  
; One variable will hold the handle for the graphic, one will hold the  
; current frame we are displaying, and one will hold the milliseconds  
; timer so we can adjust the animation speed.  
Global gfxSparks, frmSparks, tmrSparks  
; Standard graphic declaration and double buffering setup  
Graphics 640,480,16  
SetBuffer BackBuffer()  
; Load the imagestrip up and denote the frames 32x32 - for a total of 3 frames  
gfxSparks=LoadAnimImage("c:Program FilesBlitzBasicsamplesGraphicsspark.bmp",32,32,0,3)  
; We mask the image's color pink to be the 'transparent' color - look at the  
; image in your favorite editor to see more why we use masking.  
MaskImage gfxSparks,255,0,255  
; Loop until ESC  
While Not KeyHit(1)  
Cls ; Standard clear screen  
; The next statment checks to see if 100 milliseconds has passes since we  
; last changed frames. Change the 100 to higher and lower values to  
; make the animation faster or slower.  
If MilliSecs() > tmrSparks + 100 Then  
tmrSparks=MilliSecs() ; 'reset' the timer  
frmSparks=( frmSparks + 1 ) Mod 3 ; increment the frame, flip to 0 if we are  out  
End If  
DrawImage gfxSparks,MouseX(),MouseY(),frmSparks ; draw the image  
Flip ; show the buffer  
Wend  
end function
;; There are a hundred and one uses for this command, but probably most often  used would be to display a title screen or some other 'one time viewing only'  image to the front buffer (as in our example).
;; You can also load to an image buffer or back buffer. The image is scaled to  match the buffer size. This command returns 1 if the command was successful,  0 if there was an error.
;;param buffer = system or image buffer
;;param filename$ = string containing full path and filename of image
function LoadBuffer (buffer, filename$)
; LoadBuffer example  
; Set graphics mode  
Graphics 800,600,16  
; Load an image directly to the front buffer (your location may be different)  
LoadBuffer (FrontBuffer(),"C:Program FilesBlitz Basicsampleslitzanoidgfx	itle.bmp")  
; wait for ESC so user gets to see the screen  
While Not KeyHit(1)  
Wend  
end function
;; Loads a font and returns a font handle.
;; You can then use the font handle  with commands such as SetFont and FreeFont.
;; Note: Blitz doesn't work with SYMBOL fonts, like Webdings and WingDings.
;;param fontname$ - name of font to be loaded, e.g. "arial"
;;param height - height of font in points (default is 12)
;;param bold - True to load bold version of font, False not to (default is False)
;;param italic - True to load italic version of font, False not to (default is False)
;;param underlined - True to load underlined version of font, False not to (default is False)
function LoadFont (fontname$[,height][,bold][,italic][,underlined])
; LoadFont/SetFont/FreeFont Example  
; ---------------------------------  
; Enable Graphics mode  
Graphics 800,600  
; Set global on font variables  
Global fntArial,fntArialB,fntArialI,fntArialU  
; Load fonts to a file handle variables  
fntArial=LoadFont("Arial",24)  
fntArialB=LoadFont("Arial",18,True)  
fntArialI=LoadFont("Arial",32,False,True)  
fntArialU=LoadFont("Arial",14,False,False,True)  
; Set the font and print text  
SetFont fntArial  
Text 400,0,"This is just plain Arial 24 point",True  
SetFont fntArialB  
Text 400,30,"This is bold Arial 18 point",True  
SetFont fntArialI  
Text 400,60,"This is italic Arial 32 point",True  
SetFont fntArialU  
Text 400,90,"This is underlined Arial 14 point",True  
; Standard 'wait for ESC' from user  
While Not KeyHit(1)  
Wend  
; Clear all the fonts from memory!  
FreeFont fntArial  
FreeFont fntArialB  
FreeFont fntArialI  
FreeFont fntArialU  
end function
;; This command loads an image from disk and assigns it a file handle. You  will use the DrawImage command to display the graphic  later. The demo version of Blitz Basic supports BMP files; the full retail version  of Blitz Basic supports JPG and PNG format as well.
;; Many multimedia loading commands for fonts, graphics, and sounds require the  use of FILE HANDLES. You'll need to have a good understanding of file handles  if you are going to successfully use Blitz Basic.
;; A file handle is a variable (usually GLOBAL) that holds  a unique identifier for a loaded item (font, image, sound, music, etc.). This  unique number is used later for subsequent operations to designate the loaded  item. This file handle allocates the memory to hold the item.
;; You will find file handles used all over Blitz. See the example for some well-documented  code.
;; Note that when you change the graphics mode using the Graphics command, all images that were loaded will be lost, and all handles will become invalid.  However, with BlitzPlus, the images will sometimes be retained.  Expect more control over this in the next BlitzPlus update.
;; See also: <a class=small href=LoadAnimImage.htm>LoadAnimImage</a>, <a class=small href=CreateImage.htm>CreateImage</a>, <a class=small href=FreeImage.htm>FreeImage</a>, <a class=small href=SaveImage.htm>SaveImage</a>, <a class=small href=DrawImage.htm>DrawImage</a>, <a class=small href=Graphics.htm>Graphics</a>.
;;param filename = string designating full path and filename to image.
function LoadImage (Filename)
; LoadImage and DrawImage example  
; Declare a variable to hold the graphic file handle  
Global gfxPlayer  
; Set a graphics mode  
Graphics 640,480,16  
; Set drawing operations for double buffering  
SetBuffer BackBuffer()  
; Load the image and assign its file handle to the variable  
; - This assumes you have a graphic called player.bmp in the  
; same folder as this source code  
gfxPlayer=LoadImage("player.bmp")  
; Let's do a loop where the graphic is drawn wherever the  
; mouse is pointing. ESC will exit.  
While Not KeyHit(1)  
Cls ; clear the screen  
DrawImage gfxPlayer,MouseX(),MouseY() ; Draw the image!  
Flip ; flip the image into view and clear the back buffer  
Wend  
end function
;; This command loads a sound file into memory. It returns a number if successful,  or 0 if there was a problem loading the sound. You must assign the value this  returns to a variable (preferably a Global variable)  for subsequent playback using (PlaySound). Look at the example.
;;param filename$ - name of sound file. Formats supported: raw/wav/mp3/ogg
function LoadSound (filename$)
; Assign a global variable for the sound  
Global sndPlayerDie  
; Load the sound file into memory  
sndPlayerDie=LoadSound("sounds/die.wav")  
; Play the sound  
PlaySound sndPlayerDie  
end function
;; This command is probably just here for compatibility with other BASIC languages.  LOCAL will let you specify that the variable you are defining is available ONLY  to the program or Function you are assigning it in.  In order to get a variable to be accessible anywhere in your program, you need  to make it GLOBAL. I say it is only here for compatibility  because whenever you assign a variable that isn't Global, it is automatically  assigned as a LOCAL variable. You can optionally assign a value to the variable  at the time of declaration. See example.
;; See also: <a class=small href=Global.htm>Global</a>, <a class=small href=Const.htm>Const</a>, <a class=small href=Dim.htm>Dim</a>.
;;param variable = any valid variable name
function Local variable
; Local example  
; set lives to 5 for the main program loop  
Local lives=5  
; Call a function  
while not keyhit(1)  
showlives()  
Wend  
Function showlives()  
; For this function, lives will be 10!  
Local lives=10  
Print lives  
End Function  
end function
;; Sometimes you want to place the PRINT and Input$ commands at a specific location on the screen.  This command locates the 'cursor' to the designated location.
;;param x=x coordinate on the screen
;;param y=y coordinate on the screen
function Locate x,y
; Locate example  
strName$=Input$("What is your name?")  
Locate 100,200  
Print "Hello there, " + strName$  
While Not KeyHit(1)  
Wend  
end function
;; After you use LockBuffer on a buffer, the only graphics commands you can  use are the read/write pixel commands ReadPixel, WritePixel, ReadPixelFast, WritePixelFast,  CopyPixelFast, and CopyPixel. You must UnlockBuffer before using other graphics commands or API calls, and you are advised to only keep the buffer locked for as long as it is needed.
;; The buffer parameter isn't required. If omitted, the default buffer set with SetBuffer will be used.
;; See the other commands for more information.
;; See also: <a class=small href=LockedPitch.htm>LockedPitch</a>, <a class=small href=LockedFormat.htm>LockedFormat</a>, <a class=small href=LockedPixels.htm>LockedPixels</a>, <a class=small href=ReadPixelFast.htm>ReadPixelFast</a>, <a class=small href=WritePixelFast.htm>WritePixelFast</a>, <a class=small href=UnlockBuffer.htm>UnlockBuffer</a>.
;;param buffer = any valid screen/image buffer (optional)
function LockBuffer buffer
; High Speed Graphics Commands  
Graphics 640,480,16  
; Draw a bunch of stuff on the screen  
For t= 1 To 1000  
Color Rnd(255),Rnd(255),Rnd(255)  
Rect Rnd(640),Rnd(480),Rnd(150),Rnd(150),Rnd(1)  
Next  
Delay 3000  
; Copy the top half of the screen over the bottom half  
; using fast pixels and locked buffers  
For x = 1 To 640  
For y = 1 To 240  
LockBuffer FrontBuffer()  
WritePixelFast x,y+241,ReadPixelFast(x,y)  
UnlockBuffer FrontBuffer()  
Next  
Next  
Delay 3000  
; Draw the left half of the screen over the right half  
; using the slower direct pixel access  
For x = 1 To 320  
For y = 1 To 480  
WritePixel x+320,y,ReadPixel(x,y)  
Next  
Next  
end function
;; Natural logarithm of x. This is the inverse of Exp( ).
;; y = Log( x ) means y satifies x = Exp( y ).
;; The base of the natural logarithm is e = Exp(1) = 2.71828...
;; See also Exp()
;;param x = any positive number.
function Log# ( x# )
; Log example  
; NaN means "Not a Number", the numerical result is invalid.  
; NOTE: All logarithm functions are related. For example, there is no  
;       Log8 function in Blitz, but we can easily make one.  
HidePointer  
Print "Log8( -1 ) = " + Log8( -1 )  
Print "Log8( 0  ) = " + Log8( 0 )  
Print  
; ... and now some well behaved numbers...  
x# = 1.0 / 4096.0   ; small positive number  
For n = 1 To 15  
Print "Log8( " + LSet( x, 13 ) + " ) = " + Log8( x )  
x = 8 * x  
Next  
WaitKey()  :  End  
Function Log8# ( x# )  
Return Log( x ) / Log( 8 )  
End Function  
end function
;; Common logarithm of x. This is the inverse of raising 10 to a power.
;; y = Log10( x ) means y satifies x = 10 ^ y.
;;param x = any positive number.
function Log# ( x# )
; Log10 example  
; NaN means "Not a Number", the numerical result is invalid.  
HidePointer  
; First, what happens with non-positive numbers...  
Print "Log10( -1 ) = " + Log10( -1 )  
Print "Log10( 0  ) = " + Log10( 0 )  
Print  
; ... and now some well behaved numbers...  
x# = 1.0 / 4096.0   ; small positive number  
For n = 1 To 15  
Print "Log10( " + LSet( x, 13 ) + " ) = " + Log10( x )  
x = 8 * x  
Next  
WaitKey()  :  End  
end function
;; This command sets up play back a sound file (.WAV or .MP3) in an endless  loop (like for background music). You must load a variable with a sound file  using the LoadSound command. Use a Global variable  to ensure your sound loop can be played from anywhere in your program. Note:  This command doesn't actually PLAY the sound loop, just sets it up for looping.  You still need to execute the PlaySound command  to hear the sound.
;;param sound_variable = variable previously assigned with a LoadSound command.
function LoopSound sound_variable
; Assign a global variable for the sound loop  
Global sndMusicLoop  
; Load the sound loop file into memory  
sndMusicLoop=LoadSound("sounds/loop1.wav")  
; Set the sound loop  
LoopSound sndMusicLoop  
PlaySound sndMusicLoop  
end function
;; This will take a string and convert it all to lower case letters. Pretty  straight forward.
;;param string$ = any valid string variable
function Lower$ (string$)
name$="ShAnE MoNrOe"  
Print "The original name is: " + name$  
Print "In lower case it is: " + Lower$(name$)  
end function
;; If you have a string that is say, 10 letters long, but you want to make  it a full 25 letters, padding the rest of the string with spaces, this command  will do so, leaving the original string value left justified.
;;param string$ = any valid string or string variable
;;param length = how long you want the new string to be (including padding)
function LSet$ (string$, length)
name$="Shane R. Monroe"  
Print "New Padded Name: '" + LSet$(name$,40) + "'"  
end function
;; Blitz Basic assumes that when you load an image (using LoadImage or LoadAnimImage)  for drawing (using DrawImage command), you want  the color black (RGB color 0,0,0) on your image to be transparent (or see through).  There WILL come a time when you want some other color to be that masked color.  This command will let you set that mask color using the color's RGB values (I  use Paint Shop Pro to determing the Red, Green, and Blue values). The example  is a bit bloated for other commands, but I'm pretty sure you'll understand.
;;param handle=the variable you assigned the handle to when you loaded the image.
;;param red=the red color value (0-255)
;;param green=the green color value (0-255)
;;param blue=the blue color value (0-255)
function MaskImage handle,red,green,blue
; LoadAnimImage/MaskImage Example  
; With animation timers  
; Even though we don't have any functions, let's do variables global  
; One variable will hold the handle for the graphic, one will hold the  
; current frame we are displaying, and one will hold the milliseconds  
; timer so we can adjust the animation speed.  
Global gfxSparks, frmSparks, tmrSparks  
; Standard graphic declaration and double buffering setup  
Graphics 640,480,16  
SetBuffer BackBuffer()  
; Load the imagestrip up and denote the frames 32x32 - for a total of 3 frames  
gfxSparks=LoadAnimImage("c:Program FilesBlitzBasicsamplesGraphicsspark.bmp",32,32,0,3)  
; We mask the image's color pink to be the 'transparent' color - look at the  
; image in your favorite editor to see more why we use masking.  
MaskImage gfxSparks,255,0,255  
; Loop until ESC  
While Not KeyHit(1)  
Cls ; Standard clear screen  
; The next statment checks to see if 100 milliseconds has passes since we  
; last changed frames. Change the 100 to higher and lower values to  
; make the animation faster or slower.  
If MilliSecs() > tmrSparks + 100 Then  
tmrSparks=MilliSecs() ; 'reset' the timer  
frmSparks=( frmSparks + 1 ) Mod 3 ; increment the frame, flip to 0 if we are  out  
End If  
DrawImage gfxSparks,MouseX(),MouseY(),frmSparks ; draw the image  
Flip ; show the buffer  
Wend  
end function
;; Use this command to grab a set of characters from within a string. You can  choose WHERE to start in the string, and how many characters to pick. You'll  probably use this to 'decode' or 'walk through' a string to get each character  out of it for conversion or validation. See the Example.
;;param string$ = any valid string
;;param offset = location within the string to start reading
;;param characters = how many characters to read frm the offset point
function Mid$ (string$, offset, characters)
name$="Shane Monroe"  
For T = 1 To Len(name$)  
Print Mid$(name$,t,1)  
Next  
end function

end function
;; When an image is loaded with LoadImage, the  image handle (the location within the image where the image is 'drawn from')  is always defaulted to the top left corner (coordinates 0,0). This means if  you draw an image that is 50x50 pixels at screen location 200,200, the image  will begin to be drawn at 200,200 and extend to 250,250.
;; This command moves the image handle from the 0,0 coordinate of the image to  the exact middle of the image. Therefore, in the same scenario above, if you  were to draw a 50x50 pixel image at screen location 200,200 with its image handle  set to Mid with this command, the image would start drawing at 175,175 and extend  to 225,225.
;; You can manual set the location of the image's handle using the HandleImage command. You can retrieve an image's  handle using the ImageXHandle and ImageYHandle. Finally, you can make all images  automatically load with the image handle set to middle using the AutoMidHandle command.
;; Note about the term 'handle'. There are two types of 'handles' we discuss in  these documents. One is the location within an image - as discussed in this  command. The other is a 'file handle', a variable used to hold an image, sound,  or font loaded with a command. See LoadImage for  more information about file handles.
;;param image = variable holding the file handle to the image
function MidHandle image
; MidHandle/ImageXHandle()/ImageYHandle()/AutoMidHandle  
; Initiate Graphics Mode  
Graphics 640,480,16  
; Set up the image file handle as a global  
Global gfxBall  
; Load the image - you may need to change the location of the file  
gfxBall=LoadImage ("C:Program FilesBlitz Basicsamplesall.bmp")  
; Until the user presses ESC key ...  
While Not KeyHit(1)  
Text 0,0,"Default Image Handle for gfxBall... Press ESC ..."  
Text 0,14,"X handle-" + ImageXHandle(gfxBall) ; Print the location of the image  handle x location  
Text 0,28,"Y handle-" + ImageYHandle(gfxBall) ; Print the location of the image  handle y location  
DrawImage gfxBall,200,200,0 ; draw the image at 200,200  
Wend  
; Clear the screen  
Cls  
; Set the ball's handle to the center of the image  
MidHandle gfxBall  
; Until the user presses ESC key ... show the new information  
While Not KeyHit(1)  
Text 0,0,"New Image Handle for gfxBall... Press ESC ..."  
Text 0,14,"X handle-" + ImageXHandle(gfxBall)  
Text 0,28,"Y handle-" + ImageYHandle(gfxBall)  
DrawImage gfxBall,200,200,0  
Wend  
; Makes all images load up with their handles in the center of the image  
AutoMidHandle True  
Cls  
; Load the image again  
gfxBall=LoadImage ("C:Program FilesBlitz Basicsamplesall.bmp")  
; Until the user presses ESC key ... show the new information  
While Not KeyHit(1)  
Text 0,0,"Automatic image handle of gfxBall... Press ESC ..."  
Text 0,14,"X handle-" + ImageXHandle(gfxBall)  
Text 0,28,"Y handle-" + ImageYHandle(gfxBall)  
DrawImage gfxBall,200,200,0  
Wend  
end function
;; This command will return to you the system timer value in milliseconds.
;; This is incredibly useful for precision timing of events. By reading this value  into a variable, and checking it against the CURRENT time in milliseconds, you  can perform 'waits' or check time lapses with a high degree of accuracy. A common  use of this command is to seed the random number generator with the SeedRnd command.
;;param None
function Millisecs()
; This prints STILL WAITING! for three seconds then ends.  
oldTime=MilliSecs()  
While MilliSecs() < oldTime + 3000  
Print "Still waiting!"  
Wend  
end function
;; Basically, this will divide your number as many times as possible by the  divisor, then return you the remaining amount.
;;param None
function Mod
; MOD Example  
; Divide 10 by 3 until you reach a point that you can't ; Then print the remaining  value - in this case, 1 Print 10 MOD 3  
end function
;; This command (and its counterparts KeyDown and JoyDown) is used to detect if a mouse button is being  held down. You must check for each mouse button independantly with its corresponding  number (unlike KeyDown which returns WHICH key is being held down). Also see MouseHit.
;;param button = 1: Left Button, 2: Right Button, 3: Middle Button
function MouseDown (button)
; MouseDown Example  
; Until user presses ESC, show the mouse button pressed  
While Not KeyHit(1)  
button$="No"  
If MouseDown(1) Then button$="Left"  
If MouseDown(2) Then button$="Right"  
If MouseDown(3) Then button$="Middle"  
Print button$ + " mouse button pressed!"  
Wend  
end function
;; This command returns the number of times a specified mouse button has been  hit since the last time you called the MouseHit() command. Also see KeyHit and JoyHit.
;;param button = button code (1=Left, 2=Right, 3-Middle)
function MouseHit (button)
; MouseHit Example  
; Set up the timer  
current=MilliSecs()  
Print "Press left mouse button a bunch of times for five seconds..."  
; Wait 5 seconds  
While MilliSecs() < current+5000  
Wend  
; Print the results  
Print "Pressed left button " + MouseHit(1) + " times."  
end function

end function
;; This command returns the X location of the mouse on the screen. This position is always from the range 0 to GraphicsWidth( ) - 1. You can use this command in combination with DrawImage to make a custom mouse pointer, or to control something on the screen directly with the mouse.
;; See also: <a class=small href=MouseY.htm>MouseY</a>, <a class=small href=MouseZ.htm>MouseZ</a>.
;;param None
function MouseX()
Graphics 640,480  
SetBuffer BackBuffer()  
Repeat  
Cls  
Text 320,0,"Click to reset mouse",True  
Text 0,0,"Mouse X:"+MouseX()  
Text 0,10,"Mouse Y:"+MouseY()  
If MouseDown(1) Or MouseDown(2) Then MoveMouse 320,240  
Text MouseX(),MouseY(),"X",True,True  
Flip  
Until KeyHit(1)  
End  
end function
;; Often you'd like to find the difference between where the mouse WAS to where it is NOW. You can use this command and MouseXSpeed() in pairs to find out the changes in the mouse location between calls.
;; You really have to use these commands TWICE to get anything out of them. Each call you make returns the difference in location since the LAST time you called it.
;; In this example it's called every loop and therefore allows you to have infinite mouse movement without the screen size restrictions.
;; See also: <a class=small href=MouseYSpeed.htm>MouseYSpeed</a>.
;;param None.
function MouseXSpeed()
Graphics 640,480  
SetBuffer BackBuffer()  
x=320  
y=240  
; infinite mouse movement  
Repeat  
Cls  
xs=MouseXSpeed() ; see how far the mouse has been moved  
ys=MouseYSpeed()  
MoveMouse 320,240 ;put the mouse back in the middle of the screen  
x=x+xs ;adjust mouse co-ords  
y=y+ys  
If x>GraphicsWidth()-1 Then x=x-GraphicsWidth() ;wrap screen  
If x<0 Then x=x+GraphicsWidth()  
If y<0 Then y=y+GraphicsHeight()  
If y>GraphicsHeight()-1 Then y=y-GraphicsHeight()  
Text x,y,"X",True,True  
Flip  
Until KeyHit(1)  
End  
end function
;; This command returns the Y location of the mouse on the screen. This position is always from the range 0 to GraphicsHeight( ) - 1. You can use this command in combination with DrawImage to make a custom mouse pointer, or to control something on the screen directly with the mouse.
;; See also: <a class=small href=MouseX.htm>MouseX</a>, <a class=small href=MouseZ.htm>MouseZ</a>.
;;param None
function MouseY()
Graphics 640,480  
SetBuffer BackBuffer()  
Repeat  
Cls  
Text 320,0,"Click to reset mouse",True  
Text 0,0,"Mouse X:"+MouseX()  
Text 0,10,"Mouse Y:"+MouseY()  
If MouseDown(1) Or MouseDown(2) Then MoveMouse 320,240  
Text MouseX(),MouseY(),"X",True,True  
Flip  
Until KeyHit(1)  
End  
end function
;; Often you'd like to find the difference between where the mouse WAS to where it is NOW. You can use this command and MouseYSpeed() in pairs to find out the changes in the mouse location between calls.
;; You really have to use these commands TWICE to get anything out of them. Each call you make returns the difference in location since the LAST time you called it.
;; In this example it's called every loop and therefore allows you to have infinite mouse movement without the screen size restrictions.
;; See also: <a class=small href=MouseXSpeed.htm>MouseXSpeed</a>.
;;param None.
function MouseYSpeed()
Graphics 640,480  
SetBuffer BackBuffer()  
x=320  
y=240  
; infinite mouse movement  
Repeat  
Cls  
xs=MouseXSpeed() ; see how far the mouse has been moved  
ys=MouseYSpeed()  
MoveMouse 320,240 ;put the mouse back in the middle of the screen  
x=x+xs ;adjust mouse co-ords  
y=y+ys  
If x>GraphicsWidth()-1 Then x=x-GraphicsWidth() ;wrap screen  
If x<0 Then x=x+GraphicsWidth()  
If y<0 Then y=y+GraphicsHeight()  
If y>GraphicsHeight()-1 Then y=y-GraphicsHeight()  
Text x,y,"X",True,True  
Flip  
Until KeyHit(1)  
End  
end function
;; MouseZ returns the current position of the mouse wheel on a suitable mouse.  It starts off at zero when your program begins. The value of MouseZ increases  as you move the wheel away from you and decreases as you move it towards you.
;; See also: <a class=small href=MouseX.htm>MouseX</a>, <a class=small href=MouseY.htm>MouseY</a>.
;;param None.
function MouseZ()
Graphics 640, 480, 0, 2  
SetBuffer BackBuffer ()  
Repeat  
Flip:Cls  
Text 20, 20, "Mouse wheel position: " + MouseZ ()  
Until KeyHit (1)  
End  
end function
;; MouseZSpeed returns -1 if the mousewheel on a suitable mouse is being rolled  backwards (towards user), 0 if it is not being moved, and 1 if it is being rolled  forwards.
;;param None.
function MouseZSpeed()
Graphics 640, 480, 0, 2  
SetBuffer BackBuffer ()  
Repeat  
Cls  
Select MouseZSpeed ()  
Case -1  
result$ = "Backwards"  
Case 0  
; result$ = "No movement"  
Case 1  
result$ = "Forwards"  
End Select  
Text 20, 10, "NOTE: MouseZSpeed () = 0 is not listed here, to avoid confusion!"  
Text 20, 40, result$  
Flip  
Until KeyHit (1)  
End  
end function
;; Although the mouse isn't visible on the screen, the mouse location is still  being tracked and you can attach a graphic to it. However, there are times when  you want to put the pointer to a specific location on the screen. Use this command  to move the mouse to a designated location.
;;param x = the x coordinate on the screen to move the mouse
;;param y = the y coordinate on the screen to move the mouse
function MoveMouse x,y
Graphics 640,480  
SetBuffer BackBuffer()  
Repeat  
Cls  
Text 320,0,"Click to reset mouse",True  
Text 0,0,"Mouse X:"+MouseX()  
Text 0,10,"Mouse Y:"+MouseY()  
If MouseDown(1) Or MouseDown(2) Then MoveMouse 320,240  
Text MouseX(),MouseY(),"X",True,True  
Flip  
Until KeyHit(1)  
End  
end function
;; Returns the height of a movie.
;; See also: <a class=small href=OpenMovie.htm>OpenMovie</a>, <a class=small href=DrawMovie.htm>DrawMovie</a>, <a class=small href=CloseMovie.htm>CloseMovie</a>, <a class=small href=MoviePlaying.htm>MoviePlaying</a>, <a class=small href=MovieWidth.htm>MovieWidth</a>.
;;param movie - movie handle
function MovieHeight( movie )
; Movie Commands Example  
; ======================  
; This demonstrates the following commands:  
;  
;	OpenMovie  
;	MovieHeight  
;	MovieWidth  
;	MoviePlaying  
;	DrawMovie  
; Some constants to start with  
Const WIDTH = 640  
Const HEIGHT = 480  
; First of all, set up the graphics  
Graphics WIDTH, HEIGHT  
SetBuffer BackBuffer()  
ClsColor 0,0,0  
Color 0,255,0  
; Next, open the movie file.  Feel free to change this to an AVI or MPEG file.  
movie=OpenMovie("media/hat.gif")  
; check to see if it loaded okay  
If movie=0 Then RuntimeError "Error - Movie not loaded!"  
If Not(MoviePlaying(movie)) Then RuntimeError "Error - Movie not playing!"  
;Now determine the size of the movie  
w=MovieWidth(movie)     ; the width of the movie  
h=MovieHeight(movie)    ; the height of the movie  
; Now set up the starting position and timing variables  
x=(WIDTH-w)/2           ; the x position of the movie on screen  
y=(HEIGHT-h-100)/2      ; the y position of the movie on screen  
period=100              ; the interval between frames  
time=MilliSecs()        ; the time of the last frame update  
; And here's the main loop  
Repeat  
; Wait for the specified period  
; GIFs have no timing info, and as such will redraw the next frame on each call to DrawMovie.  
; AVIs and MPEGs do have timing info, and as such will redraw the most recent frame on each call to DrawMovie.  
; Ergo, this time limiter only has an impact, and is only required for GIFs.  
Repeat  
; do nothing  
Until MilliSecs()-time>=period  
time=MilliSecs()    ; save the current time for the next frame  
; Handle keyboard inputs  
; CONTROL adjusts the speed with which we do stuff  
If KeyDown(29) Or KeyDown(157) Then  
change=5  
Else  
change=1  
End If  
; SHIFT means we're dealing with the size  
If KeyDown(42) Or KeyDown(54) Then  
If KeyDown(203) And w>change-1 Then w=w-change  
If KeyDown(205) And x+w+change < WIDTH Then w=w+change  
If KeyDown(200) And h>change-1 Then h=h-change  
If KeyDown(208) And y+h+change < HEIGHT Then h=h+change  
Else  
; otherwise it's the position that we're changing  
If KeyDown(203) And x>change-1 Then x=x-change  
If KeyDown(205) And x+w+change < WIDTH Then x=x+change  
If KeyDown(200) And y>change-1 Then y=y-change  
If KeyDown(208) And y+h+change < HEIGHT Then y=y+change  
EndIf  
; +/- to change the animation speed  
If ( KeyDown(13) Or KeyDown(78) ) And period>change Then period=period-change  
If ( KeyDown(12) Or KeyDown(74) ) And period < 500 Then period=period+change  
; Redraw the screen, by...  
Cls                         ; clear the screen  
DrawMovie movie,x,y,w,h     ; draw the movie  
; draw the instructions  
Text 0,0,"Use the arrow keys to reposition the movie."  
Text 0,20,"Hold SHIFT with the arrow keys to resize."  
Text 0,40,"Use + or - or control the animation speed."  
Text 0,60,"Hold CONTROL to resize, move, or change speed faster."  
Text 0,80,"Press ESCAPE to exit."  
Text 0,100,"Current Command Syntax: DrawMovie(movie, " + x + ","+ y + "," + w + "," + h + ")"  
; Flip the buffers  
Flip  
Until KeyHit(1) ; Escape to exit  
; Remove the movie from memory before closing down  
CloseMovie(movie)  
End ; bye!  
end function
;; Returns True if the specified movie is playing.
;; See also: <a class=small href=OpenMovie.htm>OpenMovie</a>, <a class=small href=DrawMovie.htm>DrawMovie</a>, <a class=small href=CloseMovie.htm>CloseMovie</a>, <a class=small href=MovieWidth.htm>MovieWidth</a>, <a class=small href=MovieHeight.htm>MovieHeight</a>.
;;param movie - movie handle
function MoviePlaying( movie )
; Movie Commands Example  
; ======================  
; This demonstrates the following commands:  
;  
;	OpenMovie  
;	MovieHeight  
;	MovieWidth  
;	MoviePlaying  
;	DrawMovie  
; Some constants to start with  
Const WIDTH = 640  
Const HEIGHT = 480  
; First of all, set up the graphics  
Graphics WIDTH, HEIGHT  
SetBuffer BackBuffer()  
ClsColor 0,0,0  
Color 0,255,0  
; Next, open the movie file.  Feel free to change this to an AVI or MPEG file.  
movie=OpenMovie("media/hat.gif")  
; check to see if it loaded okay  
If movie=0 Then RuntimeError "Error - Movie not loaded!"  
If Not(MoviePlaying(movie)) Then RuntimeError "Error - Movie not playing!"  
;Now determine the size of the movie  
w=MovieWidth(movie)     ; the width of the movie  
h=MovieHeight(movie)    ; the height of the movie  
; Now set up the starting position and timing variables  
x=(WIDTH-w)/2           ; the x position of the movie on screen  
y=(HEIGHT-h-100)/2      ; the y position of the movie on screen  
period=100              ; the interval between frames  
time=MilliSecs()        ; the time of the last frame update  
; And here's the main loop  
Repeat  
; Wait for the specified period  
; GIFs have no timing info, and as such will redraw the next frame on each call to DrawMovie.  
; AVIs and MPEGs do have timing info, and as such will redraw the most recent frame on each call to DrawMovie.  
; Ergo, this time limiter only has an impact, and is only required for GIFs.  
Repeat  
; do nothing  
Until MilliSecs()-time>=period  
time=MilliSecs()    ; save the current time for the next frame  
; Handle keyboard inputs  
; CONTROL adjusts the speed with which we do stuff  
If KeyDown(29) Or KeyDown(157) Then  
change=5  
Else  
change=1  
End If  
; SHIFT means we're dealing with the size  
If KeyDown(42) Or KeyDown(54) Then  
If KeyDown(203) And w>change-1 Then w=w-change  
If KeyDown(205) And x+w+change < WIDTH Then w=w+change  
If KeyDown(200) And h>change-1 Then h=h-change  
If KeyDown(208) And y+h+change < HEIGHT Then h=h+change  
Else  
; otherwise it's the position that we're changing  
If KeyDown(203) And x>change-1 Then x=x-change  
If KeyDown(205) And x+w+change < WIDTH Then x=x+change  
If KeyDown(200) And y>change-1 Then y=y-change  
If KeyDown(208) And y+h+change < HEIGHT Then y=y+change  
EndIf  
; +/- to change the animation speed  
If ( KeyDown(13) Or KeyDown(78) ) And period>change Then period=period-change  
If ( KeyDown(12) Or KeyDown(74) ) And period < 500 Then period=period+change  
; Redraw the screen, by...  
Cls                         ; clear the screen  
DrawMovie movie,x,y,w,h     ; draw the movie  
; draw the instructions  
Text 0,0,"Use the arrow keys to reposition the movie."  
Text 0,20,"Hold SHIFT with the arrow keys to resize."  
Text 0,40,"Use + or - or control the animation speed."  
Text 0,60,"Hold CONTROL to resize, move, or change speed faster."  
Text 0,80,"Press ESCAPE to exit."  
Text 0,100,"Current Command Syntax: DrawMovie(movie, " + x + ","+ y + "," + w + "," + h + ")"  
; Flip the buffers  
Flip  
Until KeyHit(1) ; Escape to exit  
; Remove the movie from memory before closing down  
CloseMovie(movie)  
End ; bye!  
end function
;; Returns the width of a movie.
;; See also: <a class=small href=OpenMovie.htm>OpenMovie</a>, <a class=small href=DrawMovie.htm>DrawMovie</a>, <a class=small href=CloseMovie.htm>CloseMovie</a>, <a class=small href=MoviePlaying.htm>MoviePlaying</a>, <a class=small href=MovieHeight.htm>MovieHeight</a>.
;;param movie - movie handle
function MovieWidth( movie )
; Movie Commands Example  
; ======================  
; This demonstrates the following commands:  
;  
;	OpenMovie  
;	MovieHeight  
;	MovieWidth  
;	MoviePlaying  
;	DrawMovie  
; Some constants to start with  
Const WIDTH = 640  
Const HEIGHT = 480  
; First of all, set up the graphics  
Graphics WIDTH, HEIGHT  
SetBuffer BackBuffer()  
ClsColor 0,0,0  
Color 0,255,0  
; Next, open the movie file.  Feel free to change this to an AVI or MPEG file.  
movie=OpenMovie("media/hat.gif")  
; check to see if it loaded okay  
If movie=0 Then RuntimeError "Error - Movie not loaded!"  
If Not(MoviePlaying(movie)) Then RuntimeError "Error - Movie not playing!"  
;Now determine the size of the movie  
w=MovieWidth(movie)     ; the width of the movie  
h=MovieHeight(movie)    ; the height of the movie  
; Now set up the starting position and timing variables  
x=(WIDTH-w)/2           ; the x position of the movie on screen  
y=(HEIGHT-h-100)/2      ; the y position of the movie on screen  
period=100              ; the interval between frames  
time=MilliSecs()        ; the time of the last frame update  
; And here's the main loop  
Repeat  
; Wait for the specified period  
; GIFs have no timing info, and as such will redraw the next frame on each call to DrawMovie.  
; AVIs and MPEGs do have timing info, and as such will redraw the most recent frame on each call to DrawMovie.  
; Ergo, this time limiter only has an impact, and is only required for GIFs.  
Repeat  
; do nothing  
Until MilliSecs()-time>=period  
time=MilliSecs()    ; save the current time for the next frame  
; Handle keyboard inputs  
; CONTROL adjusts the speed with which we do stuff  
If KeyDown(29) Or KeyDown(157) Then  
change=5  
Else  
change=1  
End If  
; SHIFT means we're dealing with the size  
If KeyDown(42) Or KeyDown(54) Then  
If KeyDown(203) And w>change-1 Then w=w-change  
If KeyDown(205) And x+w+change < WIDTH Then w=w+change  
If KeyDown(200) And h>change-1 Then h=h-change  
If KeyDown(208) And y+h+change < HEIGHT Then h=h+change  
Else  
; otherwise it's the position that we're changing  
If KeyDown(203) And x>change-1 Then x=x-change  
If KeyDown(205) And x+w+change < WIDTH Then x=x+change  
If KeyDown(200) And y>change-1 Then y=y-change  
If KeyDown(208) And y+h+change < HEIGHT Then y=y+change  
EndIf  
; +/- to change the animation speed  
If ( KeyDown(13) Or KeyDown(78) ) And period>change Then period=period-change  
If ( KeyDown(12) Or KeyDown(74) ) And period < 500 Then period=period+change  
; Redraw the screen, by...  
Cls                         ; clear the screen  
DrawMovie movie,x,y,w,h     ; draw the movie  
; draw the instructions  
Text 0,0,"Use the arrow keys to reposition the movie."  
Text 0,20,"Hold SHIFT with the arrow keys to resize."  
Text 0,40,"Use + or - or control the animation speed."  
Text 0,60,"Hold CONTROL to resize, move, or change speed faster."  
Text 0,80,"Press ESCAPE to exit."  
Text 0,100,"Current Command Syntax: DrawMovie(movie, " + x + ","+ y + "," + w + "," + h + ")"  
; Flip the buffers  
Flip  
Until KeyHit(1) ; Escape to exit  
; Remove the movie from memory before closing down  
CloseMovie(movie)  
End ; bye!  
end function
;; First off, this ONLY works when you have joined a network game via StartNetGame or JoinNetGame  and you have created a player via CreateNetPlayer  (you must create a player, even if it is just to lurk). You must've received  the message already, determined by the RecvNetMsg()  command - and probably determined the type of message with (NetMsgType().
;; The string value returned from this command is the actual message text that  was sent.
;; You will use NetMsgType(), NetMsgFrom(), and NetMsgTo()  to get other important information from the message and act on it.
;; The example requires that you run it on a remote machine while the local computer  runs the example in the SendNetMsg command.
;;param None.
function NetMsgData$()
; NetMsgData$() example  
; --------------------  
; Run this program on the REMOTE computer to 'watch'  
; the activity of the SendNetMsg example. Run that  
; example on local machine.  
;  
; This program will tell you when a player involved in  
; the game hits a wall ...  
; We'll use this instead of JoinHostGame - make it easier  
StartNetGame()  
; Create a player - a player must be created to  
; receive mesages!  
playerID=CreateNetPlayer("Shane")  
; Loop and get status  
While Not KeyHit(1)  
; Check to see if we've received a message  
If RecvNetMsg() Then  
; if we did, let's figure out what type it is  
; we know it will be a user message, though  
msgType=NetMsgType()  
; 1-99 means a user message  
If msgType>0 And msgType<100 Then  
; Let's see who the message was from  
msgFrom=NetMsgFrom()  
; Let's get the message!  
msgData$=NetMsgData$()  
; Print the message  
Print msgData$  
Print "(Message was to:"+ NetMsgTo() + ")"  
End If  
End If  
Wend  
end function

end function
;; First off, this ONLY works when you have joined a network game via StartNetGame or JoinNetGame  and you have created a player via CreateNetPlayer  (you must create a player, even if it is just to lurk). You must've received  the message already, determined by the RecvNetMsg()  command - and probably determined the type of message with (NetMsgType().
;; The value returned from this command denotes the sender's ID number assigned  to them when they were created with CreateNetPlayer  command. Use this to perform actions on the player on the local machine.
;; You will use NetMsgType(), NetMsgTo(), and NetMsgData$()  to get other important information from the message and act on it.
;; The example requires that you run it on a remote machine while the local computer  runs the example in the SendNetMsg command.
;;param None.
function NetMsgFrom()
; NetMsgFrom() example  
; --------------------  
; Run this program on the REMOTE computer to 'watch'  
; the activity of the SendNetMsg example. Run that  
; example on local machine.  
;  
; This program will tell you when a player involved in  
; the game hits a wall ...  
; We'll use this instead of JoinHostGame - make it easier  
StartNetGame()  
; Create a player - a player must be created to  
; receive mesages!  
playerID=CreateNetPlayer("Shane")  
; Loop and get status  
While Not KeyHit(1)  
; Check to see if we've received a message  
If RecvNetMsg() Then  
; if we did, let's figure out what type it is  
; we know it will be a user message, though  
msgType=NetMsgType()  
; 1-99 means a user message  
If msgType>0 And msgType<100 Then  
; Let's see who the message was from  
msgFrom=NetMsgFrom()  
; Let's get the message!  
msgData$=NetMsgData$()  
; Print the message  
Print msgData$  
End If  
End If  
Wend  
end function

end function
;; First off, this ONLY works when you have joined a network game via StartNetGame or JoinNetGame  and you have created a player via CreateNetPlayer  (you must create a player, even if it is just to lurk). You must've received  the message already, determined by the RecvNetMsg()  command - and probably determined the type of message with (NetMsgType().
;; The value returned from this command denotes the messages's intended recipient's  ID number assigned to them when they were created with CreateNetPlayer.
;; You will use NetMsgType(), NetMsgFrom(), and  NetMsgData$() to get other important information from the message and act  on it.
;; The example requires that you run it on a remote machine while the local computer  runs the example in the SendNetMsg command.
;;param None.
function NetMsgTo()
; NetMsgTo() example  
; --------------------  
; Run this program on the REMOTE computer to 'watch'  
; the activity of the SendNetMsg example. Run that  
; example on local machine.  
;  
; This program will tell you when a player involved in  
; the game hits a wall ...  
; We'll use this instead of JoinHostGame - make it easier  
StartNetGame()  
; Create a player - a player must be created to  
; receive mesages!  
playerID=CreateNetPlayer("Shane")  
; Loop and get status  
While Not KeyHit(1)  
; Check to see if we've received a message  
If RecvNetMsg() Then  
; if we did, let's figure out what type it is  
; we know it will be a user message, though  
msgType=NetMsgType()  
; 1-99 means a user message  
If msgType>0 And msgType<100 Then  
; Let's see who the message was from  
msgFrom=NetMsgFrom()  
; Let's get the message!  
msgData$=NetMsgData$()  
; Print the message  
Print msgData$  
Print "(Message was to:"+ NetMsgTo() + ")"  
End If  
End If  
Wend  
end function

end function
;; First off, this ONLY works when you have joined a network game via StartNetGame or JoinNetGame  and you have created a player via CreateNetPlayer  (you must create a player, even if it is just to lurk). You must've received  the message already, determined by the RecvNetMsg()  command.
;; The value returned from this command denotes the message; 1-99 means it is a  user message, 100 means a new player has joined the game, 101 means a player  has been deleted from the network game (NetMsgFrom()  returns the player deleted), 102 means the original host has left the game and  THIS machine is now the new host.
;; If you receive a 200, this means a fatal exception has occurred and you need  to exit the game.
;; You will use NetMsgFrom,  NetMsgTo, and NetMsgData$ to get the important  information from the message and act on it.
;; The example requires that you run it on a remote machine while the local computer  runs the example in the SendNetMsg command.
;;param None.
function NetMsgType()
; NetMsgType() example  
; --------------------  
; Run this program on the REMOTE computer to 'watch'  
; the activity of the SendNetMsg example. Run that  
; example on local machine.  
;  
; This program will tell you when a player involved in  
; the game hits a wall ...  
; We'll use this instead of JoinHostGame - make it easier  
StartNetGame()  
; Create a player - a player must be created to  
; receive mesages!  
playerID=CreateNetPlayer("Shane")  
; Loop and get status  
While Not KeyHit(1)  
; Check to see if we've received a message  
If RecvNetMsg() Then  
; if we did, let's figure out what type it is  
; we know it will be a user message, though  
msgType=NetMsgType()  
; 1-99 means a user message  
If msgType>0 And msgType<100 Then  
; Let's see who the message was from  
msgFrom=NetMsgFrom()  
; Let's get the message!  
msgData$=NetMsgData$()  
; Print the message  
Print msgData$  
End If  
End If  
Wend  
end function
;; First off, this ONLY works when you have joined a network game via StartNetGame or JoinNetGame  and you have created a player via CreateNetPlayer  (you must create a player, even if it is just to lurk).
;; Use this command in conjunction with the NetMsgFrom()  (to get the player's ID) command to check and see if the player in question  is on the local machine (as opposed to a remote machine). You may wish to act  differently in your program based on whether the message that came in was from  a local or remote machine.
;; You will use NetMsgType(), NetMsgFrom(), and NetMsgTo()  to get other important information from the message and act on it.
;; The example requires that you run it on a remote machine while the local computer  runs the example in the SendNetMsg command.
;;param playerID = a valid player ID number (get from the NetMsgFrom() command)
function NetPlayerLocal (playerID)
; NetPlayerLocal example  
; --------------------  
; Run this program on the REMOTE computer to 'watch'  
; the activity of the SendNetMsg example. Run that  
; example on local machine.  
;  
; This program will tell you when a player involved in  
; the game hits a wall ...  
; We'll use this instead of JoinHostGame - make it easier  
StartNetGame()  
; Create a player - a player must be created to  
; receive mesages!  
playerID=CreateNetPlayer("Shane")  
; Loop and get status  
While Not KeyHit(1)  
; Check to see if we've received a message  
If RecvNetMsg() Then  
; if we did, let's figure out what type it is  
; we know it will be a user message, though  
msgType=NetMsgType()  
; 1-99 means a user message  
If msgType>0 And msgType<100 Then  
; Let's see who the message was from  
msgFrom=NetMsgFrom()  
; Let's get the message!  
msgData$=NetMsgData$()  
; Print the message  
Print msgData$  
if NetPlayerLocal(NetMsgFrom()) then  
print "(This was sent from a local player)"  
end if  
End If  
End If  
Wend  
end function
;; First off, this ONLY works when you have joined a network game via StartNetGame or JoinNetGame  and you have created a player via CreateNetPlayer  (you must create a player, even if it is just to lurk).
;; Use this command in conjunction with the NetMsgFrom()  (to get the player's ID) command to derive the actual name of the player. This  command returns a string value.
;; You will use NetMsgType(), NetMsgFrom(), and NetMsgTo()  to get other important information from the message and act on it.
;; The example requires that you run it on a remote machine while the local computer  runs the example in the SendNetMsg command.
;;param playerID = a valid player ID number (get from the NetMsgFrom() command
function NetPlayerName$ (playerID)
; NetPlayerName$() example  
; --------------------  
; Run this program on the REMOTE computer to 'watch'  
; the activity of the SendNetMsg example. Run that  
; example on local machine.  
;  
; This program will tell you when a player involved in  
; the game hits a wall ...  
; We'll use this instead of JoinHostGame - make it easier  
StartNetGame()  
; Create a player - a player must be created to  
; receive mesages!  
playerID=CreateNetPlayer("Shane")  
; Loop and get status  
While Not KeyHit(1)  
; Check to see if we've received a message  
If RecvNetMsg() Then  
; if we did, let's figure out what type it is  
; we know it will be a user message, though  
msgType=NetMsgType()  
; 1-99 means a user message  
If msgType>0 And msgType<100 Then  
; Let's see who the message was from  
msgFrom=NetMsgFrom()  
; Let's get the message!  
msgData$=NetMsgData$()  
; Print the message  
Print msgData$  
Print "(Message was from:"+ NetPlayerName$(NetMsgFrom()) + ")"  
End If  
End If  
Wend  
end function
;; If you aren't familiar with the TYPE command, please refer to that before  reading about this command.
;; Creates a NEW object in a Type collection. Each call to this command automatically  inserts a new object into the specified Type collection. Check the example and  other Type commands for more information.
;; See also: <a class=small href=Type.htm>Type</a>, <a class=small href=Before.htm>Before</a>, <a class=small href=After.htm>After</a>, <a class=small href=First.htm>First</a>, <a class=small href=Last.htm>Last</a>, <a class=small href=Each.htm>Each</a>, <a class=small href=Insert.htm>Insert</a>, <a class=small href=Delete.htm>Delete</a>.
;;param type_variable = the actual Type name, not the custom Type name
function New type_variable
; Define the CHAIR Type  
Type CHAIR  
Field X  
Field Y  
Field HEIGHT  
End Type  
; Create 100 new chairs using FOR ... NEXT using the collection name of ROOM  
For tempx = 1 to 10  
For tempy = 1 to 10  
room.chair = New Chair  
room\x = tempx  
room\y = tempy  
room\height = Rnd(0,10) ; set a random height 0 to 10  
Next  
Next  
end function
;; This command closes the FOR ... NEXT loop, causing program execution to  start again at the FOR command unless the loop condition has been met (the last  value has been met). Check the example for more info. Note: Do NOT use the FOR  command's variable as a parameter (i.e. NEXT T) as you would in most BASIC languages.  Blitz will automatically match it with the nearest FOR command.
;; See also: <a class=small href=For.htm>For</a>, <a class=small href=To.htm>To</a>, <a class=small href=Step.htm>Step</a>, <a class=small href=Each.htm>Each</a>, <a class=small href=Exit.htm>Exit</a>, <a class=small href=While.htm>While</a>, <a class=small href=Repeat.htm>Repeat</a>.
;;param None
function Next
; Print the values 1 through 10  
For t = 1 To 10  
Print t  
Next  
; Print the values 1,3,5,7,9  
For t = 1 To 10 Step 2  
Print t  
Next  
end function
;; This command will return the NEXT file or folder from the currently open  directory (use ReadDir to open the desired folder  for reading). This will return a string containing the folder name or the filename  plus extention. Use FILETYPE to determine if it is  a file or folder. See ReadDir and CloseDir for more. You cannot move 'backwards' through  a directory, only forward. You might want to parse the contents of a directory  into an array for display, processing, etc.
;;param filehandle = valid filehandle assigned from the ReadDir command
function NextFile$ (filehandle)
; ReadDir/NextFile$/CloseDir example  
; Define what folder to start with ...  
folder$="C:"  
; Open up the directory, and assign the handle to myDir  
myDir=ReadDir(folder$)  
; Let's loop forever until we run out of files/folders to list!  
Repeat  
; Assign the next entry in the folder to file$  
file$=NextFile$(myDir)  
; If there isn't another one, let's exit this loop  
If file$="" Then Exit  
; Use FileType to determine if it is a folder (value 2) or a file and print  results  
If FileType(folder$+"\"+file$) = 2 Then  
Print "Folder:" + file$  
Else  
Print "File:" + file$  
End If  
Forever  
; Properly close the open folder  
CloseDir myDir  
; We're done!  
Print "Done listing files!"  
end function
;; The NOT operator is used to determine if a condition is FALSE instead of TRUE. This is  frequently used in WHILE loops and  IF statements to check for the NON-EXISTANCE of an event or value. We use  NOT frequently in our examples, inside WHILE loops to test if the ESC key has  been pressed.
;; See also: <a class=small href=And.htm>And</a>, <a class=small href=Or.htm>Or</a>, <a class=small href=Xor.htm>Xor</a>.
;;param None.
function Not
; NOT example  
; Loop until they press ESC  
While Not KeyHit(1) ; As long as ESC isn't pressed ...  
Print "Press ESC to quit!"  
Wend  
end function
;; Designates a Null Type object. Useful for making a Type variable point to nothing or checking if a Type object exists.  Also, useful for checking that there are still objects left on the end of a Type list using the After command.
;; Can used for testing and setting.
;; See also: <a class=small href=Type.htm>Type</a>, <a class=small href=New.htm>New</a>, <a class=small href=Delete.htm>Delete</a>, <a class=small href=After.htm>After</a>, <a class=small href=Before.htm>Before</a>, <a class=small href=First.htm>First</a>, <a class=small href=Last.htm>Last</a>.
;;param None
function Null
; Null example  
Type Alien  
Field x,y  
End Type  
a.Alien = New Alien  
If a <> Null Then Print "Alien exists!"  
Delete a  
if a = Null Then Print "Alien gone!"  
end function
;; This command opens the designated file and prepares it to be updated. The  file must exists since this function will not create a new file.
;; By using FilePos and SeekFile the position within the file that is being read  or written can be determined and also changed. This allows a file to be read  and updated without having to make a new copy of the file or working through  the whole file sequentially. This could be useful if you have created a database  file and you want to find and update just a few records within it.
;; The file handle that is returned is an integer value that the operating system  uses to identify which file is to be read and written to and must be passed  to the functions such as ReadInt() and WriteInt().
;; Note extreme care needs to be exercised when updating files that contain strings  since these are not fixed in length.
;; See also: <a class=small href=ReadFile.htm>ReadFile</a>, <a class=small href=WriteFile.htm>WriteFile</a>, <a class=small href=CloseFile.htm>CloseFile</a>, <a class=small href=SeekFile.htm>SeekFile</a>.
;;param filename$ = any valid path and filename. The returned value is the filehandle  which is used by other file handling commands.
function OpenFile (filename$)
; Changing part of a file using OpenFile, SeekFile and WriteInt  
; Open/create a file to Write  
fileout = WriteFile("mydata.dat")  
; Write the information to the file  
WriteInt( fileout, 1 )  
WriteInt( fileout, 2 )  
WriteInt( fileout, 3 )  
WriteInt( fileout, 4 )  
WriteInt( fileout, 5 )  
; Close the file  
CloseFile( fileout )  
DisplayFile( "The file as originally written", mydata.dat" )  
; Open the file and change the Third Integer  
file = OpenFile("mydata.dat")  
SeekFile( file, 8 ) ; Move to the third integer in the file  
WriteInt( file, 9999 ) ; Replace the original value with 9999  
CloseFile( file )  
DisplayFile( "The file after being midified", "mydata.dat" )  
WaitKey()  
; **** Function Definitions follow ****  
; Read the file and print it  
Function DisplayFile( Tittle$, Filename$ )  
Print tittle$  
filein = ReadFile( Filename$ )  
While Not Eof( filein )  
Number = ReadInt( filein )  
Print Number  
Wend  
CloseFile( filein )  
Print  
End Function  
end function
;; Movie support relies on DirectShow, so you will need to ensure the correct drivers are installed.
;; This command will open GIFs as well as AVI and MPEG movie files.  However, AVI and MPEG movie will start playing immediately once this command has been used, whereas GIFs do not.  See <a class=small href=../2d_commands/DrawMovie.htm>DrawMovie</a> for details on how to deal with GIF animation playback.
;; No error is raised if the specified file is not a movie file, or if the file does not exist, but the returned movie handle will be zero.
;; See also: <a class=small href=DrawMovie.htm>DrawMovie</a>, <a class=small href=CloseMovie.htm>CloseMovie</a>, <a class=small href=MoviePlaying.htm>MoviePlaying</a>, <a class=small href=MovieWidth.htm>MovieWidth</a>, <a class=small href=MovieHeight.htm>MovieHeight</a>.
;;param file$ - filename of movie
function OpenMovie( file$ )
; Movie Commands Example  
; ======================  
; This demonstrates the following commands:  
;  
;	OpenMovie  
;	MovieHeight  
;	MovieWidth  
;	MoviePlaying  
;	DrawMovie  
; Some constants to start with  
Const WIDTH = 640  
Const HEIGHT = 480  
; First of all, set up the graphics  
Graphics WIDTH, HEIGHT  
SetBuffer BackBuffer()  
ClsColor 0,0,0  
Color 0,255,0  
; Next, open the movie file.  Feel free to change this to an AVI or MPEG file.  
movie=OpenMovie("media/hat.gif")  
; check to see if it loaded okay  
If movie=0 Then RuntimeError "Error - Movie not loaded!"  
If Not(MoviePlaying(movie)) Then RuntimeError "Error - Movie not playing!"  
;Now determine the size of the movie  
w=MovieWidth(movie)     ; the width of the movie  
h=MovieHeight(movie)    ; the height of the movie  
; Now set up the starting position and timing variables  
x=(WIDTH-w)/2           ; the x position of the movie on screen  
y=(HEIGHT-h-100)/2      ; the y position of the movie on screen  
period=100              ; the interval between frames  
time=MilliSecs()        ; the time of the last frame update  
; And here's the main loop  
Repeat  
; Wait for the specified period  
; GIFs have no timing info, and as such will redraw the next frame on each call to DrawMovie.  
; AVIs and MPEGs do have timing info, and as such will redraw the most recent frame on each call to DrawMovie.  
; Ergo, this time limiter only has an impact, and is only required for GIFs.  
Repeat  
; do nothing  
Until MilliSecs()-time>=period  
time=MilliSecs()    ; save the current time for the next frame  
; Handle keyboard inputs  
; CONTROL adjusts the speed with which we do stuff  
If KeyDown(29) Or KeyDown(157) Then  
change=5  
Else  
change=1  
End If  
; SHIFT means we're dealing with the size  
If KeyDown(42) Or KeyDown(54) Then  
If KeyDown(203) And w>change-1 Then w=w-change  
If KeyDown(205) And x+w+change < WIDTH Then w=w+change  
If KeyDown(200) And h>change-1 Then h=h-change  
If KeyDown(208) And y+h+change < HEIGHT Then h=h+change  
Else  
; otherwise it's the position that we're changing  
If KeyDown(203) And x>change-1 Then x=x-change  
If KeyDown(205) And x+w+change < WIDTH Then x=x+change  
If KeyDown(200) And y>change-1 Then y=y-change  
If KeyDown(208) And y+h+change < HEIGHT Then y=y+change  
EndIf  
; +/- to change the animation speed  
If ( KeyDown(13) Or KeyDown(78) ) And period>change Then period=period-change  
If ( KeyDown(12) Or KeyDown(74) ) And period < 500 Then period=period+change  
; Redraw the screen, by...  
Cls                         ; clear the screen  
DrawMovie movie,x,y,w,h     ; draw the movie  
; draw the instructions  
Text 0,0,"Use the arrow keys to reposition the movie."  
Text 0,20,"Hold SHIFT with the arrow keys to resize."  
Text 0,40,"Use + or - or control the animation speed."  
Text 0,60,"Hold CONTROL to resize, move, or change speed faster."  
Text 0,80,"Press ESCAPE to exit."  
Text 0,100,"Current Command Syntax: DrawMovie(movie, " + x + ","+ y + "," + w + "," + h + ")"  
; Flip the buffers  
Flip  
Until KeyHit(1) ; Escape to exit  
; Remove the movie from memory before closing down  
CloseMovie(movie)  
End ; bye!  
end function
;; Use this command to open up a TCP/IP stream to the designated server and  port. If the open command was successful, the command returns a stream handle.  Otherwise it returns 0.
;; You can use this for a multitude of different 'internet' options. Obviously  to contact a TCP/IP host outside your own network, you'll need to be connected  to the Internet.
;; The IP address can be in the form of 1.2.3.4 or "www.domain.com".
;;param ip$=Address of stream
;;param port=TCP/IP Port Number
function OpenTCPStream (ip$,port)
; OpenTCPStream/CloseTCPStream Example  
Print "Connecting..."  
tcp=OpenTCPStream( "www.blitzbasement.com",80 )  
If Not tcp Print "Failed.":WaitKey:End  
Print "Connected! Sending request..."  
WriteLine tcp,"GET http://www.blitzbasement.com HTTP/1.0"  
WriteLine tcp,Chr$(10)  
If Eof(tcp) Print "Failed.":WaitKey:End  
Print "Request sent! Waiting for reply..."  
While Not Eof(tcp)  
Print ReadLine$( tcp )  
Wend  
If Eof(tcp)=1 Then Print "Success!" Else Print "Error!"  
CloseTCPStream tcp  
WaitKey  
End  
end function
;; A logical expression to return a boolean TRUE or FALSE comparison of expressions  or values. Use this to check two expressions or more and act upon its return  value. See the example.
;; See also: <a class=small href=And.htm>And</a>, <a class=small href=Not.htm>Not</a>, <a class=small href=Xor.htm>Xor</a>.
;;param None
function Or
; OR Example  
myNum1=Rnd(0,10)  
myNum2=Rnd(0,10)  
If myNum1 = 0 OR myNum2 = 0 then  
print "One of my numbers is a Zero"  
end if  
end function
;; This command sets a point of origin for all subsequent drawing commands.  This can be positive or negative.
;;param x = x offset value
;;param y = y offset value
function Origin x,y
; Origin example  
Graphics 800,600,16  
; Offset drawing options with origin command -200 in each direction  
Origin -200,-200  
; Wait for ESC to hit  
While Not KeyHit(1)  
; Draw an oval - SHOULD be at the exact center, but it isn't!  
Oval 400,300,50,50,1  
Wend  
end function
;; Use this to draw an oval shape at the screen coordinates of your choice.  You can make the oval solid or hollow.
;;param x = x coordinate on the screen to draw the oval
;;param y = y coordinate on the screen to draw the oval
;;param width = how wide to make the oval
;;param height = how high to make the oval
;;param [solid] = 1 to make the oval solid
function Oval x,y,width,height[,solid]
; Oval example  
Graphics 800,600,16  
; Wait for ESC to hit  
While Not KeyHit(1)  
; Set a random color  
Color Rnd(255),Rnd(255),Rnd(255)  
; Draw a random oval  
Oval Rnd(800),Rnd(600),Rnd(100),Rnd(100),Rnd(0,1)  
Wend  
end function
;; When you are playing a sound channel, there may come a time you wish to  pause the sound for whatever reason (like to play another sound effect). This  command does this - and the channel can be resumed with the ResumeChannel command. You can use StopChannel to actually halt the sound. This works  with any channel playback (WAV, MP3, MIDI, etc.).
;;param channel_handle = variable assigned to the channel when played
function PauseChannel channel_handle
; Channel examples  
Print "Loading sound ..."  
; Load the sample - you'll need to point this to a sound on your computer  
; For best results, make it about 5-10 seconds...  
sndWave=LoadSound("level1.wav")  
; Prepare the sound for looping  
LoopSound sndWave  
chnWave=PlaySound(sndWave)  
Print "Playing sound for 2 seconds ..."  
Delay 2000  
Print "Pausing sound for 2 seconds ..."  
PauseChannel chnWave  
Delay 2000  
Print "Restarting sound ..."  
ResumeChannel chnWave  
Delay 2000  
Print "Changing Pitch of sound ..."  
;StopChannel chnWave  
ChannelPitch chnWave, 22000  
Delay 2000  
Print "Playing new pitched sound ..."  
Delay 2000  
Print "Left speaker only"  
ChannelPan chnWave,-1  
Delay 2000  
Print "Right speaker only"  
ChannelPan chnWave,1  
Delay 2000  
Print "All done!"  
StopChannel chnWave  
end function
;; Reads a byte from a memory bank and returns the value.
;; A byte takes up  one byte of a memory bank. Values can be in the range 0 to 255.
;; See also: <a class=small href=PeekShort.htm>PeekShort</a>, <a class=small href=PeekInt.htm>PeekInt</a>, <a class=small href=PeekFloat.htm>PeekFloat</a>.
;;param bank - bank handle
;;param offset - offset in bytes, that the peek operation will be started at
function PeekByte(bank,offset)
; Bank Commands Example  
; ---------------------  
bnkTest=CreateBank(12)  
PokeByte bnkTest,0,Rand(255)  
PokeShort bnkTest,1,Rand(65535)  
PokeInt bnkTest,3,Rand(-2147483648,2147483647)  
PokeFloat bnkTest,7,0.5  
Print PeekByte(bnkTest,0)  
Print PeekShort(bnkTest,1)  
Print PeekInt(bnkTest,3)  
Print PeekFloat(bnkTest,7)  
FreeBank bnkTest  
end function
;; Reads a float from a memory bank and returns the value.
;; A float takes  up four bytes of a memory bank.
;; See also: <a class=small href=PeekByte.htm>PeekByte</a>, <a class=small href=PeekShort.htm>PeekShort</a>, <a class=small href=PeekInt.htm>PeekInt</a>.
;;param bank - bank handle
;;param offset - offset in bytes, that the peek operation will be started at
function PeekFloat(bank,offset)
; Bank Commands Example  
; ---------------------  
bnkTest=CreateBank(12)  
PokeByte bnkTest,0,Rand(255)  
PokeShort bnkTest,1,Rand(65535)  
PokeInt bnkTest,3,Rand(-2147483648,2147483647)  
PokeFloat bnkTest,7,0.5  
Print PeekByte(bnkTest,0)  
Print PeekShort(bnkTest,1)  
Print PeekInt(bnkTest,3)  
Print PeekFloat(bnkTest,7)  
FreeBank bnkTest  
end function
;; Reads an int from a memory bank and returns the value.
;; An int takes up  four bytes of a memory bank. Values can be in the range -2147483647 to 2147483647.
;; See also: <a class=small href=PeekByte.htm>PeekByte</a>, <a class=small href=PeekShort.htm>PeekShort</a>, <a class=small href=PeekFloat.htm>PeekFloat</a>.
;;param bank - bank handle
;;param offset - offset in bytes, that the peek operation will be started at
function PeekInt(bank,offset)
; Bank Commands Example  
; ---------------------  
bnkTest=CreateBank(12)  
PokeByte bnkTest,0,Rand(255)  
PokeShort bnkTest,1,Rand(65535)  
PokeInt bnkTest,3,Rand(-2147483648,2147483647)  
PokeFloat bnkTest,7,0.5  
Print PeekByte(bnkTest,0)  
Print PeekShort(bnkTest,1)  
Print PeekInt(bnkTest,3)  
Print PeekFloat(bnkTest,7)  
FreeBank bnkTest  
end function
;; Reads a short from a memory bank and returns the value.
;; A short takes  up two bytes of a memory bank. Values can be in the range 0 to 65535.
;; See also: <a class=small href=PeekByte.htm>PeekByte</a>, <a class=small href=PeekInt.htm>PeekInt</a>, <a class=small href=PeekFloat.htm>PeekFloat</a>.
;;param bank - bank handle
;;param offset - offset in bytes, that the peek operation will be started at
function PeekShort(bank,offset)
; Bank Commands Example  
; ---------------------  
bnkTest=CreateBank(12)  
PokeByte bnkTest,0,Rand(255)  
PokeShort bnkTest,1,Rand(65535)  
PokeInt bnkTest,3,Rand(-2147483648,2147483647)  
PokeFloat bnkTest,7,0.5  
Print PeekByte(bnkTest,0)  
Print PeekShort(bnkTest,1)  
Print PeekInt(bnkTest,3)  
Print PeekFloat(bnkTest,7)  
FreeBank bnkTest  
end function
;; Returns the value of Pi to 6 digits (3.141592). Needed for geometric math  routines.
;;param None
function Pi
; Pi example  
Print "The Value of Pi is:" + Pi  
end function
;; Plays a CD track and returns a sound channel.
;; The optional mode parameter allows variations of playback. Remember, the playback happens through the CD cable inside the computer that attaches to the sound card. Many  computers (for some reason), don't have this cable inside properly attached. If this is the case, you will NOT hear CD sound even though you hear other sound  effects and music.
;;param track = track number to play
;;param mode = 1; play track once, 2; loop track, 3; play track to end of CD
function PlayCDTrack( track,[mode] )
; PlayCDTrack example  
; Get a track to play from user  
track=Input$("Enter a CD track number to play:")  
; Play the track, assign a channel - just play once  
chnCD=PlayCDTrack(track,1)  
; Figure out what time it is now  
oldTime=MilliSecs()  
; Play until the channel is over or ESC  
While ChannelPlaying(chnCD) And (Not KeyHit(1))  
; clear and print the time elapsed  
Cls  
Locate 0,0  
Print "Time Elapsed (sec):" + ((MilliSecs()-oldTime)/1000)  
Wend  
; Stop the channel  
StopChannel chnCD  
end function
;; This command will load and play a music file.
;; You MUST use a channel variable  in order to stop or adjust the music playing. You may use StopChannel, PauseChannel, ResumeChannel, etc. with this command.
;; You can't 'preload' the audio like you can a sound sample via the LoadSound command. Every time you call the PlayMusic  command, the file is reloaded and played. This means that if you use the command  while some graphics are moving on-screen, you may get a slight pause when the  hard drive seeks and grabs the music file. To avoid this, you might want to  use the PlaySound/LoopSound  commands instead.
;;param filename$ - name of music file. Formats supported:  raw/mod/s3m/xm/it/mid/rmi/wav/mp2/mp3/ogg/wma/asf
function PlayMusic (filename$)
; Load and play the background music  
chnBackground=PlayMusic("music\background.wav")  
end function
;; This plays a sound previously loaded and assigned to a variable using the LoadSound command. See example.
;; You will need to assign a channel variable handle to the sound when you play  it. All subsequent sound handling commands require you use the CHANNEL variable,  not the sound variable to control the sound - such as StopChannel, PauseChannel, ResumeChannel, ChannelPitch, ChannelVolume, ChannelPan,  and ChannelPlaying.
;;param sound_variable = variable previously assigned with a LoadSound command.
function PlaySound ( sound_variable )
; Assign a global variable for the sound  
Global sndPlayerDie  
; Load the sound file into memory  
sndPlayerDie=LoadSound("sounds/die.wav")  
; Play the sound  
chnDie=PlaySound ( sndPlayerDie )  
end function
;; Used to put a pixel on the screen defined by its x, y location in the current  drawing color defined by the Color command
;;param x= and number from zero to the width of the current graphics mode
;;param y= and number from zero to the height of the current graphics mode
function Plot x,y
;Set the color to green  
Color 0,255,0  
;Draw a dot at location 100,200 with the color green  
plot 100,200  
end function
;; Writes a byte into a memory bank.
;; A byte takes up one byte of a memory  bank. Values can be in the range 0 to 255.
;; See also: <a class=small href=PokeShort.htm>PokeShort</a>, <a class=small href=PokeInt.htm>PokeInt</a>, <a class=small href=PokeFloat.htm>PokeFloat</a>.
;;param bank - bank handle
;;param offset - offset in bytes, that the poke operation will be started at
;;param value - value that will be written to bank
function PokeByte bank,offset,value
; Bank Commands Example  
; ---------------------  
bnkTest=CreateBank(12)  
PokeByte bnkTest,0,Rand(255)  
PokeShort bnkTest,1,Rand(65535)  
PokeInt bnkTest,3,Rand(-2147483648,2147483647)  
PokeFloat bnkTest,7,0.5  
Print PeekByte(bnkTest,0)  
Print PeekShort(bnkTest,1)  
Print PeekInt(bnkTest,3)  
Print PeekFloat(bnkTest,7)  
FreeBank bnkTest  
end function
;; Writes a float into a memory bank.
;; A float takes up four bytes of a memory bank.
;; See also: <a class=small href=PokeByte.htm>PokeByte</a>, <a class=small href=PokeShort.htm>PokeShort</a>, <a class=small href=PokeInt.htm>PokeInt</a>.
;;param bank - bank handle
;;param offset - offset in bytes, that the poke operation will be started at
;;param value - value that will be written to bank
function PokeFloat bank,offset,value
; Bank Commands Example  
; ---------------------  
bnkTest=CreateBank(12)  
PokeByte bnkTest,0,Rand(255)  
PokeShort bnkTest,1,Rand(65535)  
PokeInt bnkTest,3,Rand(-2147483648,2147483647)  
PokeFloat bnkTest,7,0.5  
Print PeekByte(bnkTest,0)  
Print PeekShort(bnkTest,1)  
Print PeekInt(bnkTest,3)  
Print PeekFloat(bnkTest,7)  
FreeBank bnkTest  
end function
;; Writes an int into a memory bank.
;; An int takes up four bytes of a memory  bank. Values can be in the range -2147483647 to 2147483647.
;; See also: <a class=small href=PokeByte.htm>PokeByte</a>, <a class=small href=PokeShort.htm>PokeShort</a>, <a class=small href=PokeFloat.htm>PokeFloat</a>.
;;param bank - bank handle
;;param offset - offset in bytes, that the poke operation will be started at
;;param value - value that will be written to bank
function PokeInt bank,offset,value
; Bank Commands Example  
; ---------------------  
bnkTest=CreateBank(12)  
PokeByte bnkTest,0,Rand(255)  
PokeShort bnkTest,1,Rand(65535)  
PokeInt bnkTest,3,Rand(-2147483648,2147483647)  
PokeFloat bnkTest,7,0.5  
Print PeekByte(bnkTest,0)  
Print PeekShort(bnkTest,1)  
Print PeekInt(bnkTest,3)  
Pint PeekFloat(bnkTest,7)  
FreeBank bnkTest  
end function
;; Writes a short into a memory bank.
;; A short takes up two bytes of a memory  bank. Values can be in the range 0 to 65535.
;; See also: <a class=small href=PokeByte.htm>PokeByte</a>, <a class=small href=PokeInt.htm>PokeInt</a>, <a class=small href=PokeFloat.htm>PokeFloat</a>.
;;param bank - bank handle
;;param offset - offset in bytes, that the poke operation will be started at
;;param value - value that will be written to bank
function PokeShort bank,offset,value
; Bank Commands Example  
; ---------------------  
bnkTest=CreateBank(12)  
PokeByte bnkTest,0,Rand(255)  
PokeShort bnkTest,1,Rand(65535)  
PokeInt bnkTest,3,Rand(-2147483648,2147483647)  
PokeFloat bnkTest,7,0.5  
Print PeekByte(bnkTest,0)  
Print PeekShort(bnkTest,1)  
Print PeekInt(bnkTest,3)  
Print PeekFloat(bnkTest,7)  
FreeBank bnkTest  
end function
;; Writes a string to the front buffer (i.e. the screen), and starts a new  line.
;; If the optional string parameter is omitted, then the command will just  start a new line. See also: Write.
;;param string$ (optional) - string variable or value
function Print [string$]
; Print Example  
; -------------  
Print "Blitz "  
Print "Basic"  
end function
;; Unlike the RND command, this command actually returns  only integer values. The low value defaults to 1 if no value is specified. The  high value is the highest number that can be randomly generated.
;; If you need to generate floating point random numbers, use  Rnd.
;;param low value = optional - defaults to 1; lowest number to generate
;;param high value = highest number to generate
function Rand ([low value],high value)
; Rand example  
; Set the randomizer seed for more true random numbers  
SeedRnd (MilliSecs())  
; Generate random numbers between 1 and 100  
For t = 1 To 20  
Print Rand(1,100)  
Next  
end function
;; This reads the next value in a set of Data statements. This allows you to  store large blocks of constant information (the structure of tile blocks for  a game level for example) into easy to maintain Data statements, then retrieve  them for redrawing, etc.
;; Unlike most BASIC languages, Data statments do not have to be linear and sequential.  Through the use of Labels (aka 'dot variable') you can create 'banks' of Data  statments with the unique ability to 'Restore the Data pointer' to any one of  these labels. Each level could have its own label (.level1, .level2, etc). See  the Data statement, Restore  command, or .Label command for more information.
;; Note: You can read multiple values at one time; Read X,Y,Z for example.
;; See also: <a class=small href=Data.htm>Data</a>, <a class=small href=Restore.htm>Restore</a>.
;;param variable = valid variable to match the data type you are reading (integer,  string, etc)
function Read variable
; Sample of read/restore/data/label commands  
; Let's put the data pointer to the second data set  
Restore seconddata  
; Let's print them all to the screen  
For t = 1 To 10  
Read num ; Get the next data value in the data stack  
Print num  
Next  
; Now for the first set of data  
Restore firstdata  
; Let's print them all to the screen  
For t = 1 To 10  
Read num ; Get the next data value in the data stack  
Print num  
Next  
; this is the first set of data  
.firstdata  
Data 1,2,3,4,5,6,7,8,9,10  
; this is the second set of data  
.seconddata  
Data 11,12,13,14,15,16,17,18,19,20  
end function
;; In the case of file streams, this reflects how much data is internally buffered.  In the case of TCP streams, this reflects how much data has 'arrived'.
;;param filehandle/streamhandle = handle assigned to the file or stream when originally  opened.
function ReadAvail (filehandle/streamhandle)
; OpenTCPStream/CloseTCPStream/ReadAvail Example  
Print "Connecting..."  
tcp=OpenTCPStream( "www.blitzbasement.com",80 )  
If Not tcp Print "Failed.":WaitKey:End  
Print "Connected! Sending request..."  
WriteLine tcp,"GET http://www.blitzbasement.com HTTP/1.0"  
WriteLine tcp,Chr$(10)  
If Eof(tcp) Print "Failed.":WaitKey:End  
Print "Request sent! Waiting for reply..."  
While Not Eof(tcp)  
Print ReadLine$( tcp )  
Print "Bytes available:" + ReadAvail(tcp)  
Wend  
If Eof(tcp)=1 Then Print "Success!" Else Print "Error!"  
CloseTCPStream tcp  
WaitKey  
end function
;; Once you've opened a disk file (or stream) for reading, use this command  to read a single byte at a time from the file/stream. Note, a byte is an integer  that can take the values 0..255 and occupies 8 bits of storage. Since characters  are stored as byte values this function can be used to read a file one character  at a time. Reading beyond the end of file does not result in an error, but each  value read will be zero.
;; Advanced notes
;; The number that is stored by WriteByte is actually the least significant byte  of an integer so negative numbers and numbers above 255 will still have a value  between 0..255. Unless you understand how 32 bit integers are stored in 2's  compliment notation this will seem strange but it is NOT a bug.
;; Streams can only be used in Blitz Basic v1.52 or greater.
;;param filehandle/stream = a valid variable set with the OpenFile, ReadFile command,  or OpenTCPStream (v1.52+)
function ReadByte ( filehandle/stream )
; Reading and writing a file using ReadByte and WriteByte functions  
; Initialise some variables for the example  
Byte1% = 10 ; store 10  
Byte2% = 100 ; store 100  
Byte3% = 255 ; store 255 ( the maximum value that can be stored in a Byte)  
Byte4% = 256 ; try to store 256 this will end up as 0 ( i.e. 256 - 256 = 0 )  
Byte5% = 257 ; try to store 257 this will end up as 1 ( i.e. 257 - 256 = 1 )  
Byte6% = -1 ; try to store -1 this will end up as 255 ( i.e. 256 -1 = 255 )  
Byte7% = -2 ; try to store 256 this will end up as 254 ( i.e. 256 - 2 = 254  )  
Byte8% = Asc("A") ; Store the ASCII value for the Character "A" ( i.e. 65 )  
; Open a file to write to  
fileout = WriteFile("mydata.dat ")  
; Write the information to the file  
WriteByte( fileout, Byte1 )  
WriteByte( fileout, Byte2 )  
WriteByte( fileout, Byte3 )  
WriteByte( fileout, Byte4 )  
WriteByte( fileout, Byte5 )  
WriteByte( fileout, Byte6 )  
WriteByte( fileout, Byte7 )  
WriteByte( fileout, Byte8 )  
; Close the file  
CloseFile( fileout )  
; Open the file to Read  
filein = ReadFile("mydata.dat")  
Read1 = ReadByte( filein )  
Read2 = ReadByte( filein )  
Read3 = ReadByte( filein )  
Read4 = ReadByte( filein )  
Read5 = ReadByte( filein )  
Read6 = ReadByte( filein )  
Read7 = ReadByte( filein )  
Read8 = ReadByte( filein )  
; Close the file once reading is finished  
CloseFile( filein )  
Print "Written - Read"  
Write Byte1 + " - " : Print Read1  
Write Byte2 + " - " : Print Read2  
Write Byte3 + " - " : Print Read3  
Write Byte4 + " - " : Print Read4  
Write Byte5 + " - " : Print Read5  
Write Byte6 + " - " : Print Read6  
Write Byte7 + " - " : Print Read7  
Write Byte8 + " - " : Print Chr$( Read8 )  
WaitKey()  
end function
;; You can read the contents of a disk file (or stream) to a memory bank using  this command.
;; Note: The file handle must be opened with OpenFile  or OpenTCPStream and subsequently closed with CloseFile or CloseTCPStream  after the reading operations are complete.
;; Return how many bytes successfully read from a stream.
;; Streams can only be used in Blitz Basic v1.52 or greater.
;; See also: <a class=small href=WriteBytes.htm>WriteBytes</a>.
;;param bank = variable containing handle to valid bank
;;param file/stream = file handle of previously opened file or stream
;;param offset = offset in bytes to write the value
;;param count = how many bytes to write from the offset
function ReadBytes bank,file/stream,offset,count
; Read/WriteBytes Commands Example  
; Create a 50 byte memory bank  
bnkTest=CreateBank(500)  
; Let's fill the bank with random data  
For t = 1 To 50  
PokeByte bnkTest,t,Rnd(255)  
Next  
; Open a file to write to  
fileBank=WriteFile("test.bnk")  
; Write the bank to the file  
WriteBytes bnkTest,fileBank,0,50  
; Close it  
CloseFile fileBank  
; Free the bank  
FreeBank bnkTest  
; Make a new one  
bnkTest=CreateBank(500)  
; Open the file to read from  
fileBank=OpenFile("test.bnk")  
; Write the bank to the file  
ReadBytes bnkTest,fileBank,0,50  
; Close it  
CloseFile fileBank  
; Write back the results!  
For t = 1 To 50  
Print PeekByte (bnkTest,t)  
Next  
end function
;; In file operations, you will often need to parse through a directory/folder  and retrieve unknown filenames and other folders from it. This command opens  a specified folder to begin these operations. The command returns a file handle  which is used by the other commands to perform other services (like most file  operators). You will use the NextFile$ to iterate  through each entry (use FILETYPE to see if it is  a file or folder). Remember, once completed, good programming practice dictates  that you CloseDir the open folder. The example should  help out alot.
;;param directory = full path and name of folder/directory to open
function ReadDir (directory)
; ReadDir/NextFile$/CloseDir example  
; Define what folder to start with ...  
folder$="C:"  
; Open up the directory, and assign the handle to myDir  
myDir=ReadDir(folder$)  
; Let's loop forever until we run out of files/folders to list!  
Repeat  
; Assign the next entry in the folder to file$  
file$=NextFile$(myDir)  
; If there isn't another one, let's exit this loop  
If file$="" Then Exit  
; Use FileType to determine if it is a folder (value 2) or a file and print  results  
If FileType(folder$+"\"+file$) = 2 Then  
Print "Folder:" + file$  
Else  
Print "File:" + file$  
End If  
Forever  
; Properly close the open folder  
CloseDir myDir  
; We're done!  
Print "Done listing files!"  
end function
;; This command opens the designated filename and prepares it to be read from.  Use this to read back your own configuration file, save game data, etc. also  useful for reading custom types from a files. The filehandle that is returned  is an integer value that the operating system uses to identify which file is  to be read from and must be passed to the functions such as ReadInt(). If the  file could not be opened, for instance, if it does not exists, then the filehandle  is Zero.
;;param filename$ = any valid path and filename. The returned value is the filehandle  which is an integer value.
function ReadFile (filename$)
; Reading and writing custom types to files using ReadFile, WriteFile and  CloseFile  
; Initialise some variables for the example  
Type HighScore  
Field Name$  
Field Score%  
Field Level%  
End Type  
Best.HighScore = New HighScore  
BestName = "Mark"  
BestScore = 11657  
BestLevel = 34  
; Open a file to write to  
fileout = WriteFile("mydata.dat")  
; Write the information to the file  
WriteString( fileout, BestName )  
WriteInt( fileout, BestScore )  
WriteByte( fileout, BestLevel )  
; Close the file  
CloseFile( fileout )  
; Open the file to Read  
filein = ReadFile("mydata.dat")  
; Lets read the Greatest score from the file  
Greatest.HighScore = New HighScore  
GreatestName$ = ReadString$( filein )  
GreatestScore = ReadInt( filein )  
GreatestLevel = ReadByte( filein )  
; Close the file once reading is finished  
CloseFile( filein )  
Print "High score record read from - mydata.dat "  
Print  
Write "Name = "  
Print GreatestName  
Write "Score = "  
Print GreatestScore  
Write "Level = "  
Print GreatestLevel  
WaitKey()  
end function
;; Once you've opened a disk file (or stream) for reading, use this command  to read a single floating point number from the file. Note, each value written  uses 4 bytes of space. Reading beyond the end of file does not result in an  error, but each value read will be zero.
;; Streams can only be used in Blitz Basic v1.52 or greater.
;;param filehandle/stream = a valid variable set with the OpenFile, ReadFile command,  or OpenTCPStream (v1.52+) The value returned is a floating point number.
function ReadFloat (filehandle/stream)
; Reading and writing a file using ReadFloat and WriteFloat functions  
; Initialise some variables for the example  
Num1# = 10.5 ; store 10.5  
Num2# = 365.25 ; store 365.25  
Num3# = 32767.123 ; 32767.123 is the largest positive Short Integer Value in  BlitzBasic )  
Num4# = -32768.123 ; -32768.123 the largest negative Short Integer Value in  BlitzBasic )  
; Open a file to write to  
fileout = WriteFile("mydata.dat")  
; Write the information to the file  
WriteFloat( fileout, Num1 )  
WriteFloat( fileout, Num2 )  
WriteFloat( fileout, Num3 )  
WriteFloat( fileout, Num4 )  
; Close the file  
CloseFile( fileout )  
; Open the file to Read  
filein = ReadFile("mydata.dat")  
Read1# = ReadFloat( filein )  
Read2# = ReadFloat( filein )  
Read3# = ReadFloat( filein )  
Read4# = ReadFloat( filein )  
; Close the file once reading is finished  
CloseFile( filein )  
Print "Floating Point Data Read From File - mydata.dat "  
Print Read1  
Print Read2  
Print Read3  
Print Read4  
WaitKey()  
end function
;; Once you've opened a disk file (or stream) for reading, use this command  to read a single integer value from the file. Note, each value written uses  4 bytes of space and is written least significant byte first. Reading beyond  the end of file does not result in an error, but each value read will be zero.
;; Streams can only be used in Blitz Basic v1.52 or greater.
;;param filehandle/stream = a valid variable set with the OpenFile, ReadFile command,  or OpenTCPStream (v1.52+)
;;param The value returned is an integer in the range -2147483648 to 2147483647
function ReadInt (filehandle/stream)
; Reading and writing a file using ReadInt and WriteInt functions  
; Initialise some variables for the example  
Int1% = 10 ; store 10  
Int2% = 365 ; store 365  
Int3% = 2147483647 ; store 2147483647 the largest positive Integer Value in  BlitzBasic )  
Int4% = - 2147483648 ; store -2147483648 the largest negative Integer Value  in BlitzBasic )  
; Open a file to write to  
fileout = WriteFile("mydata.dat")  
; Write the information to the file  
WriteInt( fileout, Int1 )  
WriteInt( fileout, Int2 )  
WriteInt( fileout, Int3 )  
WriteInt( fileout, Int4 )  
; Close the file  
CloseFile( fileout )  
; Open the file to Read  
filein = ReadFile("mydata.dat")  
Read1 = ReadInt( filein )  
Read2 = ReadInt( filein )  
Read3 = ReadInt( filein )  
Read4 = ReadInt( filein )  
; Close the file once reading is finished  
CloseFile( filein )  
Print "Integer Data Read From File - mydata.dat "  
Print Read1  
Print Read2  
Print Read3  
Print Read4  
WaitKey()  
end function
;; Once you've opened a disk file (or stream) for reading, use this command  to read a whole line of text from a text file or stream. Each line of text is  returned as a string variable. This function can be used to read plain text  files.
;; Characters are read from the input file until an "end-of-line" mark is found.  An "end-of-line" can be a single carriage return (0Dh) or a single linefeed  (0Ah) or carriage return followed by a linefeed (0Dh, 0Ah). Reading beyond the  end of file does not result in an error, but each value read will be a zero  length string.
;; ReadLine$ returns all chars except chr$(13)/chr$(10).
;; Streams can only be used in Blitz Basic v1.52 or greater.
;;param filehandle/stream = a valid variable set with the OpenFile, ReadFile command,  or OpenTCPStream (v1.52+). The value returned is a text string.
function ReadLine$ (filehandle/stream)
; Reading and writing a file using ReadLine$ and WriteLine functions  
; Initialise some variables for the example  
String1$ = "Line 1 is short"  
String2$ = "Line 2 is a longer line but they can be much longer"  
String3$ = "Line 3 is made up "  
String4$ = "of two parts joined together."  
; Open a file to write to  
fileout = WriteFile("mydata.txt")  
; Write the information to the file  
WriteLine( fileout, String1 )  
WriteLine( fileout, String2 )  
WriteLine( fileout, String3 + String4)  
WriteLine( fileout, "Just to show you don't have to use variables" )  
; Close the file  
CloseFile( fileout )  
; Open the file to Read  
filein = ReadFile("mydata.txt")  
Read1$ = ReadLine$( filein )  
Read2$ = ReadLine$( filein )  
Read3$ = ReadLine$( filein )  
Read4$ = ReadLine$( filein )  
; Close the file once reading is finished  
CloseFile( filein )  
Print "Lines of text read from file - mydata.txt "  
Print  
Print Read1  
Print Read2  
Print Read3  
Print Read4  
WaitKey()  
end function
;; Reads a color value from either the current buffer or the specified buffer.
;; The returned colour value is in the form of an integer that contains the alpha, red, green and blue values of the pixel.
;; You can use this command on a locked buffer for a slight speed-up. See LockBuffer.
;; Warning: this is a low level command with no error checking for out of range parameters, use with care.
;; See also: <a class=small href=GetColor.htm>GetColor</a>, <a class=small href=ReadPixelFast.htm>ReadPixelFast</a>.
;;param x - x coordinate of pixel
;;param y - y coordinate of pixel
;;param buffer (optional) - name of buffer to read colour value from, e.g. BackBuffer() (defaults to current graphics buffer)
function ReadPixel (x,y,[buffer])
; ReadPixel/WritePixel Example  
; ----------------------------  
Graphics 640,480,16  
Print "Press a key to read color values (this may take a few seconds)"  
WaitKey()  
; Load and draw an image on to the screen - can be anything  
pic=LoadImage("media/blitz_pic.bmp")  
DrawImage pic,0,0  
; Initialise an array big enough to fit all the color information of the screen  
Dim pix(GraphicsWidth(),GraphicsHeight())  
; Use ReadPixel to get all the color information of the screen  
For y=0 To GraphicsHeight()  
For x=0 To GraphicsWidth()  
pix(x,y)=ReadPixel(x,y)  
Next  
Next  
Cls  
Locate 0,0  
Print "Press a key to write pixels (this may takes a few seconds)"  
Print "Once this has finished, you can then press a key to end the program"  
WaitKey()  
; Use WritePixel to redraw the screen using the color information we got earlier  
For y=0 To GraphicsHeight()  
For x=0 To GraphicsWidth()  
WritePixel x,y,pix(x,GraphicsHeight()-y) ; get y array value in backwards order,  to flip screen  
Next  
Next  
WaitKey()  
end function
;; Reads a color value from either the current buffer or the specified buffer,  and returns it.
;; The returned colour value is in the form of an integer than  contains the alpha, red, green and blue values of the pixel.
;; IMPORTANT:
;; You *must* use this command on a locked buffer, otherwise the command will  fail. See LockBuffer.
;; Also, you must make sure that the coordinates that you are reading from are  valid, otherwise you will end up reading garbage values.
;; WARNING:
;; By not following the above advice, you may cause your computer to crash.
;; See also: GetColor, ReadPixel.
;;param x - y-coordinate of pixel
;;param y - y-coordinate of pixel
;;param buffer (optional) - name of buffer to read colour value from, e.g. BackBuffer()
function ReadPixelFast (x,y,[buffer])
; ReadPixelFast/WritePixeFast Example  
; -----------------------------------  
Graphics 640,480,16  
Print "Press a key to read color values"  
WaitKey()  
; Load and draw an image on to the screen - can be anything  
pic=LoadImage("media/blitz_pic.bmp")  
DrawImage pic,0,0  
; Initialise an array big enough to fit all the color information of the screen  
Dim pix(GraphicsWidth(),GraphicsHeight())  
; Lock buffer before using ReadPixelFast  
LockBuffer  
; Use ReadPixel to get all the color information of the screen  
For y=0 To GraphicsHeight()  
For x=0 To GraphicsWidth()  
pix(x,y)=ReadPixelFast(x,y)  
Next  
Next  
; Lock buffer after using ReadPixelFast  
UnlockBuffer  
Cls  
Locate 0,0  
Print "Press a key to write pixels"  
Print "Once this has finished, you can then press a key to end the program"  
WaitKey()  
; Lock buffer before using WritePixelFast  
LockBuffer  
; Use WritePixel to redraw the screen using the color information we got earlier  
For y=0 To GraphicsHeight()  
For x=0 To GraphicsWidth()  
WritePixelFast x,y,pix(x,GraphicsHeight()-y) ; get y array value in backwards  order, to flip screen  
Next  
Next  
; Unlock buffer after using WritePixelFast  
UnlockBuffer  
WaitKey()  
end function
;; Once you've opened a disk file (or stream) for reading, use this command  to read a single short integer (16bit) value from the file. Note, each value  written uses 2 bytes of disk space and is written least significant byte first.  Reading beyond the end of file does not result in an error, but each value read  will be zero.
;; Streams can only be used in Blitz Basic v1.52 or greater.
;;param filehandle/stream = a valid variable set with the OpenFile, ReadFile command,  or OpenTCPStream (v1.52+)
;;param The value returned is an integer in the range 0-65535.
function ReadShort (filehandle/stream)
; Reading and writing a file using ReadShort and WriteShort functions  
; Initialise some variables for the example  
Int1% = 10 ; store 10  
Int2% = 365 ; store 365  
Int3% = 32767 ; 32767 is the largest positive Short Integer Value in BlitzBasic  )  
Int4% = -32768 ; -32768 the largest negative Short Integer Value in BlitzBasic  )  
; Open a file to write to  
fileout = WriteFile("mydata.dat")  
; Write the information to the file  
WriteShort( fileout, Int1 )  
WriteShort( fileout, Int2 )  
WriteShort( fileout, Int3 )  
WriteShort( fileout, Int4 )  
; Close the file  
CloseFile( fileout )  
; Open the file to Read  
filein = ReadFile("mydata.dat")  
Read1 = ReadShort( filein )  
Read2 = ReadShort( filein )  
Read3 = ReadShort( filein )  
Read4 = ReadShort( filein )  
; Close the file once reading is finished  
CloseFile( filein )  
Print "Short Integer Data Read From File - mydata.dat "  
Print Read1  
Print Read2  
Print Read3  
Print Read4  
WaitKey()  
end function
;; Once you've opened a disk file (or stream) for reading, use this command to read a string variable from the file.
;; Each string is stored in the file as a 4 byte (32bit) integer followed by the characters that form the string. The integer contains the number of characters  in the string, i.e. its length. Note, that Carriage Return, Line Feed and Null  characters are NOT use to indicate the end of the string. A file of strings  cannot be read like a text file, since it contains string variables and not  text. A null string, i.e. a string of zero length ("") is stored as 4 bytes,  an integer count with a value = zero, followed by no Characters. Note strings are not limited to 255 characters as in some languages. Reading beyond the end  of file does not result in an error, but each value read will be a zero length string.
;; This command should only be used when reading from a binary file or stream.  Text files should be interrogated using ReadLine.
;; Streams can only be used in Blitz Basic v1.52 or greater.
;; See also: <a class=small href=WriteString.htm>WriteString</a>, <a class=small href=OpenTCPStream.htm>OpenTCPStream</a>, <a class=small href=ReadFile.htm>ReadFile</a>, <a class=small href=ReadLine.htm>ReadLine</a>, <a class=small href=ReadByte.htm>ReadByte</a>, <a class=small href=ReadShort.htm>ReadShort</a>, <a class=small href=ReadInt.htm>ReadInt</a>, <a class=small href=ReadFloat.htm>ReadFloat</a>, <a class=small href=ReadBytes.htm>ReadBytes</a>, <a class=small href=ReadAvail.htm>ReadAvail</a>.
;;param filehandle/stream = a valid variable set with the OpenFile, ReadFile command,  or OpenTCPStream (v1.52+) The value returned is a text string.
function ReadString$ (filehandle/stream)
; Reading and writing a file using ReadString$ and WriteString functions  
; Initialise some variables for the example  
String1$ = "A short string"  
String2$ = "A longer string since these are variables lengths"  
String3$ = "This is string3 "  
String4$ = "joined to string4"  
; Open a file to write to  
fileout = WriteFile("mydata.dat")  
; Write the information to the file  
WriteString( fileout, String1 )  
WriteString( fileout, String2 )  
WriteString( fileout, String3 + String4)  
WriteString( fileout, "Just to show you don't have to use variables" )  
; Close the file  
CloseFile( fileout )  
; Open the file to Read  
filein = ReadFile("mydata.dat")  
Read1$ = ReadString$( filein )  
Read2$ = ReadString$( filein )  
Read3$ = ReadString$( filein )  
Read4$ = ReadString$( filein )  
; Close the file once reading is finished  
CloseFile( filein )  
Print "String Variables Read From File - mydata.dat "  
Print  
Print Read1  
Print Read2  
Print Read3  
Print Read4  
WaitKey()  
end function
;; This command will draw a rectangle in the current drawing Color starting at the location specified. The last parameter  determines if the rectangle is filled or just a 'box'.
;;param x = x coordinate to begin drawing the rectangle
;;param y = y coordinate to begin drawing the rectangle
;;param width = how wide to make the rectangle in pixels
;;param height = how tall to make the rectangle in pixels
;;param solid = 0 or False for unfilled and 1 or True for filled
function Rect x, y, width, height, solid
; Flip/Backbuffer()/Rect Example  
; Set Graphics Mode  
Graphics 640,480  
; Go double buffering  
SetBuffer BackBuffer()  
; Setup initial locations for the box  
box_x = -20 ; negative so it will start OFF screen  
box_y = 100  
While Not KeyHit(1)  
Cls ; Always clear screen first  
Rect box_x,box_y,20,20,1 ; Draw the box in the current x,y location  
Flip ; Flip it into view  
box_x = box_x + 1 ; Move the box over one pixel  
If box_x = 640 Then box_x=-20 ; If it leaves the Right edge, reset its x location  
Wend  
end function
;; This command will take two rectangular locations on the screen and see if  they overlap. You will need to know the x, y, width, and height of both regions  to test.
;; I'm still trying to find a real good logical use for this command with all the  other collision commands available to you like ImagesOverlap, ImagesCollide, ImageRectOverlap, and ImageRectCollide. My guess is that this is  the absolute fastest possible collision method available and useful to those  wishing to write their own collision routines.
;; Unlike the other collision commands, there is no image to detect a collision  with - simply one rectangular location overlapping another. You could probably  use this command instead of the ImageRectOverlap  command, as they are really basically doing the same thing (and I betcha this  is faster).
;; This would be useful for very easy-going 'Monkey Island' games to check the  position of your pointer against a screen location (or 'hot spot') when pixel  perfect accuracy (heck, image graphics in general) are not really needed.
;;param rect1 X = rectangle 1 x location
;;param rect1 Y = rectangle 1 y location
;;param rect1 Width = rectangle 1 width
;;param rect1 Height = rectangle 1 height
;;param rect2 X = rectangle 2 x location
;;param rect2 Y = rectangle 2 y location
;;param rect2 Width = rectangle 2 width
;;param rect2 Height = rectangle 2 height
function RectsOverlap (rect1 X,rect1 Y,rect1 Width,rect1 Height,rect2 X,rect2 Y,rect2 Width,rect2 Height)
; RectsOverlap Example  
; Flashing graphics warning! Gets hypnotic ...  
; Turn on graphics mode  
Graphics 640,480,16  
; Double buffering, and randomize the randomizer  
SetBuffer BackBuffer()  
SeedRnd MilliSecs()  
; Repeat the loop until ESC pressed  
While Not KeyHit(1)  
; Generate a random rectangle  
rect1X=Rnd(50,610)  
rect1Y=Rnd(50,430)  
rect1W=20  
rect1H=20  
; And another  
rect2X=Rnd(50,610)  
rect2Y=Rnd(50,430)  
rect2W=20  
rect2H=20  
; Clear the screen standard double buffering  
Cls  
; Draw our rectangle2 in random colors  
Color Rnd(255),Rnd(255),Rnd(255)  
Rect rect1X,rect1Y,rect1W,rect1H,0  
Color Rnd(255),Rnd(255),Rnd(255)  
Rect rect2X,rect2Y,rect2W,rect2H,0  
; Did they collide? If so, print a message and exit the loop!  
If RectsOverlap (rect1X,rect1Y,rect1W,rect1H,rect2X,rect2Y,rect2W,rect2H) Then  
Text 0,0, "Our boxes finally collided! Press a mouse button..."  
; We do a flip here to ensure the text message gets seen too!  
Flip  
Exit ; exit the While/Wend loop  
End If  
; Flip the rects into view, wait 1/10th of a sec, repeat  
Flip  
Delay 100  
Wend  
; Wait for a mouse click  
WaitMouse()  
; End our graphics mode  
EndGraphics  
end function
;; First off, this ONLY works when you have joined a network game via StartNetGame or JoinNetGame  and you have created a player via CreateNetPlayer  (you must create a player, even if it is just to lurk).
;; This returns a TRUE value if a message was received, FALSE if none has been  received. This will typically go inside a function that is constantly being  checked for message and decode and handle them. You will use NetMsgType, NetMsgFrom, NetMsgTo, and NetMsgData$  to get the important information from the message and act on it.
;; The example requires that you run it on a remote machine while the local computer  runs the example in the SendNetMsg command.
;;param None.
function RecvNetMsg()
; RecvNetMsg() example  
; --------------------  
; Run this program on the REMOTE computer to 'watch'  
; the activity of the SendNetMsg example. Run that  
; example on local machine.  
;  
; This program will tell you when a player involved in  
; the game hits a wall ...  
; We'll use this instead of JoinHostGame - make it easier  
StartNetGame()  
; Create a player - a player must be created to  
; receive mesages!  
playerID=CreateNetPlayer("Shane")  
; Loop and get status  
While Not KeyHit(1)  
; Check to see if we've received a message  
If RecvNetMsg() Then  
; if we did, let's figure out what type it is  
; we know it will be a user message, though  
msgType=NetMsgType()  
; 1-99 means a user message  
If msgType>0 And msgType<100 Then  
; Let's see who the message was from  
msgFrom=NetMsgFrom()  
; Let's get the message!  
msgData$=NetMsgData$()  
; Print the message  
Print msgData$  
End If  
End If  
Wend  
end function
;; Receives a UDP message into the specified UDP stream. Standard stream read  commands can then be used to examine the message.
;; The return value is the integer IP address of the message source, or 0 if no  message was available. You can use UDPMsgPort()  to find out the port number of the message source.
;; Click <a href=http://www.blitzbasic.co.nz/b3ddocs/command.php?name=RecvUDPMsg&ref=comments target=_blank>here</a> to view the latest version of this page online</body>
;; </html>
;;param udp_stream - UDP stream handle
function RecvUDPMsg( udp_stream )
end function
;; The REPEAT ... UNTIL loop allows you to perform  a series of commands until a specific condition has been met. This lets the  conditional appear after the commands have been executed before checking,  not before like the WHILE ... WEND  loop does. In general, use REPEAT ... UNTIL if you know you will have the commands  enclosed between them execute at least once.
;; See also: <a class=small href=Until.htm>Until</a>, <a class=small href=Forever.htm>Forever</a>, <a class=small href=Exit.htm>Exit</a>, <a class=small href=While.htm>While</a>, <a class=small href=For.htm>For</a>.
;;param None
function Repeat
; Repeat until user hits ESC key  
Repeat  
print "Press ESC to quit this!"  
Until KeyHit(1)  
end function
;; This command will allow you to replace characters within a string with another.  Use this to strip or convert letters out of your strings (like removing spaces  or turning them into underscores). Pretty straight forward.
;;param string$ = any valid string variable
;;param find$ = any valid string
;;param replace$ = any valid string
function Replace$ (string$, find$, replace$)
name$="Bill Wallace"  
Print "Your name before replacing: " + name$  
Print "Your name with L changed to B: " + Replace$(name$,"l","b")  
end function
;; Resizes a previously created memory bank. Existing bank data is unmodified,  but may be moved in memory. Also see CreateBank, CopyBank, and BankSize.
;;param bankhandle = handle assigned to bank when created
;;param new_size = new size of bank in bytes
function ResizeBank bankhandle,new_size
; BankSize, ResizeBank, CopyBank Example  
; create a bank  
bnkTest=CreateBank(5000)  
; Fill it with rand Integers  
For t = 0 To 4999  
PokeByte bnkTest,t,Rand(9)  
Next  
; Resize the bank  
ResizeBank bnkTest,10000  
; Copy the first half of the bank to the second half  
CopyBank bnkTest,0,bnkTest,5000,5000  
; Print final banksize  
Print BankSize(bnkTest)  
end function
;; Similar to ScaleImage, but uses pixel values  instead of percentages. Use this command to resize an image previously loaded  with LoadImage or LoadAnimImage.
;; This is NOT intended for REAL TIME scaling of images! Precalculate your images  before running your program, or you will likely see massively slow renderings  of graphics.
;;param image = file handle for previously loaded image
;;param width# = new width in pixels
;;param height# = new height in pixels
function ResizeImage image,width#,height#
; ResizeImage example  
; Set Graphics Mode  
Graphics 800,600,16  
; Randomize the random seed  
SeedRnd MilliSecs()  
; Load an image to tile (your location might vary)  
gfxBall=LoadImage("C:Program FilesBlitz Basicsamplesall.bmp")  
; Size it randomly from 300 to -300 both x and y  
ResizeImage gfxBall,Rnd(-300,300),Rnd(-300,300)  
; Wait for ESC to hit  
While Not KeyHit(1)  
DrawImage gfxball,Rnd(800),Rnd(600)  
VWait  
Wend  
end function
;; When using Data statements to store large blocks  of constants for use with the Read command, it is necessary  to denote the start of the Data with a .Label. The Restore  command moves the 'pointer' to the first Data statement's value following the  designated label. You MUST use the Restore label command prior to using  the Read command. This method allows you to store groups of Data statements  non-sequentially. Its different (if you are used to other BASIC languages) but  you will find it most flexible. See the example and other commands related to  Data command.
;; See also: <a class=small href=Read.htm>Read</a>, <a class=small href=Data.htm>Data</a>.
;;param label = any valid and exisiting label
function Restore label
; Sample of read/restore/data/label commands  
; Let's put the data pointer to the second data set  
Restore seconddata  
; Let's print them all to the screen  
For t = 1 To 10  
Read num ; Get the next data value in the data stack  
Print num  
Next  
; Now for the first set of data  
Restore firstdata  
; Let's print them all to the screen  
For t = 1 To 10  
Read num ; Get the next data value in the data stack  
Print num  
Next  
; this is the first set of data  
.firstdata  
Data 1,2,3,4,5,6,7,8,9,10  
; this is the second set of data  
.seconddata  
Data 11,12,13,14,15,16,17,18,19,20  
end function
;; ResumeChannel is used to continue the playing of a sound sample or music  track on the given channel after you have temporarily halted playback on that  channel (via PauseChannel).
;;param channel - a music or sound channel previously allocated via LoadSound, PlayMusic,  etc.
function ResumeChannel channel
Graphics 640, 480, 0, 2  
musicchannel = PlayMusic ("oohyeahbaby.mp3") ; Replace with a music file on  your hard drive!  
Repeat  
Print "Press a key to pause the music..."  
WaitKey  
PauseChannel musicchannel  
Print "Press a key to continue the music..."  
WaitKey  
ResumeChannel musicchannel  
Until KeyHit (1)  
End  
end function
;; When called inside a FUNCTION structure, the  RETURN command immediately returns from the function back to the calling code.  An optional value may be returned. See FUNCTION for  more information. Remember, after a Return, the remaining code of the Function  is not executed. See example. It also returns execution from a subroutine called  with the Gosub command.
;; See also: <a class=small href=Function.htm>Function</a>, <a class=small href=Gosub.htm>Gosub</a>, <a class=small href=Goto.htm>Goto</a>.
;;param Return can pass a value back to the calling function of the type declared in the function name.
;;param No value can be returned when used with Gosub.
function Return value
; RETURN example  
; Set result to the return value of the function 'testme'  
result=testme(Rnd(0,10));  
; The program effectively ends here.  
; The actual function  
Function testme(test);  
; If the random number passed = 0  
If test=0 Then  
Print "Value was 0"  
Return False ; The Function ends immediately  
Else  
Print "The value was greater than 0"  
Return True ; The Function ends immediately  
End If  
Print "This line never gets printed!"  
End Function  
end function
;; You can retrieve a certain number of characters from the rightmost side  of a string. See the example.
;;param string$ = any valid string variable
;;param length = the number of characters on the right to return
function Right$ (string$, length)
name$="Bill Wallace"  
Print "The last 4 letters of your name are: " + Right$(name$,4)  
end function
;; This returns either a floating point or integer number of a random value  falling between the start and end number. It returns an integer if assiged to  an integer variable, and it returns a floating point value if assiged to a floating  number variable. The start and end values are inclusive. Be sure to use the SeedRnd command to avoid generating the same random  numbers every time the program is run.
;;param start# = Lowest value to generate
;;param end# = Highest value to generate
function Rnd (start#,end#)
y=Rnd(0,10) ; Set y to a random integer between 0 and 10  
y#=Rnd(0,5) ; Set y floating value between 0.000000 and 5.000000  
end function
;; ;'randomize' the random number generator!
;; SeedRnd MilliSecs()
;; For k=1 To 12345
;; Rnd(1)
;; Next
;; ;capture random number generator state
;; state=RndSeed()
;; ;generate a bunch of numbers:
;; Print "First set:"
;; For k=1 To 5
;; Print Rnd(1)
;; Next
;; ;restore random nummber generator state
;; SeedRnd state
;; ;generate another bunch:
;; Print "Second set (same as the first set...):"
;; For k=1 To 5
;; Print Rnd(1)
;; Next
;; Click <a href=http://www.blitzbasic.co.nz/b3ddocs/command.php?name=RndSeed&ref=comments target=_blank>here</a> to view the latest version of this page online</body>
;; </html>
;;param Returns the current random number seed value.
;;param This allows you to 'catch' the state of the random generator, usually for the purpose of restoring it later.
function RndSeed ()
end function
;; I'm going to start this description off with:
;; This command is not fast enough to render rotations in real time!
;; Now, the purpose of this command is to rotate an image a specified number of  degrees. Since it is slow, you will need to pre-calculate rotated images with  this command. This means, before the program actually displays the images you  rotate, you will want to rotate them ahead of time.
;; This command automatically dithers/anti-aliases the rotated graphic image, so  it might mess with your transparency. To avoid this issue, use the TFormFilter command. This will render the rotated  images with bi-linear filtering.
;; I'm going to end this command with:
;; This command is not fast enough to render rotations in real time!
;;param image = variable containing the image handle
;;param value# = floating number from 0 to 360 degrees
function RotateImage image,value#
; RotateImage/TFormFilter Example  
; Turn on graphics mode  
Graphics 640,480,16  
; Change the 0 to a 1 to see the difference  
; between filter on and off.  
TFormFilter 0  
; Create new empty graphic to store our circle in  
gfxBox=CreateImage(50,50)  
; Draw the box image  
; Set drawing operations to point to our new empty graphic  
SetBuffer ImageBuffer(gfxBox)  
Color 255,0,0  
; Note the extra space between the box and the edge of the graphic  
Rect 10,10,30,30,1  
SetBuffer FrontBuffer()  
While Not KeyHit(1)  
; Make a copy of the image so we are always using a fresh one each time  
; we rotate it.  
gfxTemp=CopyImage(gfxBox)  
; Rotate it a random value and draw it at a random location  
RotateImage gfxTemp,Rnd(360)  
DrawImage gfxTemp,Rnd(640),Rnd(480)  
Wend  
end function
;; If you have a string that is say, 10 letters long, but you want to make  it a full 25 letters, padding the rest of the string with spaces, this command  will do so, leaving the original string value right justified. You could use  this to pad your high score names to make sure all are the same width in characters.
;;param string$ = any valid string or string variable
;;param length = how long you want the new string to be (including padding)
function RSet$ (string$, length)
name$="Shane R. Monroe"  
Print "New Padded Name: '" + RSet$(name$,40) + "'"  
end function
;; When doing your own error trapping, use this command to pop up a fatal error  and close the program. You can specify the error message that is displayed.
;;param message$ = Any valid string
function RuntimeError message$
;There was a problem - raise an error and quit  
RuntimeError "Installation corrupted. Please reinstall."  
end function
;; This performs a right binary shift on the value the specified number of times.  This basically is a faster method of dividing the value exponentially. By shifting  right once, you are dividing the value by 2. By shifting right twice, you divide  by 4, etc.
;; Sar command varies from Shr whereas it fills blank bits  shifted with copies of the sign bit, 0 for positive numbers and 1 for negative.
;; The usefulness of this command is basically faster math execution. Also see Shl.
;; See also: <a class=small href=Shl.htm>Shl</a>, <a class=small href=Shr.htm>Shr</a>.
;;param repetitions = number of shifts to make right
function Sar repetitions
; shl, shr, sar examples  
value = 100  
; multiple x 2  
Print "Shift 1 bit left; Value = " + value Shl 1  
; multiple x 4  
Print "Shift 2 bits left; Value = " + value Shl 2  
; multiple x 16  
Print "Shift 4 bits left; Value = " + value Shl 4  
; divide by 2  
Print "Shift 1 bit right; Value = " + value Shr 1  
; divide by 4  
Print "Shift 2 bits right; Value = " + value Shr 2  
; divide by 16  
Print "Shift 4 bits right; Value = " + value Shr 4  
Print "Shift by SAR 4 times = " + value Sar 4  
WaitKey()  
end function
;; Typically, this is used to take a screen snapshot. This will save the screen  buffer you specify to a .bmp file you specify. Remember, use the proper name  for the buffer you wish to save; FrontBuffer() for the current visible  screen, and BackBuffer() for the back or invisible drawing buffer. The  filename must be valid Windows filename syntax.
;;param buffer = The buffer to save; FrontBuffer() or BackBuffer()
;;param filename$ = valid Windows path/filename
function SaveBuffer (buffer,filename$)
; Save the screen when player pushes F10  
If KeyHit(10) Then  
SaveBuffer(FrontBuffer(),"screenshot.bmp")  
End If  
end function
;; Saves an image or one of its frames to hard drive. You will need an image in memory to save. This returns a 1 if the save was successful, 0 if not.
;;param image = variable handle to the image to save
;;param bmpfile$ = string with full path and filename to save to
;;param frame = optional; which frame of the image to save
function SaveImage (image,bmpfile$[,frame] )
; SaveImage example  
; Set Graphics Mode  
Graphics 800,600,16  
; Load an image to tile (your location might vary)  
gfxBall=LoadImage("C:Program Files\Blitz Basic\samples\ball.bmp")  
; Save the image to the c: drive ...  
ok=SaveImage (gfxBall,"c:  
ewball.bmp")  
; Print results  
If ok=1 Then  
Print "Save successful!"  
Else  
Print "There was an error saving!"  
End If  
; Wait for ESC to hit  
While Not KeyHit(1)  
Wend  
end function
;; Use this command to rescale an image to a new size using a floating point  percentage (1.0 = 100%, 2.0 = 200%, etc). Using a negative value perform image  flipping. You must've previously loaded the image with LoadImage or LoadAnimImage.
;; This is NOT intended for REAL TIME scaling of images! Precalculate your images  before running your program, or you will likely see massively slow renderings  of graphics.
;;param image = file handle variable to a previously loaded image
;;param xscale# = the amount to scale the image horizontally
;;param yscale# = the amount to scale the image vertically
function ScaleImage image,xscale#,yscale#
; ScaleImage example  
; Set Graphics Mode  
Graphics 800,600,16  
; Randomize the random seed  
SeedRnd MilliSecs()  
; Load an image to tile (your location might vary)  
gfxBall=LoadImage("C:Program FilesBlitz Basicsamplesall.bmp")  
; Scale it randomly from 50% to 150% both x and y  
ScaleImage gfxBall,Rnd(-2.0,2.0),Rnd(-2.0,2.0)  
; Wait for ESC to hit  
While Not KeyHit(1)  
DrawImage gfxball,Rnd(800),Rnd(600)  
VWait  
Wend  
end function
;;param </html>

end function
;; If for some reason you need to know the current scanline location of the  drawing system, here is how you get it.
;; See also: <a class=small href=VWait.htm>VWait</a>, <a class=small href=Flip.htm>Flip</a>.
;;param None.
function ScanLine()
; ScanLine() Example  
While Not KeyHit(1)  
Print ScanLine()  
Wend  
end function
;; Computer random number generators are not truly random. They generate numbers  based on a seed value (an integer number). If you 'seed' the random number generator  with the same seed, it will always generate the same set of numbers. Use this  command to ensure you get a good set of numbers. Usually you set the seed value  to a timer or system clock value to ensure that each time the program is run,  a new value is seeded. Look at the example for normal usage of this command.
;;param seed = valid integer number
function SeedRnd seed
SeedRnd Millisecs() ; Seed the randomizer with the current system time in  milliseconds.  
end function
;; This command allows the position in a file to be changed. This allows random  access to data within files and can be used with files opened by ReadFile, WriteFile  and OpenFile. Note, the offset is the number of bytes from the start of the  file, where the first byte is at offset 0. It is important to take account of  the size of the data elements in your file.
;; For instance Integers are 4 bytes long so the first integer in the file is at  offset 0 and the second at offset 4. If you write Custom Data types out then  you must work out haw many bytes each takes so that you can move about the file  correctly. Seeking beyond the end of a file does not generate an error but the  data is not read or written to the file, and may course unknown side effects.
;; By using FilePos and SeekFile the position within the file that is being read  or written can be determined and also changed. This allows a file to be read  and updated without having to make a new copy of the file or working through  the whole file sequentially. This could be useful if you have created a database  file and you want to find and update just a few records within it. It is also  possible to create an index file that contains pointers to where each record  starts in a data file.
;; To calculate an offset you need to know how long each data element is; Offset  = Wanted_Element * size_of_element - size_of_element
;; For example a file of integers which are 4 bytes long is calculated by:
;; The 7th integer is at offset 7 * 4 - 4 i.e. 24
;; Note, extreme care needs to be exercised when updating files that contain strings  since these are not fixed in length.
;;param filehandle = the variable returned by the Readfile, WriteFile or OpenFile  when the file was opened. The value returned is the offset from the start of  the file. ( 0 = Start of the file )
function SeekFile (filehandle, offset)
; Changing part of a file using OpenFile, SeekFile, FilePos  
; Open/create a file to Write  
fileout = WriteFile("mydata.dat")  
; Write the information to the file  
WriteInt( fileout, 100 )  
WriteInt( fileout, 200 )  
WriteInt( fileout, 300 )  
WriteInt( fileout, 400 )  
WriteInt( fileout, 500 )  
; Close the file  
CloseFile( fileout )  
DisplayFile( "The file as originally written", mydata.dat" )  
Print "Data read in random order"  
; Open the file to read just the 4th and 2nd elements from  
file = OpenFile("mydata.dat")  
; read and print the 4th integer ie 4*4-4 = 12 byte from the start of the file  
SeekFile( file, 12 ) ; Move to the found location  
Number = ReadInt( file )  
Print Number  
; read and print the 2th integer ie 2*4-4 = 4 bytes from the start of the file  
SeekFile( file, 4 ) ; Move to the found location  
Number = ReadInt( file )  
Print Number  
CloseFile( file )  
Waitkey()  
End ; End of program  
; **** Function Definitions follow ****  
; Read the file and print it  
Function DisplayFile( Tittle$, Filename$ )  
Print tittle$  
file = ReadFile( Filename$ )  
While Not Eof( file )  
Number = ReadInt( file )  
Print Number  
Wend  
CloseFile( file )  
Print  
End Function  
end function
;; This command allows you to set up a selection structure that, based on the  value of the variable you feed it, will execute different commands in different CASEs. You can also specify a DEFAULT  set of commands to happen if NONE of the CASEs are met. The selection structure  is ended with an END SELECT command.
;; This selection structure removes the need for large nested IF/THEN condition  checking. See the example for more.
;; See also: <a class=small href=Case.htm>Case</a>, <a class=small href=Default.htm>Default</a>, <a class=small href=True.htm>True</a>, <a class=small href=False.htm>False</a>, <a class=small href=If.htm>If</a>.
;;param variable - any valid variable
function Select variable
; SELECT/CASE/DEFAULT/END SELECT Example  
; Assign a random number 1-10  
mission=Rnd(1,10)  
; Start the selection process based on the value of 'mission' variable  
Select mission  
; Is mission = 1?  
Case 1  
Print "Your mission is to get the plutonium and get out alive!"  
; Is mission = 2?  
Case 2  
Print "Your mission is to destroy all enemies!"  
; Is mission = 3?  
Case 3  
Print "Your mission is to steal the enemy building plans!"  
; What do do if none of the cases match the value of mission  
Default  
Print "Missions 4-10 are not available yet!"  
; End the selection process  
End Select  
end function
;; First off, this ONLY works when you have joined a network game via StartNetGame or JoinNetGame  and you have created a player via CreateNetPlayer  (you must create a player, even if it is just to lurk).
;; This is probably the most complicated of the networking commands. This what  you use to actually send a message to one or all of the players on the network  game. The other players will use RecvNetMsg()  to intercept your message.
;; The TYPE parameter is a number from 1 to 99. These values are denoted as 'user  messages'.
;; The Data$ parameter is the actual string that contains the message you want  to send. Helpful to know that in order to keep traffic low, you will want to  combine details of a message into a single message instead of sending multiple  messages with a single element. For example, you might want to send X, Y, and  FRAME in a single string like "200,100,4" and parse it out at the recipient's  end.
;; FROM is the player's ID that is sending the message. This is the value returned  from the CreateNetPlayer() command.
;; TO is the player's ID you wish to send the message to. A default value of 0  will broadcast to ALL players.
;; The RELIABLE flag will put a priority on the message and it will ensure there  is no packet loss in the delivery. However, it is at least 3 times slower than  a regular non-reliable message.
;; The example requires that you run it on the local machine while the remote computer  runs the example in the RecvNetMsg() command.
;;param type = value 1-99
;;param data$ = string containing message to send
;;param from = player ID of the sender
;;param to = player ID of the recipient (0=broadcast)
;;param reliable = flag for sending message reliably
function SendNetMsg type,data$,from,to,reliable
; SendNetMsg example  
; ------------------  
; Run this example on the local computer  
; run the example for RecvNetMsg() on a remote computer  
; Graphics mode with double buffering  
Graphics 640,480,16  
SetBuffer BackBuffer()  
; Create a network game with NO requester  
joinStatus=HostNetGame("ShaneGame")  
; A type to hold all the player's information  
Type multi  
Field x  
Field y  
Field id  
Field name$  
Field xspeed  
Field boxColor  
End Type  
; make sure the game started ok...  
If joinStatus=2 Then  
Print "Hosted game started... "  
Else  
Print "Hosted game could not be started!"  
End  
End If  
; Create 5 local players using TYPEs  
For t = 1 To 5  
; New type instance  
player.multi = New Multi  
; Assign the ID field with the created player ID and name him  
playerID=CreateNetPlayer("Player" + t)  
; if the player was created ok ... assign some random parameters  
If playerID <> 0 Then  
player  
ame$="Player" + t  
playerx = Rand(640)  
playery = Rand(480)  
playeroxColor = Rand(255)  
playerxspeed = Rand(1,5)  
; Print some text results  
Print "Player " + t + " has joined the game with ID=" + playerID  
Else  
Print "The player couldn't join! Aborting!"  
End If  
Next  
; We've got them all! Wait for a key  
Print "All local players are joined! Press a key ..."  
WaitKey()  
; Loop this routine  
While Not KeyHit(1)  
Cls  
; for each of the players, update their locations on the screen  
For player = Each multi  
Color playeroxColor,playeroxColor,playeroxColor  
Rect playerx,playery,10,10,1  
Text playerx-10,playery-15,player  
ame$  
playerx = playerx + playerxspeed  
If playerx > 640 Or playerx < 0 Then  
playerxspeed=-playerxspeed  
message$="Player ID #" + playerID + " hit a wall!"  
; Send a broadcast message if a player rebounds off the wall  
; this message will show up on the remote machine  
SendNetMsg Rand(1,99),message$,playerid,0  
End If  
Next  
Flip  
Wend  
End  
end function
;; Transmits all the data written to the UDP stream to the specified IP address  and port. Data is written using the standard stream commands. If no destination  port is specified, the port number used to create the UDP Stream is used.
;; Note that IP addresses must be in integer format, NOT in dotted IP format.
;; Click <a href=http://www.blitzbasic.co.nz/b3ddocs/command.php?name=SendUDPMsg&ref=comments target=_blank>here</a> to view the latest version of this page online</body>
;; </html>
;;param udp_stream - UDP stream handle
;;param dest_ip - destination IP address
;;param dest_port (optional) - destination port number
function SendUDPMsg udp_stream,dest_ip[,dest_port]
end function
;; Use this command to set the current drawing buffer. If not used the default  buffer, FrontBuffer() is used. SetBuffer also resets the origin to 0,0 and the  Viewpoint to the width and height of the buffer.
;;param Buffers can either be the FrontBuffer(), BackBuffer() or an ImageBuffer()  Default buffer is the FrontBuffer
function SetBuffer buffer
SetBuffer FrontBuffer() ;Sets FrontBuffer as the current drawing buffer  
end function
;; Sets an environment variable.
;; However, it should be noted that the command does not set a Windows environment variable. It only sets an environment variable which is local to a single Blitz program - i.e. from the moment the SetEnv command is used, it will only exist for the duration of time the source code file is open in the Blitz IDE, or the program EXE is running.
;; See also: GetEnv
;;param env_var$ - the name of the environment variable
;;param value$ - the value of the environment variable
function SetEnv env_var$,value$
; SetEnv Example  
; --------------  
SetEnv "mypath","c:\program files\my game\"  
Print GetEnv$("mypath")  
WaitKey()  
end function
;; This activates a TrueType font previously loaded into memory (though the LoadFont command) for future use with printing commands  such as Text.
;; Note: Blitz doesn't work with SYMBOL fonts, like Webdings and WingDings.
;; Be sure to free the memory used by the font went you are done using the FreeFont command.
;;param fontname = string containing font name
;;param height = font height desired
;;param bold = set to TRUE to load font as BOLD
;;param italic = set to TRUE to load font as ITALIC
;;param underlined set to TRUE to load font as UNDERLINED
function SetFont fonthandle
; LoadFont/SetFont/FreeFont example  
; enable graphics mode  
Graphics 800,600,16  
; Set global on font variables  
Global fntArial,fntArialB,fntArialI,fntArialU  
;Load fonts to a file handle variables  
fntArial=LoadFont("Arial",24,False,False,False)  
fntArialB=LoadFont("Arial",18,True,False,False)  
fntArialI=LoadFont("Arial",32,False,True,False)  
fntArialU=LoadFont("Arial",14,False,False,True)  
; set the font and print text  
SetFont fntArial  
Text 400,0,"This is just plain Arial 24 point",True,False  
SetFont fntArialB  
Text 400,30,"This is bold Arial 18 point",True,False  
SetFont fntArialI  
Text 400,60,"This is italic Arial 32 point",True,False  
SetFont fntArialU  
Text 400,90,"This is underlined Arial 14 point",True,False  
; Standard 'wait for ESC' from user  
While Not KeyHit(1)  
Wend  
; Clear all the fonts from memory!  
FreeFont fntArial  
FreeFont fntArialB  
FreeFont fntArialI  
FreeFont fntArialU  
end function
;; SetGamma allows you to modify the gamma tables.
;; Gamma can ONLY be used in fullscreen mode.
;; After performing one or more SetGamma commands, you must call <a class=small href=../2d_commands/UpdateGamma.htm>UpdateGamma</a> in order for the changes to become effective.
;; Note that the behaviour of this command in Blitz3D is different to BlitzPlus.  Blitz3D will clamp out of range values, so that 300 is treated as 255.  BlitzPlus allows values to "roll-over", so that 300 is treated as 44.
;; See also: <a class=small href=UpdateGamma.htm>UpdateGamma</a>.
;;param red,green,blue - specifies the source red,green,blue components.
;;param dest_red#,dest_green#,dest_blue - specifies the destination red,green,blue components.
function SetGamma red,green,blue,dest_red#,dest_green#,dest_blue#
;gamma demo - use left/right arrows to  
;control display intensity  
;  
;hold down left-control for a 'red-out' effect!  
Graphics 640,480,16,1  
SetBuffer BackBuffer()  
n=0  
While Not KeyHit(1)  
If KeyDown(203) And n>0 Then n=n-1  
If KeyDown(205) And n<255 Then n=n+1  
If KeyDown(29)  
SetGammaRed(n)  
Else  
SetGammaIntensity(n)  
EndIf  
Cls  
SeedRnd 1234  
For k=1 To 1000  
Color Rnd(255),Rnd(255),Rnd(255)  
Rect Rnd(640),Rnd(480),Rnd(64),Rnd(64)  
Next  
Text 0,0,"Intensity offset="+n  
Flip  
Wend  
End ; bye!  
Function SetGammaRed( n )  
For k=0 To 255  
SetGamma k,k,k,k+n,0,0  
Next  
UpdateGamma  
End Function  
Function SetGammaIntensity( n )  
For k=0 To 255  
SetGamma k,k,k,k+n,k+n,k+n  
Next  
UpdateGamma  
End Function  
end function
;; Some computers may have more than one video card and/or video driver installed  (a good example is a computer system with a primary video card and a Voodoo2  or other pass-through card).
;; Once you know how many drivers there are using the CountGfxDrivers(), you can iterate through  them with GfxDriverName$ and display them for  the user to choose from. Once the user has chosen (or you decide), you can set  the graphics driver with this command.
;; Normally, this won't be necessary with 2D programming.
;;param index = index number obtained with CountGfxDrivers command
function SetGfxDriver index
; GfxDriver Examples  
; Count how many drivers there are  
totalDrivers=CountGfxDrivers()  
Print "Choose a driver to use:"  
; Go through them all and print their names (most people will have only 1)  
For t = 1 To totalDrivers  
Print t+") " + GfxDriverName$(t)  
Next  
; Let the user choose one  
driver=Input("Enter Selection:")  
; Set the driver!  
SetGfxDriver driver  
Print "Your driver has been selected!"  
end function
;; This function is used to determine whether a number or value is greater  than 0, equal to 0 or less than 0. Note: non-integer values return the sign  to 7 signigicant figures. (e.g. -1.000000)
;;param number=float or integer
function Sgn (number)
Print Sgn(10) ; prints 1  
Print Sgn(5.5) ; prints 1.000000  
Print Sgn(0) ; prints 0  
Print Sgn(0.0) ; prints 0.000000  
Print Sgn(-5.5) ; prints -1.000000  
Print Sgn(-10) ; prints -1  
end function
;; This performs a left binary shift on the value the specified number of times.  This basically is a faster method of multiplying the value exponentially. By  shifting left once, you are multiplying the value by 2. By shifting left twice,  you multiple by 4, etc.
;; The usefulness of this command is basically faster math execution.
;;param repetitions = number of shifts to make left
function Shl repetitions
; shl, shr, sar examples  
value = 100  
; multiple x 2  
Print "Shift 1 bit left; Value = " + value Shl 1  
; multiple x 4  
Print "Shift 2 bits left; Value = " + value Shl 2  
; multiple x 16  
Print "Shift 4 bits left; Value = " + value Shl 4  
; divide by 2  
Print "Shift 1 bit right; Value = " + value Shr 1  
; divide by 4  
Print "Shift 2 bits right; Value = " + value Shr 2  
; divide by 16  
Print "Shift 4 bits right; Value = " + value Shr 4  
Print "Shift by SAR 4 times = " + value Sar 4  
WaitKey()  
end function
;; ShowPointer is for use in windowed display modes, and simply shows the Windows  pointer after it's been hidden (via HidePointer).  It has no effect in full-screen modes.
;; See also: <a class=small href=HidePointer.htm>HidePointer</a>.
;;param None.
function ShowPointer
; HidePointer / ShoPointer Example  
; draw a simple screen, cut in half by a white line  
Graphics 800,600,0,2  
Color 255,255,255  
Line 400,0,400,600  
Text 200,300,"ShowPointer",True,True  
Text 600,300,"HidePointer",True,True  
; and a simple loop in which we hide / show the pointer dependent on  
; which side of the screen the mouse is on!  
Repeat  
If MouseX()<400 Then  
ShowPointer  
Else  
HidePointer  
End If  
If KeyHit(1) Then Exit ; ESCAPE to exit  
Forever  
End ; bye!  
end function
;; This performs a left binary shift on the value the specified number of times.  This basically is a faster method of dividing the value exponentially. By shifting  right once, you are dividing the value by 2. By shifting right twice, you divide  by 4, etc.
;; The usefulness of this command is basically faster math execution.
;;param repetitions = number of shifts to make right
function Shr repetitions
; shl, shr, sar examples  
value = 100  
; multiple x 2  
Print "Shift 1 bit left; Value = " + value Shl 1  
; multiple x 4  
Print "Shift 2 bits left; Value = " + value Shl 2  
; multiple x 16  
Print "Shift 4 bits left; Value = " + value Shl 4  
; divide by 2  
Print "Shift 1 bit right; Value = " + value Shr 1  
; divide by 4  
Print "Shift 2 bits right; Value = " + value Shr 2  
; divide by 16  
Print "Shift 4 bits right; Value = " + value Shr 4  
Print "Shift by SAR 4 times = " + value Sar 4  
WaitKey()  
end function
;; Sine of an angle. The angle is measured in degrees.
;; For angles between 0 and 90 degrees this is defined by the sides of a right triangle. The sine is the side opposite the angle divided by the hypotenuse.
;; Outside of 0 to 90 the definition uses a circle with radius=1. The angle is placed at the center of the circle, with one side on the positive x-axis. The other side hits the circle at some point. The y coordinate of this point is the sine of the angle.
;; The positive y-axis corresonds to +90 degrees. This is a common source of confusion in Blitz. With screen coordinates ( pixels ) the y-axis points downward. But in the 3d world the y-axis typically points upward.
;; Another possible snag is the size of the angle. In principle, the sine function repeats every 360 degrees. So Sin(-360), Sin(0), Sin(360), Sin(720) etc. should all be exactly the same. But in practice the accuracy decreases as the angle gets farther away from zero.
;; See also ASin, Cos, ACos, Tan, Atan, ATan2
;;param degrees# = angle in degrees.
function Sin# ( degrees# )
; Sin / Cos / Tan example.  
; Left/Right arrow keys change angle. Escape quits.  
Const width = 640, height = 480  
Const radius# = .2 * height  
Const KEY_ESC = 1, KEY_LEFT = 203, KEY_RIGHT = 205  
Graphics width, height  
SetBuffer BackBuffer( )  
Origin width / 3, height / 2  
angle# = 0.0  
While Not KeyDown( KEY_ESC )  
; NOTE: It is usually best to avoid very large angles.  
; The 'If angle...' lines show one way to do this.  
; Mod is another possibility.  
If KeyDown( KEY_LEFT ) Then angle = angle - .5  
; If angle < 0.0 Then angle = angle + 360  
If KeyDown( KEY_RIGHT ) Then angle = angle + .5  
; If angle >= 360.0 Then angle = angle - 360  
Cls  
Color 80, 80, 0 ; pale yellow circle  
Oval -radius, -radius, 2 * radius, 2 * radius, False  
For a# = 0.0 To Abs( angle Mod 360 ) Step .5  
x# = radius * Cos( a ) ; (x,y) is a point on the circle  
y# = radius * Sin( a ) ; corresponding to angle a.  
If ( angle Mod 360 < 0 ) Then y = -y ; reverse for negative angle  
WritePixel x, y, $ffff00 ; bright yellow  
Next  
Color 255, 255, 0 ; yellow  
Line 0, 0, radius * Cos( angle ), radius * Sin( angle )  
Color 0, 255, 0 ; green  
Line 0, 0, radius * Cos( angle ), 0  
Text radius * 1.5, 10, "Cos( angle ) = " + Cos( angle )  
Color 255, 0, 0 ; red  
Line radius * Cos( angle ), 0, radius * Cos( angle ), radius * Sin( angle )  
Text radius * 1.5, -10, "Sin( angle ) = " + Sin( angle )  
Color 255, 255, 255  
Text radius * 1.5, -30, " angle = " + angle  
Text radius * 1.5, 30, "Tan( angle ) = " + Tan( angle )  
Flip  
Wend  
End  
end function
;; Use this command to pan your sound effect between the left and right speakers  (or restore the panning to the center). Use this for cool panning stereo sounds  during your game.
;;param sound_variable = any valid sound variable previously created with the  LoadSound command.
;;param pan# = floating point number from -1 (left) to 0 (center) to 1 (right)
function SoundPan sound_variable,pan#
; Load sound sample  
sndDeath=LoadSound("audiodeath.wav")  
; Pan sound effect half to the left  
SoundPan sndDeath,-.5  
; Play sound  
PlaySound sndDeath  
end function
;; Alters the pitch of a sound previously loaded with the LoadSound command. By changing the pitch, you can  often reuse sounds for different uses or to simulate a 'counting up/down' sound.  To make the sound 'higher pitched', increase the hertz. Conversely, decreasing the hertz will 'lower' the pitch. Note: this is in relation to the original hertz frequency of the sound.
;;param sound_variable = any valid sound variable previously created with the LoadSound  command.
;;param hertz = valid playback hertz speed (up to 44000 hertz).
function SoundPitch sound_variable, hertz
; Load the sound (11,000 hertz)  
snd1Up = LoadSound("audiooneup.wav")  
; Play the sound normally  
PlaySound snd1Up  
; Change the pitch UP and play it  
SoundPitch snd1Up, 11000*2 ;twice original frequency  
PlaySound snd1Up  
; Change the pitch down and play it  
SoundPitch snd1Up, 11000/2 ;1/2 original frequency  
PlaySound snd1Up  
end function
;; Alter the playback volume of your sound effect with this command. This command  uses a floating point number from 0 to 1 to control the volume level.
;; Please see ChannelVolume for more options!
;;param sound_variable = any valid sound variable previously created with the LoadSound  command.
;;param volume# = floating point number from 0 (silence) to 1 (full volume)
function SoundVolume sound_variable,volume#
; Load sound sample  
sndDeath=LoadSound("audiodeath.wav")  
; Change volume level to half  
SoundVolume sndDeath,.5  
; Play sound  
PlaySound sndDeath  
end function
;; This command will return the square root of a specified value. The value  returned is a floating point number.
;;param float = any floating point number (integers are converted on the fly)
function Sqr (float)
; sqr# example  
value=25  
print "The square root of our value is: " + sqr#(value)  
waitkey()  
end function
;; Displays a Windows dialog with option to join or start a new multiplayer  network game, via modem, serial connection or TCP/IP (Internet).
;; Note: This command must be started before any other network commands, otherwise  they will fail.
;; A return value of 0 indicates failure, 1 means a game was joined and 2 means  a game was created and is being hosted on the local machine.
;;param None.
function StartNetGame()
newGame = StartNetGame()  
; Check the status of the new game.  
If newGame = 0 Then  
print "Could not start or join net game."  
ElseIf newGame = 1  
print "Successfully joined the network game."  
ElseIf newGame = 2  
print "A new network game was started."  
EndIf  
end function
;; Use this to tell your FOR ... NEXT  loop to increment a certain value each pass through the loop. STEP 1 is assumed  and need not be declared. STEP 2 would skip every other value, STEP 3 would  skip every third value, etc.
;; See also: <a class=small href=For.htm>For</a>, <a class=small href=To.htm>To</a>, <a class=small href=Each.htm>Each</a>, <a class=small href=Next.htm>Next</a>, <a class=small href=Exit.htm>Exit</a>, <a class=small href=While.htm>While</a>, <a class=small href=Repeat.htm>Repeat</a>.
;;param None.
function Step
; Print 1 through 100, by tens  
For t = 1 To 100 Step 10  
Print t  
Next  
end function
;; If running the program in debug mode, it this command halts the program  and returns you to the editor where you can then step through your code, view  variables, etc.
;; See also: <a class=small href=DebugLog.htm>DebugLog</a>, <a class=small href=End.htm>End</a>.
;;param None
function Stop
; Halt the program and go to the editor/debugger  
Stop  
end function
;; This command replaced StopSound in the latter  versions of Blitz Basic.
;; Once you have a sound playing, and a channel variable attached to it, you use  this command to stop the sound. This works for all sound channel types, including  MP3, WAV, MIDI, and CD track playback.
;;param channel_handle = variable assigned to the channel when played
function StopChannel channel_handle
; Channel examples  
Print "Loading sound ..."  
; Load the sample - you'll need to point this to a sound on your computer  
; For best results, make it about 5-10 seconds...  
sndWave=LoadSound("level1.wav")  
; Prepare the sound for looping  
LoopSound sndWave  
chnWave=PlaySound(sndWave)  
Print "Playing sound for 2 seconds ..."  
Delay 2000  
Print "Pausing sound for 2 seconds ..."  
PauseChannel chnWave  
Delay 2000  
Print "Restarting sound ..."  
ResumeChannel chnWave  
Delay 2000  
Print "Changing Pitch of sound ..."  
;StopChannel chnWave  
ChannelPitch chnWave, 22000  
Delay 2000  
Print "Playing new pitched sound ..."  
Delay 2000  
Print "Left speaker only"  
ChannelPan chnWave,-1  
Delay 2000  
Print "Right speaker only"  
ChannelPan chnWave,1  
Delay 2000  
Print "All done!"  
StopChannel chnWave  
end function
;; Use this command to terminate the network game currently in progress (started  with the StartNetGame() command). If possible,  the hosting session will transfer to another machine connected to the network  game.
;;param None.
function StopNetGame
; stopNetGame() example  
newGame = StartNetGame()  
; Check the status of the new game.  
If newGame = 0 Then  
print "Could not start or join net game."  
ElseIf newGame = 1  
print "Successfully joined the network game."  
ElseIf newGame = 2  
print "A new network game was started."  
EndIf  
waitkey()  
StopNetGame()  
print "The Network game was stopped."  
end function
;; Use this command to transform an numeric value to a string value for use  with string commands. Blitz prints numeric values just fine, but should you  want to do functions like LEFT$, you'll need to convert  your numeric variable to a string variable. Note: during the conversion, all  6 decimal places will be represented on floating point number conversions.
;; If you wish to convert from a string to a number, there is no equivalent Val command.  Instead, simply assign the string variable into a numeric variable, and Blitz will implicitly convert it.
;;param variable/value = numeric value or variable
function Str variable/value
; STR example  
num#=2.5  
mynum$=str num#  
Print mynum$  
end function
;; This makes a string filled with the specified occurances of the designated  string. In other words, you could use this command to write the same string  over and over again. See the example.
;;param string$ = any valid string or string variable
;;param integer = the number of times to repeat the string
function String$ (string$, integer)
name$="Shane"  
' Write the name string 10 times  
Print String$(name$,10)  
end function
;; This will return the size, in pixels, the height of the indicated string.  This is useful for determining screen layout, scrolling of text, and more. This  is calculated based on the size of the currently loaded font.
;;param string = any valid string or string variable
function StringHeight (string)
; StringWidth/Height Example  
a$="Hello Shane!"  
Print "A$=" + a$  
Print "This string is "+ StringWidth(a$) + " pixels wide and"  
Print "it is " + StringHeight(a$) + " tall, based on the current font!"  
end function
;; This will return the size, in pixels, the width of the indicated string.  This is useful for determining screen layout, scrolling of text, and more. This  is calculated based on the size of the currently loaded font.
;;param string = any valid string or string variable
function StringWidth (string)
; StringWidth/Height Example  
a$="Hello Shane!"  
Print "A$=" + a$  
Print "This string is "+ StringWidth(a$) + " pixels wide and"  
Print "it is " + StringHeight(a$) + " tall, based on the current font!"  
end function
;; SystemProperty is used to 'find out' certain system-specific things that are external to the currently running program.
;; There are broadly two different uses for SystemProperty, one for finding out the location of certain folders on the Windows OS, and one for finding out the handles/objects being used by the Win32/DX APIs to run Blitz programs. These objects and handles can then be used via third party DLLs to add extra functionality to Blitz, although this is recommended for advanced users only.
;; Properties - folders:
;; systemdir - System folder
;; windowsdir - Windows folder
;; tempdir - Temp folder
;; appdir - Program Files folder
;; Properties - objects/handles:
;; Direct3D7
;; Direct3DDevice7
;; DirectDraw7
;; DirectInput7
;; AppHWND
;; AppHINSTANCE
;;param property$ - system property information required (valid strings listed below)
function SystemProperty (property$)
Print "System folder location: " + SystemProperty ("systemdir")  
Print "Windows folder location: " + SystemProperty ("windowsdir")  
Print "Temp folder: " + SystemProperty ("tempdir")  
Print "Program was run from " + SystemProperty ("appdir")  
end function
;; Tangent of an angle. The angle is measured in degrees.
;; For angles between 0 and 90 degrees this is defined by the sides of a right triangle. The tangent is the side opposite the angle divided by the side adjacent to the angle.
;; In general, tangent is defined as sine divided by cosine.
;; The positive y-axis corresonds to +90 degrees. This is a common source of confusion in Blitz. With screen coordinates ( pixels ) the y-axis points downward. But in the 3d world the y-axis typically points upward.
;; Another possible snag is the size of the angle. In principle, the tangent function repeats every 180 degrees. So Tan(-180), Tan(0), Tan(180), Tan(360) etc. should all be exactly the same. But in practice the accuracy decreases as the angle gets farther away from zero.
;;param degrees# = angle in degrees.
function Tan# ( degrees# )
; Sin / Cos / Tan example.  
; Left/Right arrow keys change angle. Escape quits.  
Const width = 640, height = 480  
Const radius# = .2 * height  
Const KEY_ESC = 1, KEY_LEFT = 203, KEY_RIGHT = 205  
Graphics width, height  
SetBuffer BackBuffer( )  
Origin width / 3, height / 2  
angle# = 0.0  
While Not KeyDown( KEY_ESC )  
; NOTE: It is usually best to avoid very large angles.  
;       The 'If angle...' lines show one way to do this.  
;		Mod is another possibility.  
If KeyDown( KEY_LEFT ) Then angle = angle - .5  
;	If angle < 0.0 Then angle = angle + 360  
If KeyDown( KEY_RIGHT ) Then angle = angle + .5  
;	If angle >= 360.0 Then angle = angle - 360  
Cls  
Color 80, 80, 0		; pale yellow circle  
Oval -radius, -radius, 2 * radius, 2 * radius, False  
For a# = 0.0 To Abs( angle Mod 360 ) Step .5  
x# = radius * Cos( a )	; (x,y) is a point on the circle  
y# = radius * Sin( a )	; corresponding to angle a.  
If ( angle Mod 360 < 0 ) Then y = -y	; reverse for negative angle  
WritePixel x, y, $ffff00		; bright yellow  
Next  
Color 255, 255, 0		; yellow  
Line 0, 0, radius * Cos( angle ), radius * Sin( angle )  
Color 0, 255, 0			; green  
Line 0, 0, radius * Cos( angle ), 0  
Text radius * 1.5,  10, "Cos( angle ) = " + Cos( angle )  
Color 255, 0, 0			; red  
Line radius * Cos( angle ), 0, radius * Cos( angle ), radius * Sin( angle )  
Text radius * 1.5, -10, "Sin( angle ) = " + Sin( angle )  
Color 255, 255, 255  
Text radius * 1.5, -30, "     angle =   " + angle  
Text radius * 1.5,  30, "Tan( angle ) = " + Tan( angle )  
Flip  
Wend  
End  
end function
;; Returns the integer IP address of the specified tcp_stream. The address  returned is always that of the client machine.
;; Click <a href=http://www.blitzbasic.co.nz/b3ddocs/command.php?name=TCPStreamIP&ref=comments target=_blank>here</a> to view the latest version of this page online</body>
;; </html>
;;param tcp_stream - TCP stream handle
function TCPStreamIP( tcp_stream )
end function
;; Returns the port number of the specified TCP stream. The port number returned  is always that of the client machine.
;; Click <a href=http://www.blitzbasic.co.nz/b3ddocs/command.php?name=TCPStreamPort&ref=comments target=_blank>here</a> to view the latest version of this page online</body>
;; </html>
;;param tcp_stream - TCP stream handle
function TCPStreamPort( tcp_stream )
end function
;; read_millis allows you to control how long reading data into a TCP stream  can take before causing an error. By default, this is set to 10,000 (10 seconds).  This means that if data takes longer than 10 seconds to arrive, an error occurs  and the stream can not be used any more.
;; accept_millis allows you to control how the AcceptTCPStream()  function will wait for a new connection. By default, this value is 0, so AcceptTCPStream() will return immediately  if there is no new connection available.
;;param read_millis - milliseconds value
;;param accept_millis - milliseconds value
function TCPTimeouts read_millis,accept_millis
None.  
end function
;; Prints a string at the designated screen coordinates. You can center the  text on the coordiates by setting center x/center y to TRUE. This draws the  text in the current drawing color.
;; Note: Printing a space with text will NOT render a block - a space is an empty  value. So printing " " will not make a box appear.
;;param x = starting x coordinate to print text
;;param y = starting 4 coordinate to print text
;;param string$ = string/text to print
;;param center x = optional; true = center horizontally
;;param center y = optional; true = center vertically
function Text x,y,string$,[center x],[center y]
; Text example  
; enable graphics mode  
Graphics 800,600,16  
; wait for ESC key before ending  
While Not KeyHit(1)  
;print the text, centered horizontally at x=400, y=0  
Text 400,0,"Hello There!",True,False  
Wend  
end function
;; This command will enable or disable bi-linear filtering on images that are  convoluted (altered) by commands like TFormImage  and RotateImage.
;; This filtering allows the convoluted graphics to have smoother, more aliased  edges. This also makes the operations slower. The bi-linear filtering can also  create non-transparent edges what will mess with your transparency. Experiment  for the best results.
;; Try changing the example to see the difference.
;;param enable = 0 to turn off filtering; 1 to turn it on
function TFormFilter enable
; RotateImage/TFormFilter Example  
; Turn on graphics mode  
Graphics 640,480,16  
; Remove the line below to see the difference  
; between filter on and off.  
TFormFilter 0  
; Create new empty graphic to store our circle in  
gfxBox=CreateImage(50,50)  
; Draw the box image  
; Set drawing operations to point to our new empty graphic  
SetBuffer ImageBuffer(gfxBox)  
Color 255,0,0  
; Note the extra space between the box and the edge of the graphic  
Rect 10,10,30,30,1  
SetBuffer FrontBuffer()  
While Not KeyHit(1)  
; Make a copy of the image so we are always using a fresh one each time  
; we rotate it.  
gfxTemp=CopyImage(gfxBox)  
; Rotate it a random value and draw it at a random location  
RotateImage gfxTemp,Rnd(360)  
DrawImage gfxTemp,Rnd(640),Rnd(480)  
Wend  
end function
;; Transforms an image based on a 2x2 matrix.  The input parameters relate to the elements of the matrix as below:
;; ( a# b# )
;; ( c# d# )
;; The image is treated as a matrix and is multiplied by the matrix above.  Unless you understand matrix manipulation thoroughly, it is unlikely that you will find this command useful.
;; Common uses for this command might be to rotate or scale images.  These are available independently using <a class=small href=../2d_commands/ScaleImage.htm>ScaleImage</a> and <a class=small href=../2d_commands/RotateImage.htm>RotateImage</a>  However, using this command it is possible to do both operations simultaneously.  It is also possible to shear images using this command.
;; It is strongly recommended that you run the (rather lengthy) example below to better understand how this command can be used.
;; See also: <a class=small href=ScaleImage.htm>ScaleImage</a>, <a class=small href=RotateImage.htm>RotateImage</a>.
;;param image - image handle
;;param a# - 1,1 element of 2x2 matrix
;;param b# - 2,1 element of 2x2 matrix
;;param c# - 1,2 element of 2x2 matrix
;;param d# - 2,2 element of 2x2 matrix
function TFormImage image,a#,b#,c#,d#
; Set up the Blitz evironment and user controlled input parameters  
Global a#=1 ; This controls the horizontal scale, negative values cause the image to flip.  Zero is invalid.  
Global b#=0 ; This shears the image vertically  
Global c#=0 ; This shears the image horizontally  
Global d#=1 ; This controls the vertical scale, negative values cause the image to flip.  Zero is invalid.  
Graphics 600,400,16  
; Create an image that we can manipulate  
Global img=CreateImage(50,50)  
SetBuffer ImageBuffer(img)  
Color 255,0,0 : Rect 0,0,25,25,1  
Color 255,255,0 : Rect 25,25,25,25,1  
Color 0,255,0 : Rect 25,0,25,25,1  
Color 0,0,255 : Rect 0,25,25,25,1  
; Set up drawing & timing stuff  
SetBuffer BackBuffer()  
timer=CreateTimer(30)  
Repeat  
; deal with keyboard inputs  
If KeyDown(42) Or KeyDown(54) Then inc#=-0.1 Else inc#=0.1  
If KeyDown(30) Then a#=a#+inc#  
If KeyDown(48) Then b#=b#+inc#  
If KeyDown(46) Then c#=c#+inc#  
If KeyDown(32) Then d#=d#+inc#  
If KeyHit(57) Then RunDemo()  
If KeyHit(1) Then Exit  
; draw the screen  
DrawScreen()  
WaitTimer timer  
Forever  
End  
; Rolling Demo  
Function RunDemo()  
degrees=0 ; the number of degrees by which to rotate the image  
scale#=1.0 ;  the scale of the image  
timer=CreateTimer(10)  
Repeat  
a#=scale#:b#=0:c#=0:d#=scale# ; reset the matrix  
; increase the degrees (or reset if >360)  
If degrees<360 Then degrees=degrees+4 Else degrees=0  
; and set up the matrix multipliers.  The explanation as to why these work can be found in most good  
; mathematics text books, and is too involved to go into here!  
ma#=Cos(degrees)  
mb#=Sin(degrees)  
mc#=-Sin(degrees)  
md#=Cos(degrees)  
; multiply the two matrices.  
na#=a#*ma#+b#*mc#  
nb#=a#*mb#+b#*md#  
nc#=c#*ma#+d#*mc#  
nd#=c#*mb#+d#*md#  
a#=na#:b#=nb#:c#=nc#:d#=nd#  
; handle input and draw the screen  
If KeyHit(1) Then Exit  
If KeyDown(200) And scale#<2.0 Then scale#=scale#+0.1  
If KeyDown(208) And scale#>0.1 Then scale#=scale#-0.1  
DrawScreen(True)  
WaitTimer timer  
Forever  
a#=1:b#=0:c#=0:d#=1 ; reset the matrix  
FlushKeys()  
End Function  
; Draw stuff on the screen, including transforming the image  
Function DrawScreen(demo=False)  
ClsColor 0,0,0  
Cls  
DrawBlock img,10,10  
Text 100,5,"TFORMIMAGE EXAMPLE"  
Text 100,20,"The command format is: TFormImage image, a, b, c, d"  
If Not demo Then  
Text 100,35,"Press 'Space' for a rolling demo"  
Text 100,50,"Use the keys a, b, c, and d to increase the parameters"  
Text 100,65,"Also hold down shift to decrease each of these parameters"  
Else  
Text 100,35,"Use the up and down arrow keys to change the scale"  
End If  
Text 100,80,"Press 'Escape' to exit"  
Text 10,110,"Current Parameters:"  
; ######################## This is where we actually use TFormImage using the input parameters  
Text 20,125,"TFormImage image, "+a#+", "+b#+", "+c#+", "+d#  
img2=CopyImage(img)  
TFormImage img2,a#,b#,c#,d#  
; #############################################################################  
If demo Then  
DrawBlock img2,300,280  
Else  
DrawBlock img2,300-ImageWidth(img2)/2,280-ImageHeight(img2)/2  
EndIf  
Flip  
End Function  
end function
;; Used in an IF statement to denote the end of the conditional  to be checked. Famous for its participation in the IF ... THEN structure. See  example and IF statement for more information.
;; See also: <a class=small href=If.htm>If</a>, <a class=small href=Else.htm>Else</a>, <a class=small href=ElseIf.htm>ElseIf</a>, <a class=small href=EndIf.htm>EndIf</a>, <a class=small href=Select.htm>Select</a>.
;;param None.
function Then
; IF THEN Example  
; Input the user's name  
name$=Input$("What is your name? ")  
; Doesn't the person's name equal SHANE?  
If name$ = "Shane" Then  
Print "You are recognized, Shane! Welcome!"  
Else  
Print "Sorry, you don't belong here!"  
; End of the condition checking  
End If  
end function
;; Similar to TileImage but ignores transparency.  Use this to tile an entire or portion of the screen with a single repetative  image.
;;param image = file handle variable holding the loaded image
;;param x = x coordinate offset(optional)
;;param y = y coordinate offset (optional)
;;param frame = frame of the image to use (optional)
function TileBlock image [,x,y,frame]
; TileBlock example  
Graphics 800,600,16  
; Load an image to tile (your location might vary)  
gfxBall=LoadImage("C:Program FilesBlitz Basicsamplesall.bmp")  
; Tile the graphic without transparency  
TileBlock gfxBall  
; Wait for ESC to hit  
While Not KeyHit(1)  
Wend  
end function
;; If you want to make a starfield or other easy tiled background, this is  YOUR command. All you have to do is specify the image handle (an image loaded  with the LoadImage or LoadAnimImage command). Optionally, you can  specify a starting x and y location, as well as an optional frame. You can milk  some serious parallax effects with a simple imagestrip with a couple of various  starfields and the TileImage command.
;;param handle= variable holding the image's handle
;;param x=starting x location of the tile; assumed 0
;;param y=starting y location of the tile; assumed 0
;;param frames=the frame of the image to tile; optional with imagestrip
function TileImage handle,[x],[y],[frames]
; CreateImage/TileImage/ImageBuffer example  
; Again, we'll use globals even tho we don't need them here  
; One variable for the graphic we'll create, one for a timer  
Global gfxStarfield, tmrScreen  
; Declare graphic mode  
Graphics 640,480,16  
; Create a blank image that is 320 pixels wide and 32 high with 10 frames of  32x32  
gfxStarfield=CreateImage(32,32,10)  
; loop through each frame of the graphic we just made  
For t = 0 To 9  
; Set the drawing buffer to the graphic frame so we can write on it  
SetBuffer ImageBuffer(gfxStarfield,t)  
; put 50 stars in the frame at random locations  
For y = 1 To 50  
Plot Rnd(32),Rnd(32)  
Next  
Next  
; Double buffer mode for smooth screen drawing  
SetBuffer BackBuffer()  
; Loop until ESC is pressed  
While Not KeyHit(1)  
; Only update the screen every 300 milliseconds. Change 300 for faster or  
; slower screen updates  
If MilliSecs() > tmrScreen+300 Then  
Cls ; clear the screen  
; Tile the screen with a random frame from our new graphic starting at  
; x=0 and y=0 location.  
TileImage gfxStarfield,0,0,Rnd(9)  
Flip ; Flip the screen into view  
tmrScreen=MilliSecs() ; reset the time  
End If  
Wend  
end function
;; Use the TO command to tell your FOR ... NEXT loop which numbers the variable should be assign  to during the loop. If you count down instead of up, you must use a negative STEP value. The values must be integer values. See example.
;; See also: <a class=small href=For.htm>For</a>, <a class=small href=To.htm>To</a>, <a class=small href=Step.htm>Step</a>, <a class=small href=Each.htm>Each</a>, <a class=small href=Next.htm>Next</a>, <a class=small href=Exit.htm>Exit</a>, <a class=small href=While.htm>While</a>, <a class=small href=Repeat.htm>Repeat</a>.
;;param See example
function To
; Print numbers 10 to 1  
For t = 10 to 1 Step -1  
Print t  
Next  
end function
;; TotalVidMem () simply returns the total available memory on a graphics  card, in bytes. Note that to retrieve the currently available number  of bytes, you should use AvailVidMem ().
;;param None.
function TotalVidMem()
Print "Total graphics memory available: " + TotalVidMem () + " bytes."  
; NOTE: To retrieve the *available* graphics memory, use AvailVidMem ()!  
end function
;; This function removes leading and trailing spaces from the specified string.
;;param string$ - any valid string
function Trim$( string$ )
Graphics 640,480,0,2  
a$ = "  Weeeeee  "  
Text 0,0,a$  
Text 0,20,Trim$(a$)  
WaitKey()  
end function
;; TRUE is a keyword to denote a positive result in a conditional statement.  Often times, TRUE is implied and doesn't need to be directly referenced. TRUE  can also be used as a RETURN value from aFUNCTION. See the example.
;; See also: <a class=small href=False.htm>False</a>, <a class=small href=If.htm>If</a>, <a class=small href=Select.htm>Select</a>, <a class=small href=While.htm>While</a>, <a class=small href=Repeat.htm>Repeat</a>.
;;param None.
function True
; TRUE example  
; Assign test a random number of 0 or 1  
test= Rnd(0,1)  
; TRUE is implied; This statement REALLY means: if test=1 is TRUE then proceed  
If test=1 Then  
Print "Test was valued at 1"  
End If  
; Let's set test to be true  
test=True  
; Pointlessly test it  
If test=True Then  
Print "Test is true"  
End If  
end function
;; If you know C programming, a TYPE is basically a STRUCT in Blitz Basic. If  you don't know C, read on!
;; TYPE is your best friend. It is used to create a 'collection' of objects  that share the same parameters and need to be interated through quickly and  easily.
;; Think about SPACE INVADERS. There are many aliens on the screen at one time.  Each of these aliens have a few variables that they all need: x and y coordinates  plus a variable to control which graphic to display (legs out or legs in). Now,  we could make hundreds of variables like invader1x, invader1y, invader2x, invader2y,  etc. to control all the aliens, but that wouldn't make much sense would it?  You could use an array to track them; invader(number,x,y,graphic), and the loop  through them with a FOR ... NEXT  loop but that is a lot of work! The TYPE variable collection was created to  handle just this sort of need.
;; TYPE defines an object collection. Each object in that collection inherits  its own copy of the variables defined by the TYPE's FIELD  command. Each variable of each object in the collection can be read individually  and can be easily iterated through quickly. Use the FIELD command to assign  the variables you want between the TYPE and END  TYPE commands.
;; If it helps, think of a TYPE collection as a database. Each object is a record  of the database, and every variable is a field of the record. Using commands  like BEFORE, AFTER, and FOR  ... EACH, you can move change the pointer of the 'database'  to point to a different record and retrieve/set the variable 'field' values.
;; Not a database guru? Need another example? Okay. Let's say you are setting  up an auditorium for a speech or event and you are putting up hundreds of chairs  for the spectators. The chairs have to be in a certain place on the floor, and  some will need to be raised up a bit higher than others (visiting dignitaries,  the mayor is coming, etc.). So being the computer genius you are, you start  figuring out how you can layout the chairs with the least amount of effort.  You realize that the floor is checkered, so its really a huge grid! This will  make it easy! You just need to number the floor on a piece of graph paper and  put into the grid how high each chair should be, based on where the boss told  you the important people are to sit. So, for each chair, you will have a row  and column on the graph paper (x and y location) and a level to adjust the chair  to (height). Good, we are organized. Now, even though we have it all on paper,  we still have to do the work of placing all the chairs. After you are done,  let's say your boss walks up to you and says "they aren't centered right ..  move'em all over 1 square". Oh no! You have them all perfect, and even though  it is a simple thing to move a chair one square to the right (after all, their  order and height won't change) - you still have to move each and every chair!  Sure would be nice if you could just wave your hand and say "For each chair  in the room, add 1 square to its x location" and have it just magically happen.  Alas, in the real world, get busy - you've got a lot of chairs to move!
;; In Blitz, you could have set up a TYPE called CHAIR, set the TYPE's FIELDS  as X, Y, and HEIGHT. You would then create as many chairs as you need with the NEW command (each time you call NEW, it makes a new chair,  with its OWN X, Y, and HEIGHT variables) and assign them the X, Y, and HEIGHT  values you decide upon. In our example above, when the boss told you to move  the chairs over 1 box, you probably groaned inside. That's a lot of work! In  Blitz, we could use four lines of code to adjust all our CHAIR objects to the  new position (using FOR ... EACH commands).
;; Still lost? Its okay - TYPEs are hard to get a grasp on. Look at the example  and we'll try to show you how types work in a practical environment. I recommend  looking at other people's code too, to help you get a handle on them. Once you  do, you will know why C people are crazy for STRUCTs and why almost all Blitz  programs use them.
;; A cunning trick for debug purposes, or for saving data from types to a file, is to use the Str$ command.  Print Str$(<Type Variable>) will print the values of each field of the type in turn, comma separated, within square brackets, e.g. [15,42,"Fluffy",500].
;; Advanced programmers might like to know that Types are stored in a "doubly linked list".
;; See also: <a class=small href=Field.htm>Field</a>, <a class=small href=New.htm>New</a>, <a class=small href=Null.htm>Null</a>, <a class=small href=First.htm>First</a>, <a class=small href=Last.htm>Last</a>, <a class=small href=Before.htm>Before</a>, <a class=small href=After.htm>After</a>, <a class=small href=Insert.htm>Insert</a>, <a class=small href=Before.htm>Before</a>.
;;param variable = any legal variable name
function Type variable
; Define the CHAIR Type  
Type CHAIR  
Field X  
Field Y  
Field HEIGHT  
End Type  
; Create 100 new chairs using FOR ... NEXT using the collection name of ROOM  
For tempx = 1 to 10  
For tempy = 1 to 10  
room.chair = New Chair  
room\x = tempx  
room\y = tempy  
room\height = Rnd(0,10) ; set a random height 0 to 10  
Next  
Next  
; Move them all over 1 (like the description example)  
For room.chair = Each chair  
room\x = room\x + 1  
Next  
end function
;; Returns the integer IP address of the sender of the last UDP message received.  This value is also returned by RecvUDPMsg().
;; Click <a href=http://www.blitzbasic.co.nz/b3ddocs/command.php?name=UDPMsgIP&ref=comments target=_blank>here</a> to view the latest version of this page online</body>
;; </html>
;;param udp_stream - UDP stream handle
function UDPMsgIP( udp_stream )
end function
;; Returns the port of the sender of the last UDP message received.
;; Click <a href=http://www.blitzbasic.co.nz/b3ddocs/command.php?name=UDPMsgPort&ref=comments target=_blank>here</a> to view the latest version of this page online</body>
;; </html>
;;param udp_stream - UDP stream handle
function UDPMsgPort( udp_stream )
end function
;; Returns the integer IP address of the specified udp_stream. Currently, this  always returns 0.
;; Click <a href=http://www.blitzbasic.co.nz/b3ddocs/command.php?name=UDPStreamIP&ref=comments target=_blank>here</a> to view the latest version of this page online</body>
;; </html>
;;param udp_stream - UDP stream handle
function UDPStreamIP( udp_stream )
end function
;; Returns the port number of the specified UDP stream. This can be useful  if you've created the UDP stream without specifying a port number.
;; Click <a href=http://www.blitzbasic.co.nz/b3ddocs/command.php?name=UDPStreamPort&ref=comments target=_blank>here</a> to view the latest version of this page online</body>
;; </html>
;;param udp_stream - UDP stream handle
function UDPStreamPort( udp_stream )
end function
;; recv_millis allows you to control how long the  RecvUDPMsg() function will wait for a new message. By default, this is set  to 0, meaning RecvUDPMsg() will return immediately  if there is no message to be received.
;; Click <a href=http://www.blitzbasic.co.nz/b3ddocs/command.php?name=UDPTimeouts&ref=comments target=_blank>here</a> to view the latest version of this page online</body>
;; </html>
;;param recv_millis - milliseconds value
function UDPTimeouts recv_millis
end function
;; After you use LockBuffer on a buffer, the only  graphics commands you can use are the read/write pixel commands ReadPixel, WritePixel, ReadPixelFast, and WritePixelFast. You must use this command before  using other graphics commands.
;; The buffer parameter isn't required. If omitted, the default buffer set with SetBuffer will be used.
;; See the other commands for more information.
;; See also: <a class=small href=LockedPitch.htm>LockedPitch</a>, <a class=small href=LockedFormat.htm>LockedFormat</a>, <a class=small href=LockedPixels.htm>LockedPixels</a>, <a class=small href=ReadPixelFast.htm>ReadPixelFast</a>, <a class=small href=WritePixelFast.htm>WritePixelFast</a>, <a class=small href=LockBuffer.htm>LockBuffer</a>.
;;param buffer = any valid screen/image buffer (optional)
function UnlockBuffer buffer
; High Speed Graphics Commands  
Graphics 640,480,16  
; Draw a bunch of stuff on the screen  
For t= 1 To 1000  
Color Rnd(255),Rnd(255),Rnd(255)  
Rect Rnd(640),Rnd(480),Rnd(150),Rnd(150),Rnd(1)  
Next  
Delay 3000  
; Copy the top half of the screen over the bottom half  
; using fast pixels and locked buffers  
For x = 1 To 640  
For y = 1 To 240  
LockBuffer FrontBuffer()  
WritePixelFast x,y+241,ReadPixelFast(x,y)  
UnlockBuffer FrontBuffer()  
Next  
Next  
Delay 3000  
; Draw the left half of the screen over the right half  
; using the slower direct pixel access  
For x = 1 To 320  
For y = 1 To 480  
WritePixel x+320,y,ReadPixel(x,y)  
Next  
Next  
end function
;; This portion of the REPEAT ... UNTIL loop dictates  what condition must be met before the loop stops execution. All commands between  the two commands will be executed endlessly until the UNTIL condition is met.
;; See also: <a class=small href=Repeat.htm>Repeat</a>, <a class=small href=Forever.htm>Forever</a>, <a class=small href=Exit.htm>Exit</a>, <a class=small href=While.htm>While</a>, <a class=small href=For.htm>For</a>.
;;param condition = any valid expression (see example)
function Until condition
; Repeat until user hits ESC key  
Repeat  
print "Press ESC to quit this!"  
Until KeyHit(1)  
end function
;; ;gamma demo - use left/right arrows to
;; ;control display intensity
;; ;
;; ;hold down left-control for a 'red-out' effect!
;; Graphics 640,480,16,1
;; SetBuffer BackBuffer()
;; n=0
;; While Not KeyHit(1)
;; If KeyDown(203) n=n-1
;; If KeyDown(205) n=n+1
;; If KeyDown(29)
;; SetGammaRed(n)
;; Else
;; SetGammaIntensity(n)
;; EndIf
;; Cls
;; SeedRnd 1234
;; For k=1 To 1000
;; Color Rnd(255),Rnd(255),Rnd(255)
;; Rect Rnd(640),Rnd(480),Rnd(64),Rnd(64)
;; Next
;; Text 0,0,"Intensity offset="+n
;; Flip
;; Wend
;; End
;; Function SetGammaRed( n )
;; For k=0 To 255
;; SetGamma k,k,k,k+n,0,0
;; Next
;; UpdateGamma
;; End Function
;; Function SetGammaIntensity( n )
;; For k=0 To 255
;; SetGamma k,k,k,k+n,k+n,k+n
;; Next
;; UpdateGamma
;; End Function
;; Click <a href=http://www.blitzbasic.co.nz/b3ddocs/command.php?name=UpdateGamma&ref=comments target=_blank>here</a> to view the latest version of this page online</body>
;; </html>
;;param UpdateGamma should be used after a series of <a class=small href=../2d_commands/SetGamma.htm>SetGamma</a> commands in order to effect actual changes.
;;param See also: <a class=small href=SetGamma.htm>SetGamma</a>.
function UpdateGamma [calibrate]
end function
;; This command takes the given string and converts it entirely to upper case.
;;param string$ = any valid string or string variable
function Upper$ (string$)
name$="Shane R. Monroe"  
print "Your name all in upper case is: " + upper$(name$)  
end function
;; There are MANY MANY times you want to draw graphics (aliens, ships, etc)  ONLY on a certain area of the screen while leaving the other areas alone. This  is often referred to as 'windowing' or 'portaling' or 'clipping' an area. Usually,  you will perform all your drawing operations first (background, controls, etc),  then section off the restricted area of the screen with VIEWPORT to do drawing  in that area. There are a million uses for this; overhead map radar screens,  Ultima style display windows, onscreen scrollers, etc. This is a bit more complex  than most graphic commands, so be sure you have a good handle on it before trying  to use it. The biggest tip I can give you about this command is: REMEMBER TO  CLEAR THE VIEWPORT WHEN YOU ARE DONE! Do this by setting the viewport to include  the whole screen (i.e. Viewport 0,0,640,480 if your game was in 640x480). Look  carefully at the example. Remember, the second set of numbers isn't the ENDING  location of the port - rather the SIZE of the port starting at the first coordinates.
;;param x = the topmost left corner to start the port x coordinate
;;param y = the topmost left corner to start the port y coordinate
;;param width = how wide the port is (in pixels)
;;param height = how tall the port is (in pixels)
function Viewport x, y, width, height
; ViewPort Example  
; Set Up Graphics Mode  
Graphics 800,600  
; Set up Double Buffering  
SetBuffer BackBuffer()  
; Set viewport starting at 100,100 and make it 200,200 pixels in size  
Viewport 100,100,200,200  
; Infinately draw random rectangles with random colors  
While Not KeyHit(1)  
Cls ; Clear screen in 'blitting' technique  
For t = 1 To 100 ; Do 100 rectangles each time  
Color Rnd(255),Rnd(255),Rnd(255) ; Random color  
Rect Rnd(800),Rnd(600),Rnd(300),Rnd(300),Rnd(0,1) ; Random sized and located  box, some filled  
Next ; repeat that drawing loop  
Flip ; Flip to the back buffer  
Wend  
end function
;; VWait will cause the CPU to wait for the next (or specified number of) vertical blank event on the monitor
;; There are times when you can draw too fast, and your drawing operations happen so fast that you get undesireable effects.  This command forces the CPU to wait until the drawing scan line reaches the bottom of the screen before proceeding. Try the example with and without the VWait command.
;; Note that this command is different to the vertical blank waiting mechanism in Flip because Flip will cause the graphics card (as opposed to the CPU) to wait for the next vertical blank.  The vertical blank can be disabled on some graphics cards, hence it is quite common to use "VWait : Flip False" to ensure consistent updates on all setups.
;; See also: <a class=small href=Flip.htm>Flip</a>, <a class=small href=ScanLine.htm>ScanLine</a>.
;;param [frames] = optional number of frames to wait.  Default is 1 frame.
function VWait [frames]
; Vwait example  
Graphics 800,600,16  
; Wait for ESC to hit  
While Not KeyHit(1)  
; Set a random color  
Color Rnd(255),Rnd(255),Rnd(255)  
; Draw a random line  
Line Rnd(800),Rnd(600),Rnd(800),Rnd(600)  
; Wait For a vertical blank to happen before looping  
VWait  
Wend  
end function
;; This command makes your program halt until a jpystick button is pressed  on the joystick. Used alone, it simply halts and waits for a button press. It  can also be used to assign the pressed button's code value to a variable. See  example.
;; In MOST CASES, you are not going to want to use this command because chances  are likely you are going to want things on the screen still happening while  awaiting the button press. In that situation, you'll use a WHILE ... WEND awaiting  a JoyHit value - refreshing your screen each loop.
;; As with any joystick command, you MUST have a DirectX compatible joystick plugged  in and properly configured within Windows for it to work. See your joystick  documentation for more information.
;;param port = joystick port to check
function WaitJoy ([port])
; WaitJoy() sample  
Print "Press a joystick button to continue."  
button=WaitJoy()  
Print "The joystick button code of the button you pressed was: " + button  
Print "Now press a button to quit."  
WaitJoy()  
End  
end function
;; This command makes your program halt until a key is pressed on the keyboard.  Used alone, it simply halts and waits for a key press. It can also be used to  assign the pressed key's ASCII value to a variable. See example.
;; In MOST CASES, you are not going to want to use this command because chances  are likely you are going to want things on the screen still happening while  awaiting the keypress. In that situation, you'll use a WHILE ... WEND awaiting  a KeyHit value - refreshing your screen each loop.
;;param None.
function WaitKey()
; WaitKey() sample  
Print "Press any key to continue."  
key=WaitKey()  
Print "The ASCII code of the key you pressed was: " + key  
Print "Now press a key to quit."  
WaitKey()  
End  
end function
;; This command makes your program halt until a mouse button is pressed on  the mouse. Used alone, it simply halts and waits for a button press. It can  also be used to assign the pressed button's code value to a variable. See example.
;; In MOST CASES, you are not going to want to use this command because chances  are likely you are going to want things on the screen still happening while  awaiting the button press. In that situation, you'll use a WHILE ... WEND awaiting  a MouseHit value - refreshing your screen each loop.
;;param None.
function WaitMouse()
; WaitMouse() sample  
Print "Press a mouse button to continue."  
button=WaitMouse()  
Print "The mouse button code of the button you pressed was: " + button  
Print "Now press a button to quit."  
WaitMouse()  
End  
end function
;; Use this in conjunction with the CreateTimer  command. This command will halt execution until the timer reaches its value.  This is useful to control the execution speed of your program. Check out the CreateTimer command for more.
;;param timer = any valid timer variable created with the CreateTimer command.
function WaitTimer (timer_variable)
; Create the timer to track speed  
frameTimer=CreateTimer(60)  
; Your main screen draw loop  
While Not KeyHit(1)  
WaitTimer(frameTimer) ; Pause until the timer reaches 60  
Cls  
; Draw your screen stuff  
Flip  
Wend  
end function
;; This is the command that tells program execution to branch to the beginning  of the WHILE/WEND loop at the WHILE command. See the WHILE  command for complete details.
;; See also: <a class=small href=While.htm>While</a>, <a class=small href=Exit.htm>Exit</a>, <a class=small href=Repeat.htm>Repeat</a>, <a class=small href=For.htm>For</a>.
;;param None.
function Wend
; While/Wend Example  
; The loop condition is at the TOP of the loop  
While Not KeyHit(1) ; As long as the user hasn't hit ESC yet ...  
Print "Press Esc to end this mess!" ; Print this  
Wend ; Go back to the start of the WHILE loop  
end function
;; The WHILE/WEND loop is used when you wish to execute a series of commands  multiple times based on whether a condition is true or not. This is similar  to the REPEAT/UNTIL loop, except the condition checking is at the beginning  of the loop, instead of at the end. Usually you'll use WHILE/WEND when you aren't  sure whether or not the looped commands will even need to be executed once,  since you can actually stop the loop before any commands are executed if the  condition check fails. If you need to execute the commands in the loop at least  once before checking a condition, use REPEAT/UNTIL. See example.
;; See also: <a class=small href=Wend.htm>Wend</a>, <a class=small href=Exit.htm>Exit</a>, <a class=small href=Repeat.htm>Repeat</a>, <a class=small href=For.htm>For</a>.
;;param condition = any valid conditional statement
function While condition
; While/Wend Example  
; The loop condition is at the TOP of the loop  
While Not KeyHit(1) ; As long as the user hasn't hit ESC yet ...  
Print "Press Esc to end this mess!" ; Print this  
Wend ; Go back to the start of the WHILE loop  
end function
;; Writes a string to the front buffer (i.e. the screen), but doesn't then  start a new line (unlike Print).
;; See also: Print.
;;param string$ - string variable or value
function Write string$
; Write Example  
; -------------  
Write "Blitz "  
Write "Basic"  
end function
;; Once you've opened a disk file (or stream) for reading, use this command  to write a single byte at a time to the file/stream. Note, a byte is an integer  that can take the values 0..255 and occupies 8 bits of storage. Since characters  are stored as byte values this function can be used to create a text file one  character at a time.
;; Advanced notes:
;; The number that is stored by WriteByte is actually the least significant byte  of an integer so negative numbers and numbers above 255 will still have a value  between 0..255. Unless you understand how 32 bit integers are stored in 2's  compliment notation this will seem strange but it is NOT a bug.
;; Streams can only be used in Blitz Basic v1.52 or greater.
;;param filehandle/stream = a valid variable set with the OpenFile, ReadFile command,  or OpenTCPStream (v1.52+)
;;param mybyte = can be an Integer or a floating point number, but only values between  0 and 255 will be stored faithfully.
function WriteByte (filehandle/stream, mybyte)
; Reading and Writing a file using ReadByte and WriteByte functions  
; Initialise some variables for the example  
Byte1% = 10 ; store 10  
Byte2% = 100 ; store 100  
Byte3% = 255 ; store 255 ( the maximum value that can be stored in a Byte )  
Byte4% = 256 ; try to store 256 this will end up as 0 ( i.e. 256 - 256 = 0 )  
Byte5% = 257 ; try to store 257 this will end up as 1 ( i.e. 257 - 256 = 1 )  
Byte6% = -1 ; try to store -1 this will end up as 255 ( i.e. 256 -1 = 255 )  
Byte7% = -2 ; try to store 256 this will end up as 254 ( i.e. 256 - 2 = 254  )  
Byte8% = Asc("A") ; Store the ASCII value for the Character "A" ( i.e. 65 )  
; Open a file to write to  
fileout = WriteFile("mydata.dat ")  
; Write the information to the file  
WriteByte( fileout, Byte1 )  
WriteByte( fileout, Byte2 )  
WriteByte( fileout, Byte3 )  
WriteByte( fileout, Byte4 )  
WriteByte( fileout, Byte5 )  
WriteByte( fileout, Byte6 )  
WriteByte( fileout, Byte7 )  
WriteByte( fileout, Byte8 )  
; Close the file  
CloseFile( fileout )  
; Open the file to Read  
filein = ReadFile("mydata.dat")  
Read1 = ReadByte( filein )  
Read2 = ReadByte( filein )  
Read3 = ReadByte( filein )  
Read4 = ReadByte( filein )  
Read5 = ReadByte( filein )  
Read6 = ReadByte( filein )  
Read7 = ReadByte( filein )  
Read8 = ReadByte( filein )  
; Close the file once reading is finished  
CloseFile( filein )  
Print "Written - Read"  
Write Byte1 + " - " : Print Read1  
Write Byte2 + " - " : Print Read2  
Write Byte3 + " - " : Print Read3  
Write Byte4 + " - " : Print Read4  
Write Byte5 + " - " : Print Read5  
Write Byte6 + " - " : Print Read6  
Write Byte7 + " - " : Print Read7  
Write Byte8 + " - " : Print Chr$( Read8 )  
WaitKey()  
end function
;; You can write the contents of a memory bank to a file on disk (or stream)  using this command.
;; Note: The file handle must be opened with WriteFile  or OpenTCPStream and subsequently closed with CloseFile or CloseTCPStream  after the writing operations are complete.
;; Return how many bytes successfully written to a stream.
;; Streams can only be used in Blitz Basic v1.52 or greater.
;; See also: <a class=small href=ReadBytes.htm>ReadBytes</a>.
;;param bank = variable containing handle to valid bank
;;param filehandle/stream = a valid variable set with the WriteFile or OpenTCPStream  (v1.52+)
;;param offset = offset in bytes to write the value
;;param count = how many bytes to write from the offset
function WriteBytes bank,filehandle/stream,offset,count
; Read/WriteBytes Commands Example  
; Create a 50 byte memory bank  
bnkTest=CreateBank(500)  
; Let's fill the bank with random data  
For t = 1 To 50  
PokeByte bnkTest,t,Rnd(255)  
Next  
; Open a file to write to  
fileBank=WriteFile("test.bnk")  
; Write the bank to the file  
WriteBytes bnkTest,fileBank,0,50  
; Close it  
CloseFile fileBank  
; Free the bank  
FreeBank bnkTest  
; Make a new one  
bnkTest=CreateBank(500)  
; Open the file to read from  
fileBank=OpenFile("test.bnk")  
; Write the bank to the file  
ReadBytes bnkTest,fileBank,0,50  
; Close it  
CloseFile fileBank  
; Write back the results!  
For t = 1 To 50  
Print PeekByte (bnkTest,t)  
Next  
end function
;; This command opens the designated filename and prepares it to be written  to. Use this to write your own configuration file, save game data, etc. also  useful for saving custom types to files. The filehandle that is returned is  an integer value that the operating system uses to identify which file is to  be written to and must be passed to the functions such as WriteInt(). If the  file could not be opened then the filehandle is Zero.
;;param filename$ = any valid path and filename. The returned value is the filehandle  which is an integer value.
function WriteFile (filename$)
; Reading and writing custom types to files using ReadFile, WriteFile and  CloseFile  
; Initialise some variables for the example  
Type HighScore  
Field Name$  
Field Score%  
Field Level%  
End Type  
Best.HighScore = New HighScore  
BestName = "Mark"  
BestScore = 11657  
BestLevel = 34  
; Open a file to write to  
fileout = WriteFile("mydata.dat")  
; Write the information to the file  
WriteString( fileout, BestName )  
WriteInt( fileout, BestScore )  
WriteByte( fileout, BestLevel )  
; Close the file  
CloseFile( fileout )  
; Open the file to Read  
filein = ReadFile("mydata.dat")  
; Lets read the Greatest score from the file  
Greatest.HighScore = New HighScore  
GreatestName$ = ReadString$( filein )  
GreatestScore = ReadInt( filein )  
GreatestLevel = ReadByte( filein )  
; Close the file once reading is finished  
CloseFile( filein )  
Print "High score record read from - mydata.dat "  
Print  
Write "Name = "  
Print GreatestName  
Write "Score = "  
Print GreatestScore  
Write "Level = "  
Print GreatestLevel  
WaitKey()  
end function
;; Once you've opened a disk file (or stream) for writing, use this command  to write a single floating point number to the file. Note, each value written  uses 4 bytes.
;; Streams can only be used in Blitz Basic v1.52 or greater.
;;param filehandle/stream = a valid variable set with the OpenFile, WriteFile command,  or OpenTCPStream (v1.52+)
;;param myFloat = a floating point variable
function WriteFloat (filehandle/stream, myFloat)
; Reading and writing a file using ReadFloat and WriteFloat functions  
; Initialise some variables for the example  
Num1# = 10.5 ; store 10.5  
Num2# = 365.25 ; store 365.25  
Num3# = 32767.123 ; 32767.123 is the largest positive Short Integer Value in  BlitzBasic )  
Num4# = -32768.123 ; -32768.123 the largest negative Short Integer Value in  BlitzBasic )  
; Open a file to write to  
fileout = WriteFile("mydata.dat")  
; Write the information to the file  
WriteFloat( fileout, Num1 )  
WriteFloat( fileout, Num2 )  
WriteFloat( fileout, Num3 )  
WriteFloat( fileout, Num4 )  
; Close the file  
CloseFile( fileout )  
; Open the file to Read  
filein = ReadFile("mydata.dat")  
Read1# = ReadFloat( filein )  
Read2# = ReadFloat( filein )  
Read3# = ReadFloat( filein )  
Read4# = ReadFloat( filein )  
; Close the file once reading is finished  
CloseFile( filein )  
Print "Floating Point Data Read From File - mydata.dat "  
Print Read1  
Print Read2  
Print Read3  
Print Read4  
WaitKey()  
end function
;; Once you've opened a disk file (or stream) for writing, use this command  to write a single integer value to the file. Note, each value written uses 4  bytes and is written least significant byte first. The range of the value saved  is -2147483648 to 2147483647
;; Streams can only be used in Blitz Basic v1.52 or greater.
;;param filehandle/stream = a valid variable set with the OpenFile, WriteFile command,  or OpenTCPStream (v1.52+)
;;param myinteger = an integer variable (a floating point number can be used but this  will be converted to an integer before saving so only the integer part will  be saved)
function WriteInt (filehandle/stream, myinteger)
; Reading and writing a file using ReadInt and WriteInt functions  
; Initialise some variables for the example  
Int1% = 10 ; store 10  
Int2% = 365 ; store 365  
Int3% = 2147483647; store 2147483647 the largest positive Integer Value in BlitzBasic  )  
Int4% = - 2147483648 ; store -2147483648 the largest negative Integer Value  in BlitzBasic )  
; Open a file to write to  
fileout = WriteFile("mydata.dat")  
; Write the information to the file  
WriteInt( fileout, Int1 )  
WriteInt( fileout, Int2 )  
WriteInt( fileout, Int3 )  
WriteInt( fileout, Int4 )  
; Close the file  
CloseFile( fileout )  
; Open the file to Read  
filein = ReadFile("mydata.dat")  
Read1 = ReadInt( filein )  
Read2 = ReadInt( filein )  
Read3 = ReadInt( filein )  
Read4 = ReadInt( filein )  
; Close the file once reading is finished  
CloseFile( filein )  
Print "Integer Data Read From File - mydata.dat "  
Print Read1  
Print Read2  
Print Read3  
Print Read4  
WaitKey()  
end function
;; Once you've opened a disk file (or stream) for writing, use this command  to right a whole line of text to the file. Each line of text is automatically  terminated with an "end-of-line" mark, consisting of a Carriage Return character  followed by a LineFeed character. (i.e. 0Dh, 0Ah ) This function can be used  to make plain text files.
;; Streams can only be used in Blitz Basic v1.52 or greater.
;;param filehandle/stream = a valid variable set with the OpenFile, WriteFile command,  or OpenTCPStream (v1.52+). The value returned is a text string.
;;param string$ = valid string value.
function WriteLine$ (filehandle/stream, string$)
; Reading and writing a file using ReadLine$ and WriteLine functions  
; Initialise some variables for the example  
String1$ = "Line 1 is short"  
String2$ = "Line 2 is a longer line but they can be much longer"  
String3$ = "Line 3 is made up "  
String4$ = "of two parts joined together."  
; Open a file to write to  
fileout = WriteFile("mydata.txt")  
; Write the information to the file  
WriteLine( fileout, String1 )  
WriteLine( fileout, String2 )  
WriteLine( fileout, String3 + String4)  
WriteLine( fileout, "Just to show you don't have to use variables" )  
; Close the file  
CloseFile( fileout )  
; Open the file to Read  
filein = ReadFile("mydata.txt")  
Read1$ = ReadLine( filein )  
Read2$ = ReadLine$( filein )  
Read3$ = ReadLine$( filein )  
Read4$ = ReadLine$( filein )  
; Close the file once reading is finished  
CloseFile( filein )  
Print "Lines of text read from file - mydata.txt "  
Print  
Print Read1  
Print Read2  
Print Read3  
Print Read4  
WaitKey()  
end function
;; Writes a color value to either the current buffer or the specified buffer.
;; You can use this command on a locked buffer for a slight speed-up.
;; See also: <a class=small href=Plot.htm>Plot</a>, <a class=small href=WritePixelFast.htm>WritePixelFast</a>, <a class=small href=LockBuffer.htm>LockBuffer</a>.
;;param x - y-coordinate of pixel
;;param y - y-coordinate of pixel
;;param argb - argb colour value of pixel (alpha, red, green, blue)
;;param buffer (optional) - name of buffer to write colour value to, e.g. BackBuffer()
function WritePixel x,y,argb,[buffer]
; ReadPixel/WritePixel Example  
; ----------------------------  
Graphics 640,480,16  
Print "Press a key to read color values (this may take a few seconds)"  
WaitKey()  
; Load and draw an image on to the screen - can be anything  
pic=LoadImage("media/blitz_pic.bmp")  
DrawImage pic,0,0  
; Initialise an array big enough to fit all the color information of the screen  
Dim pix(GraphicsWidth(),GraphicsHeight())  
; Use ReadPixel to get all the color information of the screen  
For y=0 To GraphicsHeight()  
For x=0 To GraphicsWidth()  
pix(x,y)=ReadPixel(x,y)  
Next  
Next  
Cls  
Locate 0,0  
Print "Press a key to write pixels (this may takes a few seconds)"  
Print "Once this has finished, you can then press a key to end the program"  
WaitKey()  
; Use WritePixel to redraw the screen using the color information we got earlier  
For y=0 To GraphicsHeight()  
For x=0 To GraphicsWidth()  
WritePixel x,y,pix(x,GraphicsHeight()-y) ; get y array value in backwards order,  to flip screen  
Next  
Next  
WaitKey()  
end function
;; Writes a colour value to either the current buffer or the specified buffer.
;; IMPORTANT:
;; You *must* use this command on a locked buffer, otherwise the command will  fail. See LockBuffer.
;; Also, you must make sure that the coordinates that you are writing to are  valid, otherwise you will end up overwriting other areas of memory.
;; WARNING:
;; By not following the above advice, you may cause your computer to crash.
;; See also: Plot, WritePixel.
;;param x - y-coordinate of pixel
;;param y - y-coordinate of pixel
;;param argb - argb colour value of pixel (alpha, red, green, blue)
;;param buffer (optional) - name of buffer to write colour value to, e.g. BackBuffer()
function WritePixelFast x,y,rgb,[buffer]
; ReadPixelFast/WritePixeFast Example  
; -----------------------------------  
Graphics 640,480,16  
Print "Press a key to read color values"  
WaitKey()  
; Load and draw an image on to the screen - can be anything  
pic=LoadImage("media/blitz_pic.bmp")  
DrawImage pic,0,0  
; Initialise an array big enough to fit all the color information of the screen  
Dim pix(GraphicsWidth(),GraphicsHeight())  
; Lock buffer before using ReadPixelFast  
LockBuffer  
; Use ReadPixel to get all the color information of the screen  
For y=0 To GraphicsHeight()  
For x=0 To GraphicsWidth()  
pix(x,y)=ReadPixelFast(x,y)  
Next  
Next  
; Lock buffer after using ReadPixelFast  
UnlockBuffer  
Cls  
Locate 0,0  
Print "Press a key to write pixels"  
Print "Once this has finished, you can then press a key to end the program"  
WaitKey()  
; Lock buffer before using WritePixelFast  
LockBuffer  
; Use WritePixel to redraw the screen using the color information we got earlier  
For y=0 To GraphicsHeight()  
For x=0 To GraphicsWidth()  
WritePixelFast x,y,pix(x,GraphicsHeight()-y) ; get y array value in backwards  order, to flip screen  
Next  
Next  
; Unlock buffer after using WritePixelFast  
UnlockBuffer  
WaitKey()  
end function
;; Once you've opened a disk file (or stream) for writing, use this command  to write a single short integer (16 bit) value to the file. Note, each value  written uses 2 bytes and is written least significant byte first. The range  of the value saved is 0-65535
;; Streams can only be used in Blitz Basic v1.52 or greater.
;;param filehandle/stream = a valid variable set with the OpenFile, WriteFile command,  or OpenTCPStream (v1.52+)
;;param myinteger = an integer variable (a floating point number can be used but this  will be converted to an integer before saving so only the integer part will  be saved)
function WriteShort (filehandle/stream, myinteger)
; Reading and writing a file using ReadShort and WriteShort functions  
; Initialise some variables for the example  
Int1% = 10 ; store 10  
Int2% = 365 ; store 365  
Int3% = 32767 ; 32767 is the largest positive Short Integer Value in BlitzBasic  )  
Int4% = -32768 ; -32768 the largest negative Short Integer Value in BlitzBasic  )  
; Open a file to write to  
fileout = WriteFile("mydata.dat")  
; Write the information to the file  
WriteShort( fileout, Int1 )  
WriteShort( fileout, Int2 )  
WriteShort( fileout, Int3 )  
WriteShort( fileout, Int4 )  
; Close the file  
CloseFile( fileout )  
; Open the file to Read  
filein = ReadFile("mydata.dat")  
Read1 = ReadShort( filein )  
Read2 = ReadShort( filein )  
Read3 = ReadShort( filein )  
Read4 = ReadShort( filein )  
; Close the file once reading is finished  
CloseFile( filein )  
Print "Short Integer Data Read From File - mydata.dat "  
Print Read1  
Print Read2  
Print Read3  
Print Read4  
WaitKey()  
end function
;; Once you've opened a disk file (or stream) for writing, use this command  to write a string variable to the file.
;; Each string is stored in the file as a 4 byte (32bit) integer followed by the  characters that form the string. The integer contains the number of characters  in the string, i.e. its length. Note, that Carriage Return, Line Feed and Null  characters are NOT use to indicate the end of the string. A file of strings  cannot be read like a text file, since it contains string variables and not  text. A null string, i.e. a string of zero length ("") is stored as 4 bytes,  an integer count with a value = zero, followed by no Characters. Note strings  are not limited to 255 characters as in some languages. Reading beyond the end  of file does not result in an error, but each value read will be a zero length  string.
;; Streams can only be used in Blitz Basic v1.52 or greater.
;;param filehandle/stream = a valid variable set with the OpenFile, WriteFile command,  or OpenTCPStream (v1.52+)
;;param mystring$ = any string variable or text contained between quotes.
function WriteString (filehandle/stream, mystring$)
; Reading and writing a file using ReadString$ and WriteString functions  
; Initialise some variables for the example  
String1$ = "A short string"  
String2$ = "A longer string since these are variables lengths"  
String3$ = "This is string3 "  
String4$ = "joined to string4"  
; Open a file to write to  
fileout = WriteFile("mydata.dat")  
; Write the information to the file  
WriteString( fileout, String1 )  
WriteString( fileout, String2 )  
WriteString( fileout, String3 + String4)  
WriteString( fileout, "Just to show you don't have to use variables" )  
; Close the file  
CloseFile( fileout )  
; Open the file to Read  
filein = ReadFile("mydata.dat")  
Read1$ = ReadString$( filein )  
Read2$ = ReadString$( filein )  
Read3$ = ReadString$( filein )  
Read4$ = ReadString$( filein )  
; Close the file once reading is finished  
CloseFile( filein )  
Print "String Variables Read From File - mydata.dat "  
Print  
Print Read1  
Print Read2  
Print Read3  
Print Read4  
WaitKey()  
end function
;; Often used for lightweight (meaning worthless ;) encryption, this will take  two values and perform an exclusive OR with each bit following the basic rules  of XOR. The result can be XORed with one of the original numbers to reveal the  other number. See the example for more.
;; See also: <a class=small href=And.htm>And</a>, <a class=small href=Or.htm>Or</a>, <a class=small href=Not.htm>Not</a>.
;;param None.
function Xor
num=%11110000111100001111000011110000 ; Define a bit pattern which is easy  to recognize  
bitmask=Rnd(-2147483648,2147483647) ; Define a RANDOM Xor 32bit wide bitmask  
; This line prints the binary and decimal numbers before the Xor  
Print "Binary number is: "+Bin$(num)+" ("+num+")"  
; This line prints the binary and decimal numbers of the Xor bitmask  
Print "Xor bitmask is: "+Bin$(bitmask)+" ("+bitmask+")"  
Print "------------------------------------------------------------------"  
; This line Xor's the number with the bitmask  
xres=num Xor bitmask  
; This line prints the binary and decimal numbers after the Xor  
Print "Xor result is: "+Bin$(xres)+" ("+xres+")"  
Print "------------------------------------------------------------------"  
; This line Xor's the Xor result with the bitmask again  
xres=xres Xor bitmask  
; This line prints the binary and decimal numbers after the second Xor. NOTE:  This number is identical to the original number  
Print "Result Xor'ed again: "+Bin$(xres)+" ("+xres+")"  
WaitMouse ; Wait for the mouse before ending.  
end function
;; Creates an animation sequence for an entity. This must be done before any  animation keys set by SetAnimKey can be used in  an actual animation however this is optional. You may use it to "bake" the frames you have added previously using SetAnimKey.
;; Returns the animation sequence number added.
;;param entity - entity handle
;;param length -
function AddAnimSeq ( entity,length)
;Create 3d animation example  
;Set up a simple nice looking level  
Graphics3D 640,480  
camera=CreateCamera()  
PositionEntity camera,0,12,-12  
RotateEntity camera,35,0,0  
light=CreateLight(2)  
PositionEntity light,1000,1000,-1000  
ground=CreatePlane(2)  
EntityAlpha ground,0.5  
EntityColor ground,0,0,255  
mirror=CreateMirror()  
;Lets make a bouncing ball that squashes on impact with the floor.  
ball=CreateSphere(16)  
EntityShininess ball,1  
EntityColor ball,255,0,0  
; Lets animate him and "record" the 3D animation for later playback  
bloat#=0 : flatten#=0 : ypos#=10  
For frame=1 To 10  
;Drop the ball from height 10 to 2  
ypos = ypos - spd#  
spd#=spd#+.2  
PositionEntity ball,0,ypos,0  
ScaleEntity ball,1+bloat,1+flatten,1+bloat  
;If the ball is low enough make it look increasingly squashed  
If frame>8  
bloat=bloat+1.5  
flatten=flatten-.25  
Else  
flatten=flatten+.05  
EndIf  
;Record the frame!  
SetAnimKey ball,frame  
Next  
;Now we need to add the frames we've just made to the sequence of "film"!  
seq = AddAnimSeq(ball,frame-1) ; total number of frames  
;Play it back ping-pong!  
Animate ball,2,0.15  
While Not KeyHit(1)  
UpdateWorld  
RenderWorld  
Flip  
Wend  
End  
end function
;; Adds the source mesh to the destination mesh.
;; AddMesh works best with  meshes that have previously only had mesh commands used with them.
;; So if you want to manipulate a mesh before adding it to another mesh, make  sure you use ScaleMesh, PositionMesh, PaintMesh etc rather than ScaleEntity,  PositionEntity, EntityTexture etc before using AddMesh.
;; However, something to be aware of when using commands such as RotateMesh  is that all mesh commands work from a global origin of 0,0,0. Therefore it is  generally a good idea to scale and rotate a mesh before positioning it, otherwise  your mesh could end up in unexpected positions. Also, when using AddMesh, the  origin of the new all-in-one mesh will be set at 0,0,0.
;; After using AddMesh, the original source_mesh will still exist, therefore  use FreeEntity to delete it if you wish to do so.
;;param source_mesh - source mesh handle
;;param dest_mesh - destination mesh handle
function AddMesh source_mesh,dest_mesh
; AddMesh Example  
; ---------------  
Graphics3D 640,480  
SetBuffer BackBuffer()  
camera=CreateCamera()  
PositionEntity camera,0,0,-10  
light=CreateLight()  
RotateEntity light,90,0,0  
; Create tree mesh (upper half)  
tree=CreateCone()  
green_br=CreateBrush(0,255,0)  
PaintMesh tree,green_br  
ScaleMesh tree,2,2,2  
PositionMesh tree,0,1.5,0  
; Create trunk mesh  
trunk=CreateCylinder()  
brown_br=CreateBrush(128,64,0)  
PaintMesh trunk,brown_br  
PositionMesh trunk,0,-1.5,0  
; Add trunk mesh to tree mesh, to form one whole tree  
AddMesh trunk,tree  
; Free trunk mesh - we don't need it anymore  
FreeEntity trunk  
While Not KeyDown( 1 )  
TurnEntity tree,1,1,1  
RenderWorld  
Flip  
Wend  
End  
end function
;; Adds a triangle to a surface and returns the triangle's index number, starting  from 0.
;; The v0, v1 and v2 parameters are the index numbers of the vertices  created using AddVertex.
;; Depending on how the vertices are arranged, then the triangle will only be  visible from a certain side. Imagine that a triangle's vertex points are like  dot-to-dot pattern, each numbered v0, v1, v2. If these dots, starting from v0,  through to V2, form a clockwise pattern relative to the viewer, then the triangle  will be visible. If these dots form an anti-clockwise pattern relative to the  viewer, then the triangle will not be visible.
;; The reason for having one-sided triangles is that it reduces the amount of  triangles that need to be rendered when one side faces the side of an object  which won't be seen (such as the inside of a snooker ball). However, if you  wish for a triangle to be two-sided, then you can either create two triangles,  using the same set of vertex numbers for both but assigning them in opposite  orders, or you can use CopyEntity and FlipMesh together.
;;param surface - surface handle
;;param v0 - index number of first vertex of triangle
;;param v1 - index number of second vertex of triangle
;;param v2 - index number of third vertex of triangle
function AddTriangle ( surface,v0,v1,v2 )
Graphics3D 640,480  
SetBuffer BackBuffer()  
mesh = CreateMesh()  
surf = CreateSurface(mesh)  
v0 = AddVertex (surf, -5,-5,0,  0  ,0)  
v1 = AddVertex (surf,  5,-5,0,  1  ,0)  
v2 = AddVertex (surf,  0, 5,0,  0.5,1)  
tri = AddTriangle (surf,v0,v2,v1)  
cam = CreateCamera()  
MoveEntity cam, 0,0,-7  
RenderWorld  
Flip  
WaitKey  
End  
end function
;; Adds a vertex to the specified surface and returns the vertices' index number,  starting from 0.
;; x,y,z are the geometric coordinates of the vertex, and u,v,w are texture mapping coordinates.
;; A vertex is a point in 3D space which is used to connect edges of a triangle together. Without any vertices, you can't have any triangles. At least three  vertices are needed to create one triangle; one for each corner.
;; The optional u, v and w parameters allow you to specify texture coordinates for a vertex, which will determine how any triangle created using those vertices will be texture mapped. The u, v and w parameters specified will take effect on both texture coordinate sets (0 and 1). This works on the following basis:
;; The top left of an image has the uv coordinates 0,0.
;; The top right has coordinates 1,0
;; The bottom right is 1,1.
;; The bottom left 0,1.
;; Thus, uv coordinates for a vertex correspond to a point in the image. For example, coordinates 0.9,0.1 would be near the upper right corner of the image.
;; So now imagine you have a normal equilateral triangle. By assigning the bottom left vertex a uv coordinate of 0,0, the bottom right a coordinate of 1,0 and the top centre 0.5,1, this will texture map the triangle with an image that fits it.
;; When adding a vertex its default color is 255,255,255,255.
;;param surface - surface handle
;;param x# - x coordinate of vertex
;;param y# - y coordinate of vertex
;;param z# - z coordinate of vertex
;;param u# (optional) - u texture coordinate of vertex
;;param v# (optional) - v texture coordinate of vertex
;;param w# (optional) - w texture coordinate of vertex - not used, included for future expansion
function AddVertex ( surface,x#,y#,z#[,u#][,v#][,w#] )
Graphics3D 640,480  
SetBuffer BackBuffer()  
mesh = CreateMesh()  
surf = CreateSurface(mesh)  
v0 = AddVertex (surf, -5,-5,0,  0  ,0)  
v1 = AddVertex (surf,  5,-5,0,  1  ,0)  
v2 = AddVertex (surf,  0, 5,0,  0.5,1)  
tri = AddTriangle (surf,v0,v2,v1)  
cam = CreateCamera()  
MoveEntity cam, 0,0,-7  
RenderWorld  
Flip  
WaitKey  
End  
end function
;; Aligns an entity axis to a vector.
;; Click <a href=http://www.blitzbasic.co.nz/b3ddocs/command.php?name=AlignToVector&ref=comments target=_blank>here</a> to view the latest version of this page online</body>
;; </html>
;;param entity - entity handle
;;param vector_x# - vector x
;;param vector_y# - vector y
;;param vector_z# - vector z
;;param axis - axis of entity that will be aligned to vector
;;param 1: x-axis
;;param 2: y-axis
;;param 3: z-axis
;;param rate# (optional) - rate at which entity is aligned from current  orientation to vector orientation. Should be in the range 0 to 1, 0 for smooth  transition and 1 for 'snap' transition. Defaults to 1.
function AlignToVector entity,vector_x#,vector_y#,vector_z#,axis[,rate#]
end function
;; Sets the ambient lighting colour.
;; Ambient light is a light source that affects all points on a 3D object equally.  So with ambient light only, all 3D objects will appear flat, as there will be  no shading.
;; Ambient light is useful for providing a certain level of light, before adding  other lights to provide a realistic lighting effect.
;; An ambient light level of 0,0,0 will result in no ambient light being displayed.
;; See also: <a class=small href=CreateLight.htm>CreateLight</a>.
;;param red# - red ambient light value
;;param green# - green ambient light value
;;param blue# - blue ambient light value
;;param The green, red and blue values should be  in the range 0-255. The default ambient light colour is 127,127,127.
function AmbientLight red#,green#,blue#
; AmbientLight Example  
; --------------------  
Graphics3D 640,480  
SetBuffer BackBuffer()  
camera=CreateCamera()  
sphere=CreateSphere( 32 )  
PositionEntity sphere,-2,0,5  
cone=CreateCone( 32 )  
PositionEntity cone,2,0,5  
; Set initial ambient light colour values  
red#=127  
green#=127  
blue#=127  
While Not KeyDown( 1 )  
; Change red, green, blue values depending on key pressed  
If KeyDown( 2 )=True And red#>0 Then red#=red#-1  
If KeyDown( 3 )=True And red#<255 Then red#=red#+1  
If KeyDown( 4 )=True And green#>0 Then green#=green#-1  
If KeyDown( 5 )=True And green#<255 Then green#=green#+1  
If KeyDown( 6 )=True And blue#>0 Then blue#=blue#-1  
If KeyDown( 7 )=True And blue#<255 Then blue#=blue#+1  
; Set ambient light color using red, green, blue values  
AmbientLight red#,green#,blue#  
RenderWorld  
Text 0,0,"Press keys 1-6 to change AmbientLight red#,green#,blue# values  
Text 0,20,"Ambient Red: "+red#  
Text 0,40,"Ambient Green: "+green#  
Text 0,60,"Ambient Blue: "+blue#  
Flip  
Wend  
End  
end function
;; Animates an entity.
;; More info about the optional parameters:
;; speed# - a negative speed will play the animation backwards.
;; sequence - Initially, an entity loaded with LoadAnimMesh  will have a single animation sequence. More sequences can be added using either LoadAnimSeq or AddAnimSeq.  Animation sequences are numbered 0,1,2...etc.
;; transition# - A value of 0 will cause an instant 'leap' to the first frame,  while values greater than 0 will cause a smooth transition.
;; Click <a href=http://www.blitzbasic.co.nz/b3ddocs/command.php?name=Animate&ref=comments target=_blank>here</a> to view the latest version of this page online</body>
;; </html>
;;param entity - entity handle
;;param mode (optional) - mode of animation.
;;param 0: stop animation
;;param 1: loop animation (default)
;;param 2: ping-pong animation
;;param 3: one-shot animation
;;param speed# (optional) - speed of animation. Defaults to 1.
;;param sequence (optional) - specifies which sequence of animation frames to play.  Defaults to 0.
;;param transition# (optional) - used to tween between an entities current position  rotation and the first frame of animation. Defaults to 0.
function Animate entity[,mode][,speed#][,sequence][,transition#]
end function
;; Animates an md2 entity.
;; The md2 will actually move from one frame to the  next when UpdateWorld is called.
;;param md2 - md2 handle
;;param mode (optional) - mode of animation
;;param 0: stop animation
;;param 1: loop animation (default)
;;param 2: ping-pong animation
;;param 3: one-shot animation
;;param speed# (optional) - speed of animation. Defaults to  1.
;;param first_frame (optional) - first frame of animation. Defaults to 1.
;;param last_frame# (optional) - last frame of animation. Defaults to last frame of  all md2 animations.
;;param transition# (optional) - smoothness of transition between last frame shown of  previous animation and first frame of next animation. Defaults to 0.
function AnimateMD2 md2[,mode][,speed#][,first_frame][,last_frame][,transition#]
; AnimateMD2 Example  
; ------------------  
Graphics3D 640,480  
SetBuffer BackBuffer()  
camera=CreateCamera()  
light=CreateLight()  
RotateEntity light,90,0,0  
; Load md2  
gargoyle=LoadMD2( "media/gargoyle/gargoyle.md2" )  
; Load md2 texture  
garg_tex=LoadTexture( "media/gargoyle/gargoyle.bmp" )  
; Apply md2 texture to md2  
EntityTexture gargoyle,garg_tex  
; Animate md2  
AnimateMD2 gargoyle,1,0.1,32,46  
PositionEntity gargoyle,0,-45,100  
RotateEntity gargoyle,0,180,0  
While Not KeyDown( 1 )  
UpdateWorld  
RenderWorld  
Flip  
Wend  
End  
end function
;; Returns true if the specified entity is currently animating.
;; Click <a href=http://www.blitzbasic.co.nz/b3ddocs/command.php?name=Animating&ref=comments target=_blank>here</a> to view the latest version of this page online</body>
;; </html>
;;param entity - entity handle
function Animating ( entity )
end function
;; Returns the length of the specified entity's current animation sequence.
;; Click <a href=http://www.blitzbasic.co.nz/b3ddocs/command.php?name=AnimLength&ref=comments target=_blank>here</a> to view the latest version of this page online</body>
;; </html>
;;param entity - entity handle
function AnimLength ( entity )
end function
;; Returns the specified entity's current animation sequence.
;; Click <a href=http://www.blitzbasic.co.nz/b3ddocs/command.php?name=AnimSeq&ref=comments target=_blank>here</a> to view the latest version of this page online</body>
;; </html>
;;param entity - entity handle
function AnimSeq ( entity )
end function
;; Returns the current animation time of an entity.
;; Click <a href=http://www.blitzbasic.co.nz/b3ddocs/command.php?name=AnimTime&ref=comments target=_blank>here</a> to view the latest version of this page online</body>
;; </html>
;;param entity - entity handle
function AnimTime# ( entity )
end function
;; Enables or disables fullscreen antialiasing.
;; Fullscreen antialiasing is a technique used to smooth out the entire screen,  so that jagged lines are made less noticeable.
;; Some 3D cards have built-in support for fullscreen antialiasing, which should  allow you to enable the effect without much slowdown. However, for cards without  built-in support for fullscreen antialiasing, enabling the effect may cause  severe slowdown.
;;param enable - True to enable fullscreen antialiasing, False to disable.
;;param The default AntiAlias mode is False.
function AntiAlias
; AntiAlias Example  
; -----------------  
Graphics3D 640,480  
SetBuffer BackBuffer()  
camera=CreateCamera()  
light=CreateLight()  
RotateEntity light,90,0,0  
sphere=CreateSphere()  
PositionEntity sphere,0,0,2  
While Not KeyDown( 1 )  
; Toggle antialias enable value between true and false when spacebar is pressed  
If KeyHit( 57 )=True Then enable=1-enable  
; Enable/disable antialiasing  
AntiAlias enable  
RenderWorld  
Text 0,0,"Press spacebar to toggle between AntiAlias True/False"  
If enable=True Then Text 0,20,"AntiAlias True" Else Text 0,20,"AntiAlias False"  
Flip  
Wend  
End  
end function
;; Sets the alpha level of a brush.
;; The alpha# value should be in the range  0-1. The default brush alpha setting is 1.
;; The alpha level is how transparent an entity is. A value of 1 will mean the  entity is non-transparent, i.e. opaque. A value of 0 will mean the entity is  completely transparent, i.e. invisible. Values between 0 and 1 will cause varying  amount of transparency accordingly, useful for imitating the look of objects  such as glass and ice.
;; An BrushAlpha value of 0 is especially useful as Blitz3D will not render  entities with such a value, but will still involve the entities in collision  tests. This is unlike HideEntity, which doesn't  involve entities in collisions.
;; Click <a href=http://www.blitzbasic.co.nz/b3ddocs/command.php?name=BrushAlpha&ref=comments target=_blank>here</a> to view the latest version of this page online</body>
;; </html>
;;param brush - brush handle
;;param alpha# - alpha level of brush
function BrushAlpha brush,alpha#
end function
;; Sets the blending mode for a brush.
;; Click <a href=http://www.blitzbasic.co.nz/b3ddocs/command.php?name=BrushBlend&ref=comments target=_blank>here</a> to view the latest version of this page online</body>
;; </html>
;;param brush - brush handle
;;param blend -
;;param 1: alpha (default)
;;param 2: multiply
;;param 3: add
function BrushBlend brush,blend
end function
;; Sets the colour of a brush.
;; The green, red and blue values should be in  the range 0-255. The default brush color is  255,255,255.
;; Please note that if EntityFX or  BrushFX flag 2 is being used, brush colour will have no effect and vertex  colours will be used instead.
;;param brush - brush handle
;;param red# - red value of brush
;;param green# - green value of brush
;;param blue# - blue value of brush
function BrushColor brush,red#,green#,blue#
; BrushColor Example  
; ------------------  
Graphics3D 640,480  
SetBuffer BackBuffer()  
camera=CreateCamera()  
light=CreateLight()  
RotateEntity light,90,0,0  
cube=CreateCube()  
PositionEntity cube,0,0,5  
; Create brush  
brush=CreateBrush()  
; Set brush color  
BrushColor brush,0,0,255  
; Paint mesh with brush  
PaintMesh cube,brush  
While Not KeyDown( 1 )  
pitch#=0  
yaw#=0  
roll#=0  
If KeyDown( 208 )=True Then pitch#=-1  
If KeyDown( 200 )=True Then pitch#=1  
If KeyDown( 203 )=True Then yaw#=-1  
If KeyDown( 205 )=True Then yaw#=1  
If KeyDown( 45 )=True Then roll#=-1  
If KeyDown( 44 )=True Then roll#=1  
TurnEntity cube,pitch#,yaw#,roll#  
RenderWorld  
Flip  
Wend  
End  
end function
;; Sets miscellaneous effects for a brush.
;; Flags can be added to combine  two or more effects. For example, specifying a flag of 3 (1+2) will result in  a full-bright and vertex-coloured brush.
;; Click <a href=http://www.blitzbasic.co.nz/b3ddocs/command.php?name=BrushFX&ref=comments target=_blank>here</a> to view the latest version of this page online</body>
;; </html>
;;param brush - brush handle
;;param fx -
;;param 0: nothing (default)
;;param 1: full-bright
;;param 2: use vertex colors instead of brush color
;;param 4: flatshaded
;;param 8: disable fog
;;param 16: disable backface culling
function BrushFX brush,fx
end function
;; Sets the specular shininess of a brush.
;; The shininess# value should be  in the range 0-1. The default shininess setting is 0.
;; Shininess is how much brighter certain areas of an object will appear to  be when a light is shone directly at them.
;; Setting a shininess value of 1 for a medium to high poly sphere, combined  with the creation of a light shining in the direction of it, will give it the  appearance of a shiny snooker ball.
;; Click <a href=http://www.blitzbasic.co.nz/b3ddocs/command.php?name=BrushShininess&ref=comments target=_blank>here</a> to view the latest version of this page online</body>
;; </html>
;;param brush - brush handle
;;param shininess# - shininess of brush
function BrushShininess brush,shininess#
end function
;; Assigns a texture to a brush.
;; The optional frame parameter specifies which  animation frame, if any exist, should be assigned to the brush.
;; The optional index parameter specifies texture layer that the texture should  be assigned to. Brushes have up to four texture layers, 0-3 inclusive.
;;param brush - brush handle
;;param texture - texture handle
;;param frame (optional) - texture frame. Defaults to 0.
;;param index (optional) - texture index. Defaults to 0.
function BrushTexture brush,texture[,frame][,index]
; BrushTexture Example  
; --------------------  
Graphics3D 640,480  
SetBuffer BackBuffer()  
camera=CreateCamera()  
light=CreateLight()  
RotateEntity light,90,0,0  
cube=CreateCube()  
PositionEntity cube,0,0,5  
; Load texture  
tex=LoadTexture( "media/b3dlogo.jpg" )  
; Create brush  
brush=CreateBrush()  
; Apply texture to brush  
BrushTexture brush,tex  
; Paint mesh with brush  
PaintMesh cube,brush  
While Not KeyDown( 1 )  
pitch#=0  
yaw#=0  
roll#=0  
If KeyDown( 208 )=True Then pitch#=-1  
If KeyDown( 200 )=True Then pitch#=1  
If KeyDown( 203 )=True Then yaw#=-1  
If KeyDown( 205 )=True Then yaw#=1  
If KeyDown( 45 )=True Then roll#=-1  
If KeyDown( 44 )=True Then roll#=1  
TurnEntity cube,pitch#,yaw#,roll#  
RenderWorld  
Flip  
Wend  
End  
end function
;; Sets the ambient lighting colour for a BSP model.
;; Note that BSP models  do not use the normal AmbientLight setting. This  can also be used to increase the brightness of a BSP model, but the effect is  not as 'nice' as using the gamma_adjust parameter of LoadBSP.
;; See also: <a class=small href=LoadBSP.htm>LoadBSP</a>, <a class=small href=BSPLighting.htm>BSPLighting</a>.
;;param bsp - BSP handle
;;param red# - red BSP ambient light value
;;param green# - green BSP ambient light value
;;param blue# - blue BSP ambient light value
;;param The red, green and blue values should  be in the range 0-255. The default BSP ambient light colour is 0,0,0.
function BSPAmbientLight bsp, red#, green#, blue#
Graphics3D 640,480  
campiv = CreatePivot()  
cam = CreateCamera(campiv)  
CameraRange cam, 0.1,2000  
level=LoadBSP( "nyk3dm1\nyk3dm1.bsp",.8 ) ; load a 'legal' quake3 bsp map  
BSPAmbientLight level, 0,255,0 ; make the ambient light green  
;BSPLighting level, False ; uncomment this line to turn lightmap off  
While Not KeyDown(1) ; if ESCAPE pressed then exit  
RenderWorld:Flip  
mys = MouseYSpeed()  
If Abs(EntityPitch(cam)+mys) < 75 ; restrict pitch of camera  
TurnEntity cam, mys,0,0  
EndIf  
TurnEntity campiv,0,-MouseXSpeed(),0  
If MouseDown(1) Then ; press mousebutton to move forward  
TFormVector 0,0,3,cam,campiv  
MoveEntity campiv,TFormedX(),TFormedY(),TFormedZ()  
EndIf  
MoveMouse 320,240 ; centre mouse cursor  
Wend  
End  
end function
;; Controls whether BSP models are illuminated using lightmaps, or by vertex lighting.
;; Vertex lighting will be faster on some graphics cards, but doesn't look as good!
;; See also: <a class=small href=LoadBSP.htm>LoadBSP</a>, <a class=small href=BSPAmbientLight.htm>BSPAmbientLight</a>.
;;param bsp - BSP handle
;;param use_lightmaps - True to use lightmaps, False for vertex lighting. The default  mode is True.
function BSPLighting bsp, use_lightmaps
Graphics3D 640,480  
campiv = CreatePivot()  
cam = CreateCamera(campiv)  
CameraRange cam, 0.1,2000  
level=LoadBSP( "nyk3dm1\nyk3dm1.bsp",.8 ) ; load a 'legal' quake3 bsp map  
BSPAmbientLight level, 0,255,0 ; make the ambient light green  
;BSPLighting level, False ; uncomment this line to turn lightmap off  
While Not KeyDown(1) ; if ESCAPE pressed then exit  
RenderWorld:Flip  
mys = MouseYSpeed()  
If Abs(EntityPitch(cam)+mys) < 75 ; restrict pitch of camera  
TurnEntity cam, mys,0,0  
EndIf  
TurnEntity campiv,0,-MouseXSpeed(),0  
If MouseDown(1) Then ; press mousebutton to move forward  
TFormVector 0,0,3,cam,campiv  
MoveEntity campiv,TFormedX(),TFormedY(),TFormedZ()  
EndIf  
MoveMouse 320,240 ; centre mouse cursor  
Wend  
End  
end function
;; Sets camera background color. Defaults to 0,0,0.
;;param camera - camera handle
;;param red# - red value of camera background color
;;param green# - green value of camera background color
;;param blue# - blue value of camera background color
function CameraClsColor camera,red#,green#,blue#
; CameraClsColor Example  
; ----------------------  
Graphics3D 640,480  
SetBuffer BackBuffer()  
camera=CreateCamera()  
light=CreateLight()  
RotateEntity light,90,0,0  
sphere=CreateSphere( 32 )  
PositionEntity sphere,0,0,5  
While Not KeyDown(1)  
; Change red, green, blue values depending on key pressed  
If KeyDown(2)=True And red#>0 Then red#=red#-1  
If KeyDown(3)=True And red#<255 Then red#=red#+1  
If KeyDown(4)=True And green#>0 Then green#=green#-1  
If KeyDown(5)=True And green#<255 Then green#=green#+1  
If KeyDown(6)=True And blue#>0 Then blue#=blue#-1  
If KeyDown(7)=True And blue#<255 Then blue#=blue#+1  
; Set camera clear color using red, green, blue values  
CameraClsColor camera,red#,green#,blue#  
RenderWorld  
Text 0,0,"Press keys 1-6 to change CameraClsColor red#,green#,blue# values  
Text 0,20,"Red: "+red#  
Text 0,40,"Green: "+green#  
Text 0,60,"Blue: "+blue#  
Flip  
Wend  
End  
end function
;; Sets camera clear mode.
;;param camera - camera handle
;;param cls_color - true to clear the color buffer, false not to
;;param cls_zbuffer - true to clear the z-buffer, false not to
function CameraClsMode camera,cls_color,cls_zbuffer
; CameraClsMode Example  
; ---------------------  
Graphics3D 640,480  
SetBuffer BackBuffer()  
camera=CreateCamera()  
PositionEntity camera,0,1,-10  
light=CreateLight()  
RotateEntity light,90,0,0  
plane=CreatePlane()  
ground_tex=LoadTexture("media/MossyGround.bmp")  
EntityTexture plane,ground_tex  
cube=CreateCube()  
PositionEntity cube,0,1,0  
; Load 2D background image  
background=LoadImage("media/sky.bmp")  
; Use red ink color so we can see text on top of black or light blue background  
Color 255,0,0  
While Not KeyDown(1)  
If KeyDown(205)=True Then TurnEntity camera,0,-1,0  
If KeyDown(203)=True Then TurnEntity camera,0,1,0  
If KeyDown(208)=True Then MoveEntity camera,0,0,-0.05  
If KeyDown(200)=True Then MoveEntity camera,0,0,0.05  
; Toggle cls_color value between 0 and 1 when spacebar is pressed  
If KeyHit(57)=True Then cls_color=1-cls_color  
; Set the camera clear mode, using the cls_color value  
CameraClsMode camera,cls_color,1  
; Draw a 2D background. When cls_color is set to 0, the 2D graphics will show  behind the 3D graphics.  
TileBlock background,0,0  
RenderWorld  
Text 0,0,"Use cursor keys to move about"  
Text 0,20,"Press space bar to enable/disable colour clearing"  
Text 0,40,"CameraClsMode camera,"+cls_color+","+1  
Flip  
Wend  
End  
end function
;; Sets camera fog color.
;;param camera - camera handle
;;param red# - red value of value
;;param green# - green value of fog
;;param blue# - blue value of fog
function CameraFogColor camera,red#,green#,blue#
; CameraFogColor Example  
; ----------------------  
Graphics3D 640,480  
SetBuffer BackBuffer()  
camera=CreateCamera()  
PositionEntity camera,0,1,0  
light=CreateLight()  
RotateEntity light,90,0,0  
plane=CreatePlane()  
grass_tex=LoadTexture( "media/mossyground.bmp" )  
EntityTexture plane,grass_tex  
; Set camera fog to 1 (linear fog)  
CameraFogMode camera,1  
; Set camera fog range  
CameraFogRange camera,1,10  
; Set initial fog colour values  
red#=0  
green#=0  
blue#=0  
While Not KeyDown( 1 )  
; Change red, green, blue values depending on key pressed  
If KeyDown( 2 )=True And red#>0 Then red#=red#-1  
If KeyDown( 3 )=True And red#<255 Then red#=red#+1  
If KeyDown( 4 )=True And green#>0 Then green#=green#-1  
If KeyDown( 5 )=True And green#<255 Then green#=green#+1  
If KeyDown( 6 )=True And blue#>0 Then blue#=blue#-1  
If KeyDown( 7 )=True And blue#<255 Then blue#=blue#+1  
; Set camera fog color using red, green, blue values  
CameraFogColor camera,red#,green#,blue#  
If KeyDown( 205 )=True Then TurnEntity camera,0,-1,0  
If KeyDown( 203 )=True Then TurnEntity camera,0,1,0  
If KeyDown( 208 )=True Then MoveEntity camera,0,0,-0.05  
If KeyDown( 200 )=True Then MoveEntity camera,0,0,0.05  
RenderWorld  
Text 0,0,"Use cursor keys to move about the infinite plane"  
Text 0,20,"Press keys 1-6 to change CameraFogColor red#,green#,blue# values  
Text 0,40,"Fog Red: "+red#  
Text 0,60,"Fog Green: "+green#  
Text 0,80,"Fog Blue: "+blue#  
Flip  
Wend  
End  
end function
;; Sets the camera fog mode.
;; This will enable/disable fogging, a technique  used to gradually fade out graphics the further they are away from the camera.  This can be used to avoid 'pop-up', the moment at which 3D objects suddenly  appear on the horizon.
;; The default fog colour is black and the default fog range is 1-1000, although  these can be changed by using CameraFogColor  and CameraFogRange respectively.
;; Each camera can have its own fog mode, for multiple on-screen fog effects.
;;param camera - camera handle
;;param mode -
;;param 0: no fog (default)
;;param 1: linear fog
function CameraFogMode camera,mode
; CameraFogMode Example  
; ---------------------  
Graphics3D 640,480  
SetBuffer BackBuffer()  
camera=CreateCamera()  
PositionEntity camera,0,1,0  
CameraFogRange camera,1,10  
light=CreateLight()  
RotateEntity light,90,0,0  
plane=CreatePlane()  
grass_tex=LoadTexture( "media/mossyground.bmp" )  
EntityTexture plane,grass_tex  
While Not KeyDown( 1 )  
; Toggle camera fog mode between 0 and 1 when spacebar is pressed  
If KeyHit( 57 )=True Then fog_mode=1-fog_mode : CameraFogMode camera,fog_mode  
If KeyDown( 205 )=True Then TurnEntity camera,0,-1,0  
If KeyDown( 203 )=True Then TurnEntity camera,0,1,0  
If KeyDown( 208 )=True Then MoveEntity camera,0,0,-0.05  
If KeyDown( 200 )=True Then MoveEntity camera,0,0,0.05  
RenderWorld  
Text 0,0,"Use cursor keys to move about the infinite plane"  
Text 0,20,"Press spacebar to toggle between CameraFogMode 0/1"  
If fog_mode=False Then Text 0,40,"CameraFogMode 0" Else Text 0,40,"CameraFogMode  1"  
Flip  
Wend  
End  
end function
;; Sets camera fog range.
;; The near parameter specifies at what distance  in front of the camera that the fogging effect will start; all 3D object  before this point will not be faded.
;; The far parameter specifies at what distance in front of the camera that  the fogging effect will end; all 3D objects beyond this point will be  completely faded out.
;;param camera - camera handle
;;param near# - distance in front of camera that fog starts
;;param far# - distance in front of camera that fog ends
function CameraFogRange camera,near#,far#
; CameraFogRange Example  
; ----------------------  
Graphics3D 640,480  
SetBuffer BackBuffer()  
camera=CreateCamera()  
PositionEntity camera,0,1,0  
light=CreateLight()  
RotateEntity light,90,0,0  
plane=CreatePlane()  
grass_tex=LoadTexture( "media/mossyground.bmp" )  
EntityTexture plane,grass_tex  
; Set camera fog to 1 (linear fog)  
CameraFogMode camera,1  
; Set intial fog range value  
fog_range=10  
While Not KeyDown( 1 )  
; If square brackets keys pressed then change fog range value  
If KeyDown( 26 )=True Then fog_range=fog_range-1  
If KeyDown( 27 )=True Then fog_range=fog_range+1  
; Set camera fog range  
CameraFogRange camera,1,fog_range  
If KeyDown( 205 )=True Then TurnEntity camera,0,-1,0  
If KeyDown( 203 )=True Then TurnEntity camera,0,1,0  
If KeyDown( 208 )=True Then MoveEntity camera,0,0,-0.05  
If KeyDown( 200 )=True Then MoveEntity camera,0,0,0.05  
RenderWorld  
Text 0,0,"Use cursor keys to move about the infinite plane"  
Text 0,20,"Press [ or ] to change CameraFogRange value"  
Text 0,40,"CameraFogRange camera,1,"+fog_range  
Flip  
Wend  
End  
end function
;; Picks the entity positioned at the specified viewport coordinates.
;; Returns  the entity picked, or 0 if none there.
;; An entity must have its EntityPickMode set  to a non-0 value value to be 'pickable'.
;; See also: <a class=small href=EntityPick.htm>EntityPick</a>, <a class=small href=LinePick.htm>LinePick</a>, <a class=small href=CameraPick.htm>CameraPick</a>, <a class=small href=EntityPickMode.htm>EntityPickMode</a>.
;;param camera - camera handle
;;param viewport_x# - 2D viewport coordinate
;;param viewport_z# - 2D viewport coordinate
function CameraPick ( camera,viewport_x#,viewport_y# )
; CameraPick Example  
; ------------------  
Graphics3D 640,480,0,2  
SetBuffer BackBuffer()  
camera=CreateCamera()  
PositionEntity camera,0,2,-10  
light=CreateLight()  
RotateEntity light,90,0,0  
plane=CreatePlane()  
EntityPickMode plane,2 ; Make the plane entity 'pickable'. Use pick_geometry  mode no.2 for polygon collision.  
ground_tex=LoadTexture("media/Chorme-2.bmp")  
EntityTexture plane,ground_tex  
cube=CreateCube()  
EntityPickMode cube,2 ; Make the cube entity 'pickable'. Use pick_geometry mode  no.2 for polygon collision.  
cube_tex=LoadTexture("media/b3dlogo.jpg")  
EntityTexture cube,cube_tex  
PositionEntity cube,0,1,0  
While Not KeyDown( 1 )  
If KeyDown( 205 )=True Then TurnEntity camera,0,-1,0  
If KeyDown( 203 )=True Then TurnEntity camera,0,1,0  
If KeyDown( 208 )=True Then MoveEntity camera,0,0,-0.05  
If KeyDown( 200 )=True Then MoveEntity camera,0,0,0.05  
; If left mouse button hit then use CameraPick with mouse coordinates  
; In this example, only three things can be picked: the plane, the cube, or  nothing  
If MouseHit(1)=True Then CameraPick(camera,MouseX(),MouseY())  
RenderWorld  
Text 0,0,"Use cursor keys to move about"  
Text 0,20,"Press left mouse button to use CameraPick with mouse coordinates"  
Text 0,40,"PickedX: "+PickedX#()  
Text 0,60,"PickedY: "+PickedY#()  
Text 0,80,"PickedZ: "+PickedZ#()  
Text 0,100,"PickedNX: "+PickedNX#()  
Text 0,120,"PickedNY: "+PickedNY#()  
Text 0,140,"PickedNZ: "+PickedNZ#()  
Text 0,160,"PickedTime: "+PickedTime#()  
Text 0,180,"PickedEntity: "+PickedEntity()  
Text 0,200,"PickedSurface: "+PickedSurface()  
Text 0,220,"PickedTriangle: "+PickedTriangle()  
Flip  
Wend  
End  
end function
;; Projects the world coordinates x,y,z on to the 2D screen.
;;param camera - camera handle
;;param x# - world coordinate x
;;param y# - world coordinate y
;;param z# - world coordinate z
function CameraProject camera,x#,y#,z#
; CameraProject Example  
; ---------------------  
Graphics3D 640,480  
SetBuffer BackBuffer()  
camera=CreateCamera()  
PositionEntity camera,0,2,-10  
light=CreateLight()  
RotateEntity light,90,0,0  
plane=CreatePlane()  
ground_tex=LoadTexture("media/Chorme-2.bmp")  
EntityTexture plane,ground_tex  
cube=CreateCube()  
cube_tex=LoadTexture("media/b3dlogo.jpg")  
EntityTexture cube,cube_tex  
PositionEntity cube,0,1,0  
While Not KeyDown( 1 )  
If KeyDown( 205 )=True Then TurnEntity camera,0,-1,0  
If KeyDown( 203 )=True Then TurnEntity camera,0,1,0  
If KeyDown( 208 )=True Then MoveEntity camera,0,0,-0.05  
If KeyDown( 200 )=True Then MoveEntity camera,0,0,0.05  
; Use camera project to get 2D coordinates from 3D coordinates of cube  
CameraProject(camera,EntityX(cube),EntityY(cube),EntityZ(cube))  
RenderWorld  
; If cube is in view then draw text, if not then draw nothing otherwise text  will be drawn at 0,0  
If EntityInView(cube,camera)=True  
; Use ProjectedX() and ProjectedY() to get 2D coordinates from when CameraProject  was used.  
; Use these coordinates to draw text at a 2D position, on top of a 3D scene.  
Text ProjectedX#(),ProjectedY#(),"Cube"  
EndIf  
Text 0,0,"Use cursor keys to move about"  
Text 0,20,"ProjectedX: "+ProjectedX#()  
Text 0,40,"ProjectedY: "+ProjectedY#()  
Text 0,60,"ProjectedZ: "+ProjectedZ#()  
Text 0,80,"EntityInView: "+EntityInView(cube,camera)  
Flip  
Wend  
End  
end function
;; Sets the camera projection mode.
;; The projection mode is the the technique  used by Blitz to display 3D graphics on the screen. Using projection mode 0,  nothing is displayed on the screen, and this is the fastest method of hiding  a camera. Using camera projection mode 1, the graphics are displayed in their  'correct' form - and this is the default mode for a camera. Camera projection  mode 2 is a special type of projection, used for displaying 3D graphics on screen,  but in a 2D form - that is, no sense of perspective will be given to the graphics.  Two identical objects at varying distances from the camera will both appear  to be the same size. Orthographic projection is useful for 3D editors, where  a sense of perspective is unimportant, and also certain games.
;; Use 'CameraZoom' to control the scale of graphics rendered with orthographic  projection. As a general rule, using orthographic projection with the default  camera zoom setting of 1 will result in graphics that are too 'zoomed-in' -  changing the camera zoom to 0.1 should fix this.
;; One thing to note with using camera project mode 2, is that terrains will  not be displayed correctly - this is because the level of detail algorithm used  by terrains relies on perspective in order to work properly.
;;param camera - camera handle
;;param mode - projection mode:
;;param 0: no projection - disables camera (faster than HideEntity)
;;param 1: perspective projection (default)
;;param 2: orthographic projection
function CameraProjMode camera,mode
; CameraProjMode Example  
; ----------------------  
Graphics3D 640,480  
SetBuffer BackBuffer()  
camera=CreateCamera()  
PositionEntity camera,0,0,-10  
light=CreateLight()  
RotateEntity light,0,0,0  
; Create cube 1, near to camera  
cube1=CreateCube()  
EntityColor cube1,255,0,0  
PositionEntity cube1,0,0,0  
; Create cube 2, same size as cube 1 but further away  
cube2=CreateCube()  
EntityColor cube2,0,255,0  
PositionEntity cube2,5,5,5  
While Not KeyDown( 1 )  
; If spacebar pressed then change mode value  
If KeyHit(57)=True Then mode=mode+1 : If mode=3 Then mode=0  
; If mode value = 2 (orthagraphic), then reduce zoom value to 0.1  
If mode=2 Then zoom#=0.1 Else zoom#=1  
; Set camera projection mode using mode value  
CameraProjMode camera,mode  
; Set camera zoom using zoom value  
CameraZoom camera,zoom#  
RenderWorld  
Text 0,0,"Press spacebar to change the camera project mode"  
Text 0,20,"CameraProjMode camera,"+mode  
Text 0,40,"CameraZoom camera,"+zoom#  
Flip  
Cls  
Wend  
End  
end function
;; Sets camera range.
;; Try and keep the ratio of far/near as small as possible  for optimal z-buffer performance. Defaults to 1,1000.
;;param camera - camera handle
;;param near - distance in front of camera that 3D objects start being drawn
;;param far - distance in front of camera that 3D object stop being drawn
function CameraRange camera,near#,far#
; CameraRange Example  
; -------------------  
Graphics3D 640,480  
SetBuffer BackBuffer()  
camera=CreateCamera()  
PositionEntity camera,0,1,0  
light=CreateLight()  
RotateEntity light,90,0,0  
plane=CreatePlane()  
grass_tex=LoadTexture("media/mossyground.bmp")  
EntityTexture plane,grass_tex  
; Set intial camera range value  
cam_range=10  
While Not KeyDown( 1 )  
; If square brackets keys pressed then change camera range value  
If KeyDown(26)=True Then cam_range=cam_range-1  
If KeyDown(27)=True Then cam_range=cam_range+1  
; Set camera range  
CameraRange camera,1,cam_range  
If KeyDown(205)=True Then TurnEntity camera,0,-1,0  
If KeyDown(203)=True Then TurnEntity camera,0,1,0  
If KeyDown(208)=True Then MoveEntity camera,0,0,-0.05  
If KeyDown(200)=True Then MoveEntity camera,0,0,0.05  
RenderWorld  
Text 0,0,"Use cursor keys to move about the infinite plane"  
Text 0,20,"Press [ or ] to change CameraRange value"  
Text 0,40,"CameraRange camera,1,"+cam_range  
Flip  
Wend  
End  
end function
;; Sets the camera viewport position and size.
;; The camera viewport is the  area of the 2D screen that the 3D graphics as viewed by the camera are  displayed in.
;; Setting the camera viewport allows you to achieve spilt-screen and  rear-view mirror effects.
;;param camera - camera handle
;;param x - x coordinate of top left hand corner of viewport
;;param y - y coordinate of top left hand corner of viewport
;;param width - width of viewport
;;param height - height of viewport
function CameraViewport camera,x,y,width,height
; CameraViewport Example  
; ----------------------  
Graphics3D 640,480  
SetBuffer BackBuffer()  
; Create first camera  
cam1=CreateCamera()  
; Set the first camera's viewport so that it fills the top half of the  camera  
CameraViewport cam1,0,0,GraphicsWidth(),GraphicsHeight()/2  
; Create second camera  
cam2=CreateCamera()  
; Set the second camera's viewport so that it fills the bottom half of the  camera  
CameraViewport cam2,0,GraphicsHeight()/2,GraphicsWidth(),GraphicsHeight()/2  
light=CreateLight()  
RotateEntity light,90,0,0  
plane=CreatePlane()  
grass_tex=LoadTexture( "media/mossyground.bmp" )  
EntityTexture plane,grass_tex  
PositionEntity plane,0,-1,0  
While Not KeyDown( 1 )  
If KeyDown( 205 )=True Then TurnEntity cam1,0,-1,0  
If KeyDown( 203 )=True Then TurnEntity cam1,0,1,0  
If KeyDown( 208 )=True Then MoveEntity cam1,0,0,-0.05  
If KeyDown( 200 )=True Then MoveEntity cam1,0,0,0.05  
RenderWorld  
Text 0,0,"Use cursor keys to move the first camera about the infinite plane"  
Flip  
Wend  
End  
end function
;; Sets zoom factor for a camera. Defaults to 1.
;;param camera - camera handle
;;param zoom# - zoom factor of camera
function CameraZoom camera,zoom#
; CreateZoom Example  
; ------------------  
Graphics3D 640,480  
SetBuffer BackBuffer()  
camera=CreateCamera()  
PositionEntity camera,0,1,0  
light=CreateLight()  
RotateEntity light,90,0,0  
ground=CreatePlane()  
sand_tex=LoadTexture("media/sand.bmp")  
ScaleTexture sand_tex,10,10  
EntityTexture ground,sand_tex  
EntityColor ground,168,133,55  
cactus1=LoadMesh("media/CACTUS2.x")  
cactus2=LoadMesh("media/CACTUS2.x")  
camel=LoadMesh("media/camel.x")  
PositionEntity cactus1,-1,2,10  
PositionEntity cactus2,1,2,10  
PositionEntity camel,0,1,1000  
; Set initial zoom value  
zoom#=1  
While Not KeyDown( 1 )  
If KeyDown( 205 )=True Then TurnEntity camera,0,-1,0  
If KeyDown( 203 )=True Then TurnEntity camera,0,1,0  
; Change zoom value depending on key pressed  
If KeyDown( 208 )=True Then zoom#=zoom#-0.1  
If KeyDown( 200 )=True Then zoom#=zoom#+0.1  
; Put a minimum and maximum cap on zoom value  
If zoom#<1 Then zoom#=1  
If zoom#>100 Then zoom#=100  
; Set camera zoom  
CameraZoom camera,zoom#  
RenderWorld  
Text 0,0,"Use left and right cursor keys to turn around"  
Text 0,20,"Use up and down cursor keys to change camera zoom"  
Text 0,40,"There is a camel on the horizon, inbetween the cacti. Zoom in to  see it."  
Text 0,60,"CameraZoom camera,"+zoom#  
Flip  
Wend  
End  
end function
;; CaptureWorld 'captures' the properties (position, rotation, scale, alpha etc) of each entity in the 3D world.
;; This is then used in conjunction with the <a class=small href=../3d_commands/RenderWorld.htm>RenderWorld</a> tween parameter in order to render an interpolated frame between the captured state of each entity and the current state of each entity. See the <a class=small href=../3d_commands/RenderWorld.htm>RenderWorld</a> docs for a full explanation of render tweening.
;; See also: <a class=small href=RenderWorld.htm>RenderWorld</a>.
;;param None.
function CaptureWorld
; CaptureWorld and RenderWorld with tween.  
; Left/Right arrow keys change tween. Escape quits.  
Const width = 640, height = 480  
Const KEY_ESC = 1, KEY_LEFT = 203, KEY_RIGHT = 205  
Graphics3D 640, 480  
AmbientLight 50, 50, 50  
c1 = CreateCone( )  
PositionEntity c1, -5, 0, 0		; on the left side of the screen  
ScaleEntity c1, 1, 3, 1  
EntityColor c1, 255, 0, 0  
cam = CreateCamera()  
PositionEntity cam, 0, 0, -50  
CameraZoom cam, 4  
lt = CreateLight() : TurnEntity lt, 30, 40, 0  
c2 = CopyEntity( c1 )  
CaptureWorld	; with c1 and c2 identically placed  
MoveEntity c2, 8, 0, 0		; to the right side of the screen  
TurnEntity c2, 0, 0, 90		; and tilted  
tw# = 100		; 100 * tween  
; Interpolate between the pre-CaptureWorld ( tween = 0 )  
; arrangement and the current one ( tween = 1 ).  
While Not KeyDown( KEY_ESC )  
If KeyDown( KEY_LEFT  ) Then tw = tw - 1  
If KeyDown( KEY_RIGHT ) Then tw = tw + 1  
RenderWorld tw / 100  
Text 250, 100, "tween = " + ( tw / 100 )  
Flip  
Wend  
End  
end function
;; Clears the collision information list.
;; Whenever you use the Collisions command to enable collisions between  two different entity types, information is added to the collision list. This  command clears that list, so that no collisions will be detected until the Collisions  command is used again.
;; The command will not clear entity collision information. For example, entity  radius, type etc.
;;param None.
function ClearCollisions
; ClearCollisions Example  
; -----------------------  
Graphics3D 640,480  
SetBuffer BackBuffer()  
camera=CreateCamera()  
light=CreateLight()  
sphere=CreateSphere( 32 )  
PositionEntity sphere,-2,0,5  
cone=CreateCone( 32 )  
EntityType cone,type_cone  
PositionEntity cone,2,0,5  
; Set collision type values  
type_sphere=1  
type_cone=2  
; Set up sphere collision data  
EntityRadius sphere,1  
EntityType sphere,type_sphere  
; Set up cone collision data  
EntityType cone,type_cone  
; Enable collisions between type_sphere and type_cone, with sphere->polygon  method and slide response  
Collisions type_sphere,type_cone,2,2  
While Not KeyDown( 1 )  
x#=0  
y#=0  
z#=0  
If KeyDown( 203 )=True Then x#=-0.1  
If KeyDown( 205 )=True Then x#=0.1  
If KeyDown( 208 )=True Then y#=-0.1  
If KeyDown( 200 )=True Then y#=0.1  
If KeyDown( 44 )=True Then z#=-0.1  
If KeyDown( 30 )=True Then z#=0.1  
MoveEntity sphere,x#,y#,z#  
; If spacebar pressed then clear collisions  
If KeyHit( 57 )=True Then ClearCollisions  
; Perform collision checking  
UpdateWorld  
RenderWorld  
Text 0,0,"Use cursor/A/Z keys to move sphere"  
Text 0,20,"Press spacebar to use ClearCollisions command"  
Flip  
Wend  
End  
end function
;; Removes all vertices and/or triangles from a surface.
;; This is useful for  clearing sections of mesh. The results will be instantly visible.
;; After clearing a surface, you may wish to add vertices and triangles to it  again but with a slightly different polygon count for dynamic level of detail  (LOD).
;; Click <a href=http://www.blitzbasic.co.nz/b3ddocs/command.php?name=ClearSurface&ref=comments target=_blank>here</a> to view the latest version of this page online</body>
;; </html>
;;param surface - surface handle
;;param clear_verts (optional) - true to remove all vertices from the specified surface,  false not to. Defaults to true.
;;param clear_triangles (optional) - true to remove all triangles from the specified  surface, false not to. Defaults to true.
function ClearSurface surface,[clear_verts][,clear_triangles]
end function
;; Clears the current texture filter list.
;;param None.
function ClearTextureFilters
; ClearTextureFilters and TextureFilter Example.  
; ----------------------------------------------  
Const tex_color 	= 1		; Color texture  
Const tex_alpha 	= 2		; Alpha texture (Include alpha channel data)  
Const tex_mask 		= 4		; Masked texture (black is transparent)  
Const tex_mipmap 	= 8		; Create texture mipmaps  
Const tex_clampu 	= 16	; Restrict U texture coords from "bleeding over"  
Const tex_clampv	= 32	; Restrict V texture coords from "bleeding over"  
Const tex_envshpere	= 64	; Load texture as a spherical environment map  
Const tex_vram 		= 256	; Force texture graphics to vram  
Const tex_highcolor	= 512	; Forces texture graphics to be 32-bits per pixel  
Graphics3D 640,480  
; Removes any texture filters that might apply.  
ClearTextureFilters  
; Add an alpha texture to the list of  
; texture filters to apply to files  
; that have "_alpha" in their filenames.  
TextureFilter "_alpha",tex_color + tex_alpha + tex_mipmap  
; Set appropriate texture flags for loading  
; suitable skybox textures from files named  
; something with "_skybox".  
TextureFilter "_skybox", tex_color + tex_mipmap + tex_clampu + tex_clampv  
; Set the flags for loading a spherical refletction  
; map to apply to all "_refmap" files.  
TextureFilter "_refmap", tex_color + tex_mipmap + tex_envshpere  
; Setup a texture filter to allow faster  
; (and easier) pixel manipulation on all  
; loaded "_fastblit" files.  
TextureFilter "_fastblit", tex_color + tex_vram + tex_highcolor  
; This is where you would normally load your special  
; textures.  
; The next bit resets the texture filters to their  
; standard settings.  
ClearTextureFilters  
TextureFilter "", tex_color + tex_mipmap  
End  
end function
;; Clears all entities, brushes and/or textures from the screen and from memory.
;; As soon as you clear something, you will not be able to use it again until you  reload it. Trying to do so will cause a runtime error.
;; This command is useful for when a level has finished and you wish to load  a different level with new entities, brushes and textures.
;; Click <a href=http://www.blitzbasic.co.nz/b3ddocs/command.php?name=ClearWorld&ref=comments target=_blank>here</a> to view the latest version of this page online</body>
;; </html>
;;param entities (optional) - True to clear all entities, False not to. Defaults  to true.
;;param brushes (optional) - True to clear all brushes, False not to. Defaults to true.
;;param textures (optional) - True to clear all textures, False not to. Defaults to  true.
function ClearWorld [entities][,brushes][,textures]
end function
;; Returns the other entity involved in a particular collision. Index should  be in the range 1...CountCollisions( entity  ), inclusive.
;; See also: <a class=small href=CollisionX.htm>CollisionX</a>, <a class=small href=CollisionY.htm>CollisionY</a>, <a class=small href=CollisionZ.htm>CollisionZ</a>, <a class=small href=CollisionNX.htm>CollisionNX</a>, <a class=small href=CollisionNY.htm>CollisionNY</a>, <a class=small href=CollisionNZ.htm>CollisionNZ</a>, <a class=small href=CountCollisions.htm>CountCollisions</a>, <a class=small href=EntityCollided.htm>EntityCollided</a>, <a class=small href=CollisionTime.htm>CollisionTime</a>, <a class=small href=CollisionEntity.htm>CollisionEntity</a>, <a class=small href=CollisionSurface.htm>CollisionSurface</a>, <a class=small href=CollisionTriangle.htm>CollisionTriangle</a>.
;; Click <a href=http://www.blitzbasic.co.nz/b3ddocs/command.php?name=CollisionEntity&ref=comments target=_blank>here</a> to view the latest version of this page online</body>
;; </html>
;;param entity - entity handle
;;param index - index of collision
function CollisionEntity ( entity,index )
end function
;; Returns the x component of the normal of a particular collision.
;; Index  should be in the range 1...CountCollisions(  entity ) inclusive.
;; See also: <a class=small href=CollisionX.htm>CollisionX</a>, <a class=small href=CollisionY.htm>CollisionY</a>, <a class=small href=CollisionZ.htm>CollisionZ</a>, <a class=small href=CollisionNX.htm>CollisionNX</a>, <a class=small href=CollisionNY.htm>CollisionNY</a>, <a class=small href=CollisionNZ.htm>CollisionNZ</a>, <a class=small href=CountCollisions.htm>CountCollisions</a>, <a class=small href=EntityCollided.htm>EntityCollided</a>, <a class=small href=CollisionTime.htm>CollisionTime</a>, <a class=small href=CollisionEntity.htm>CollisionEntity</a>, <a class=small href=CollisionSurface.htm>CollisionSurface</a>, <a class=small href=CollisionTriangle.htm>CollisionTriangle</a>.
;; Click <a href=http://www.blitzbasic.co.nz/b3ddocs/command.php?name=CollisionNX&ref=comments target=_blank>here</a> to view the latest version of this page online</body>
;; </html>
;;param entity - entity handle
;;param index - index of collision
function CollisionNX# ( entity,index )
end function
;; Returns the y component of the normal of a particular collision.
;; Index  should be in the range 1...CountCollisions(  entity ) inclusive.
;; See also: <a class=small href=CollisionX.htm>CollisionX</a>, <a class=small href=CollisionY.htm>CollisionY</a>, <a class=small href=CollisionZ.htm>CollisionZ</a>, <a class=small href=CollisionNX.htm>CollisionNX</a>, <a class=small href=CollisionNY.htm>CollisionNY</a>, <a class=small href=CollisionNZ.htm>CollisionNZ</a>, <a class=small href=CountCollisions.htm>CountCollisions</a>, <a class=small href=EntityCollided.htm>EntityCollided</a>, <a class=small href=CollisionTime.htm>CollisionTime</a>, <a class=small href=CollisionEntity.htm>CollisionEntity</a>, <a class=small href=CollisionSurface.htm>CollisionSurface</a>, <a class=small href=CollisionTriangle.htm>CollisionTriangle</a>.
;; Click <a href=http://www.blitzbasic.co.nz/b3ddocs/command.php?name=CollisionNY&ref=comments target=_blank>here</a> to view the latest version of this page online</body>
;; </html>
;;param entity - entity handle
;;param index - index of collision
function CollisionNY# ( entity,index )
end function
;; Returns the z component of the normal of a particular collision.
;; Index  should be in the range 1...CountCollisions(  entity ) inclusive.
;; See also: <a class=small href=CollisionX.htm>CollisionX</a>, <a class=small href=CollisionY.htm>CollisionY</a>, <a class=small href=CollisionZ.htm>CollisionZ</a>, <a class=small href=CollisionNX.htm>CollisionNX</a>, <a class=small href=CollisionNY.htm>CollisionNY</a>, <a class=small href=CollisionNZ.htm>CollisionNZ</a>, <a class=small href=CountCollisions.htm>CountCollisions</a>, <a class=small href=EntityCollided.htm>EntityCollided</a>, <a class=small href=CollisionTime.htm>CollisionTime</a>, <a class=small href=CollisionEntity.htm>CollisionEntity</a>, <a class=small href=CollisionSurface.htm>CollisionSurface</a>, <a class=small href=CollisionTriangle.htm>CollisionTriangle</a>.
;; Click <a href=http://www.blitzbasic.co.nz/b3ddocs/command.php?name=CollisionNZ&ref=comments target=_blank>here</a> to view the latest version of this page online</body>
;; </html>
;;param entity - entity handle
;;param index - index of collision
function CollisionNZ# ( entity,index )
end function
;; Enables collisions between two different entity types.
;; Entity types are just numbers you assign to an entity using EntityType. Blitz then uses the entity types to check for collisions between all the entities that have those entity types.
;; Blitz has many ways of checking for collisions, as denoted by the method parameter. However, collision checking is always ellipsoid to something. In order for Blitz to know what size a source entity is, you must first assign an entity radius to all source entities using EntityRadius.
;; In the case of collision detection method 1 being selected (ellipsoid-to-ellipsoid), then the destination entities concerned will need to have an EntityRadius assigned to them too. In the case of method 3 being selected (ellipsoid-to-box), then the destination entities  will need to have an EntityBox assigned to them. Method 2 (ellipsoid-to-polygon) requires nothing to be assigned to the destination entities.
;; Not only does Blitz check for collisions, but it acts upon them when it detects them too, as denoted by the response parameter. You have three options in this situation. You can either choose to make the source entity stop, slide or only slide upwards.
;; All collision checking occurs, and collision responses are acted out, when UpdateWorld is called.
;; Finally, every time the Collision command is used, collision information is added to the collision information list. This can be cleared at any time using the ClearCollisions command.
;; See also: <a class=small href=EntityBox.htm>EntityBox</a>, <a class=small href=EntityRadius.htm>EntityRadius</a>, <a class=small href=Collisions.htm>Collisions</a>, <a class=small href=EntityType.htm>EntityType</a>, <a class=small href=ResetEntity.htm>ResetEntity</a>.
;;param src_type - entity type to be checked for collisions.
;;param dest_type - entity type to be collided with.
;;param method - collision detection method.
;;param 1: ellipsoid-to-ellipsoid collisions
;;param 2: ellipsoid-to-polygon collisions
;;param 3: ellipsoid-to-box collisions
;;param response - what the source entity does when a collision occurs.
;;param 1: stop
;;param 2: slide1 - full sliding collision
;;param 3: slide2 - prevent entities from sliding down slopes
function Collisions src_type,dest_type,method,response
; Collisions Example  
; ------------------  
Graphics3D 640,480  
SetBuffer BackBuffer()  
; Set collision type values  
type_ground=1  
type_character=2  
type_scenery=3  
camera=CreateCamera()  
RotateEntity camera,45,0,0  
PositionEntity camera,0,15,-10  
light=CreateLight()  
RotateEntity light,45,0,0  
; Create cube 'ground'  
cube=CreateCube()  
ScaleEntity cube,10,10,10  
EntityColor cube,0,127,0  
EntityType cube,type_ground  
PositionEntity cube,0,-5,0  
; Create sphere 'character'  
sphere=CreateSphere( 32 )  
EntityColor sphere,127,0,0  
EntityRadius sphere,1  
EntityType sphere,type_character  
PositionEntity sphere,0,7,0  
; Enable collisions between type_character and type_ground  
Collisions type_character,type_ground,2,2  
; Create cylinder 'scenery'  
cylinder=CreateCylinder( 32 )  
ScaleEntity cylinder,2,2,2  
EntityColor cylinder,0,0,255  
EntityRadius cylinder,2  
EntityBox cylinder,-2,-2,-2,4,4,4  
EntityType cylinder,type_scenery  
PositionEntity cylinder,-4,7,-4  
; Create cone 'scenery'  
cone=CreateCone( 32 )  
ScaleEntity cone,2,2,2  
EntityColor cone,0,0,255  
EntityRadius cone,2  
EntityBox cone,-2,-2,-2,4,4,4  
EntityType cone,type_scenery  
PositionEntity cone,4,7,-4  
; Create prism 'scenery'  
prism=CreateCylinder( 3 )  
ScaleEntity prism,2,2,2  
EntityColor prism,0,0,255  
EntityRadius prism,2  
EntityBox prism,-2,-2,-2,4,4,4  
EntityType prism,type_scenery  
PositionEntity prism,-4,7,4  
RotateEntity prism,0,180,0  
; Create pyramid 'scenery'  
pyramid=CreateCone( 4 )  
ScaleEntity pyramid,2,2,2  
EntityColor pyramid,0,0,255  
EntityRadius pyramid,2  
EntityBox pyramid,-2,-2,-2,4,4,4  
EntityType pyramid,type_scenery  
RotateEntity pyramid,0,45,0  
PositionEntity pyramid,4,7,4  
; Set collision method and response values  
method=2  
response=2  
method_info$="ellipsoid-to-polygon"  
response_info$="slide1"  
While Not KeyDown( 1 )  
x#=0  
y#=0  
z#=0  
If KeyDown( 203 )=True Then x#=-0.1  
If KeyDown( 205 )=True Then x#=0.1  
If KeyDown( 208 )=True Then z#=-0.1  
If KeyDown( 200 )=True Then z#=0.1  
MoveEntity sphere,x#,y#,z#  
MoveEntity sphere,0,-0.02,0 ; gravity  
; Change collision method  
If KeyHit( 50 )=True  
method=method+1  
If method=4 Then method=1  
If method=1 Then method_info$="ellipsoid-to-sphere"  
If method=2 Then method_info$="ellipsoid-to-polygon"  
If method=3 Then method_info$="ellipsoid-to-box"  
EndIf  
; Change collision response  
If KeyHit( 19 )=True  
response=response+1  
If response=4 Then response=1  
If response=1 Then response_info$="stop"  
If response=2 Then response_info$="slide1"  
If response=3 Then response_info$="slide2"  
EndIf  
; Enable collisions between type_character and type_scenery  
Collisions type_character,type_scenery,method,response  
; Perform collision checking  
UpdateWorld  
RenderWorld  
Text 0,0,"Use cursor keys to move sphere"  
Text 0,20,"Press M to change collision Method (currently: "+method_info$+")"  
Text 0,40,"Press R to change collision Response (currently: "+response_info$+")"  
Text 0,60,"Collisions type_character,type_scenery,"+method+","+response  
Flip  
Wend  
End  
end function
;; Returns the handle of the surface belonging to the specified entity that was closest to the point of a particular collision.
;; Index should be in  the range 1...CountCollisions( entity ), inclusive.
;; See also: <a class=small href=CollisionX.htm>CollisionX</a>, <a class=small href=CollisionY.htm>CollisionY</a>, <a class=small href=CollisionZ.htm>CollisionZ</a>, <a class=small href=CollisionNX.htm>CollisionNX</a>, <a class=small href=CollisionNY.htm>CollisionNY</a>, <a class=small href=CollisionNZ.htm>CollisionNZ</a>, <a class=small href=CountCollisions.htm>CountCollisions</a>, <a class=small href=EntityCollided.htm>EntityCollided</a>, <a class=small href=CollisionTime.htm>CollisionTime</a>, <a class=small href=CollisionEntity.htm>CollisionEntity</a>, <a class=small href=CollisionSurface.htm>CollisionSurface</a>, <a class=small href=CollisionTriangle.htm>CollisionTriangle</a>.
;; Click <a href=http://www.blitzbasic.co.nz/b3ddocs/command.php?name=CollisionSurface&ref=comments target=_blank>here</a> to view the latest version of this page online</body>
;; </html>
;;param entity - entity handle
;;param index - index of collision
function CollisionSurface ( entity,index )
end function
;; Returns the time taken to calculate a particular collision.
;; Index should be in the range 1...CountCollisions(  entity ) inclusive.
;; See also: <a class=small href=CollisionX.htm>CollisionX</a>, <a class=small href=CollisionY.htm>CollisionY</a>, <a class=small href=CollisionZ.htm>CollisionZ</a>, <a class=small href=CollisionNX.htm>CollisionNX</a>, <a class=small href=CollisionNY.htm>CollisionNY</a>, <a class=small href=CollisionNZ.htm>CollisionNZ</a>, <a class=small href=CountCollisions.htm>CountCollisions</a>, <a class=small href=EntityCollided.htm>EntityCollided</a>, <a class=small href=CollisionTime.htm>CollisionTime</a>, <a class=small href=CollisionEntity.htm>CollisionEntity</a>, <a class=small href=CollisionSurface.htm>CollisionSurface</a>, <a class=small href=CollisionTriangle.htm>CollisionTriangle</a>.
;; Click <a href=http://www.blitzbasic.co.nz/b3ddocs/command.php?name=CollisionTime&ref=comments target=_blank>here</a> to view the latest version of this page online</body>
;; </html>
;;param entity - entity handle
;;param index - index of collision
function CollisionTime ( entity,index )
end function
;; Returns the index number of the triangle belonging to the specified entity  that was closest to the point of a particular collision.
;; Index should be in the range 1...CountCollisions(  entity ), inclusive.
;; See also: <a class=small href=CollisionX.htm>CollisionX</a>, <a class=small href=CollisionY.htm>CollisionY</a>, <a class=small href=CollisionZ.htm>CollisionZ</a>, <a class=small href=CollisionNX.htm>CollisionNX</a>, <a class=small href=CollisionNY.htm>CollisionNY</a>, <a class=small href=CollisionNZ.htm>CollisionNZ</a>, <a class=small href=CountCollisions.htm>CountCollisions</a>, <a class=small href=EntityCollided.htm>EntityCollided</a>, <a class=small href=CollisionTime.htm>CollisionTime</a>, <a class=small href=CollisionEntity.htm>CollisionEntity</a>, <a class=small href=CollisionSurface.htm>CollisionSurface</a>, <a class=small href=CollisionTriangle.htm>CollisionTriangle</a>.
;; Click <a href=http://www.blitzbasic.co.nz/b3ddocs/command.php?name=CollisionTriangle&ref=comments target=_blank>here</a> to view the latest version of this page online</body>
;; </html>
;;param entity - entity handle
;;param index - index of collision
function CollisionTriangle ( entity,index )
end function
;; Returns the world x coordinate of a particular collision.
;; Index should  be in the range 1...CountCollisions( entity  ) inclusive.
;; See also: <a class=small href=CollisionX.htm>CollisionX</a>, <a class=small href=CollisionY.htm>CollisionY</a>, <a class=small href=CollisionZ.htm>CollisionZ</a>, <a class=small href=CollisionNX.htm>CollisionNX</a>, <a class=small href=CollisionNY.htm>CollisionNY</a>, <a class=small href=CollisionNZ.htm>CollisionNZ</a>, <a class=small href=CountCollisions.htm>CountCollisions</a>, <a class=small href=EntityCollided.htm>EntityCollided</a>, <a class=small href=CollisionTime.htm>CollisionTime</a>, <a class=small href=CollisionEntity.htm>CollisionEntity</a>, <a class=small href=CollisionSurface.htm>CollisionSurface</a>, <a class=small href=CollisionTriangle.htm>CollisionTriangle</a>.
;; Click <a href=http://www.blitzbasic.co.nz/b3ddocs/command.php?name=CollisionX&ref=comments target=_blank>here</a> to view the latest version of this page online</body>
;; </html>
;;param entity - entity handle
;;param index - index of collision
function CollisionX# ( entity,index )
end function
;; Returns the world y coordinate of a particular collision.
;; Index should  be in the range 1...CountCollisions( entity  ) inclusive.
;; See also: <a class=small href=CollisionX.htm>CollisionX</a>, <a class=small href=CollisionY.htm>CollisionY</a>, <a class=small href=CollisionZ.htm>CollisionZ</a>, <a class=small href=CollisionNX.htm>CollisionNX</a>, <a class=small href=CollisionNY.htm>CollisionNY</a>, <a class=small href=CollisionNZ.htm>CollisionNZ</a>, <a class=small href=CountCollisions.htm>CountCollisions</a>, <a class=small href=EntityCollided.htm>EntityCollided</a>, <a class=small href=CollisionTime.htm>CollisionTime</a>, <a class=small href=CollisionEntity.htm>CollisionEntity</a>, <a class=small href=CollisionSurface.htm>CollisionSurface</a>, <a class=small href=CollisionTriangle.htm>CollisionTriangle</a>.
;; Click <a href=http://www.blitzbasic.co.nz/b3ddocs/command.php?name=CollisionY&ref=comments target=_blank>here</a> to view the latest version of this page online</body>
;; </html>
;;param entity - entity handle
;;param index - index of collision
function CollisionY# ( entity,index )
end function
;; Returns the world z coordinate of a particular collision.
;; Index should  be in the range 1...CountCollisions( entity  ) inclusive.
;; See also: <a class=small href=CollisionX.htm>CollisionX</a>, <a class=small href=CollisionY.htm>CollisionY</a>, <a class=small href=CollisionZ.htm>CollisionZ</a>, <a class=small href=CollisionNX.htm>CollisionNX</a>, <a class=small href=CollisionNY.htm>CollisionNY</a>, <a class=small href=CollisionNZ.htm>CollisionNZ</a>, <a class=small href=CountCollisions.htm>CountCollisions</a>, <a class=small href=EntityCollided.htm>EntityCollided</a>, <a class=small href=CollisionTime.htm>CollisionTime</a>, <a class=small href=CollisionEntity.htm>CollisionEntity</a>, <a class=small href=CollisionSurface.htm>CollisionSurface</a>, <a class=small href=CollisionTriangle.htm>CollisionTriangle</a>.
;; Click <a href=http://www.blitzbasic.co.nz/b3ddocs/command.php?name=CollisionZ&ref=comments target=_blank>here</a> to view the latest version of this page online</body>
;; </html>
;;param entity - entity handle
;;param index - index of collision
function CollisionZ# ( entity,index )
end function
;; Creates a copy of an entity and returns the handle of the newly created copy. This is a new entity instance of an existing entity's mesh! Anything you do to the original Mesh (such as RotateMesh) will effect all the copies. Other properties (such as EntityColor, Position etc.) since they are 'Entity' properties, will be individual to the copy.
;; If a parent entity is specified, the copied entity will be created at the parent entity's position. Otherwise, it will be created at 0,0,0.
;;param entity - Entity Handle
;;param parent (optional) - Entity that will act as Parent to the copy.
function CopyEntity ( entity[,parent] )
; CopyEntity Example  
; This example creates an entity and  
; allows you to make copies of it.  
Graphics3D 640,480  
AppTitle "CopyEntity Example"  
Cam = CreateCamera()  
Lit = CreateLight()  
PositionEntity Lit,-5,-5,0  
PositionEntity Cam,0,0,-5  
AnEntity = CreateCube()    ; This is our Test Entity  
ScaleMesh anEntity,0.4,0.4,0.4  
While Not KeyDown(1) ; Until we press ESC  
If KeyHit(57) Then  
; When we hit Space, a new Entity is created  
; These share the same internal mesh structure though!  
; Hence although we are only Rotating the original MESH  
; Linked to the original Entity, since it is a Mesh command,  
; all the Entity Copies are linked to it..  
NewEntity = CopyEntity(AnEntity) ; Hit Space to Copy!  
; Change the Color of the Entity. Since this is an entity  
; Property, it doesn't effect the other copies.  
EntityColor NewEntity,Rand(255),Rand(255),Rand(255)  
PositionEntity NewEntity,Rand(4)-2,Rand(4)-2,0  
EndIf  
SeedRnd MilliSecs()  
RotateMesh AnEntity,.25,.35,.45  
RenderWorld ; Draw the Scene  
Flip ; Flip it into View  
Wend  
End  
end function
;; Creates a copy of a mesh and returns the newly-created mesh's handle.
;; The difference between CopyMesh and CopyEntity  is that CopyMesh performs a 'deep' copy of a mesh.
;; CopyMesh is identical to performing new_mesh=CreateMesh() : AddMesh mesh,new_mesh
;;param mesh - handle of mesh to be copied
;;param parent (optional) - handle of entity to be made parent of mesh
function CopyMesh ( mesh[,parent] )
; CopyMesh Example  
; ----------------  
Graphics3D 640,480  
SetBuffer BackBuffer()  
camera=CreateCamera()  
light=CreateLight()  
RotateEntity light,90,0,0  
crate1=LoadMesh("media/wood-crate/wcrate1.3ds")  
PositionEntity crate1,-40,0,100  
crate2=CopyMesh(crate1)  
PositionEntity crate2,40,0,100  
While Not KeyDown(1)  
TurnEntity crate1,1,1,1  
TurnEntity crate2,1,1,-1  
RenderWorld  
Flip  
Wend  
End  
end function
;; Returns the number of children of an entity.
;;param entity - entity handle
function CountChildren ( entity )
If CountChildren(entity) > 0  
For childcount = 1 to CountChildren(entity)  
child = GetChild(entity,childcount)  
Next  
Endif  
end function
;; Returns how many collisions an entity was involved in during the last UpdateWorld.
;; See also: <a class=small href=CollisionX.htm>CollisionX</a>, <a class=small href=CollisionY.htm>CollisionY</a>, <a class=small href=CollisionZ.htm>CollisionZ</a>, <a class=small href=CollisionNX.htm>CollisionNX</a>, <a class=small href=CollisionNY.htm>CollisionNY</a>, <a class=small href=CollisionNZ.htm>CollisionNZ</a>, <a class=small href=CountCollisions.htm>CountCollisions</a>, <a class=small href=EntityCollided.htm>EntityCollided</a>, <a class=small href=CollisionTime.htm>CollisionTime</a>, <a class=small href=CollisionEntity.htm>CollisionEntity</a>, <a class=small href=CollisionSurface.htm>CollisionSurface</a>, <a class=small href=CollisionTriangle.htm>CollisionTriangle</a>.
;; Click <a href=http://www.blitzbasic.co.nz/b3ddocs/command.php?name=CountCollisions&ref=comments target=_blank>here</a> to view the latest version of this page online</body>
;; </html>
;;param entity - entity handle
function CountCollisions ( entity )
end function
;; Returns the number of 3D compatible modes available on the selected 3D graphics card, and sets up the info to be returned by GfxModeWidth, GfxModeHeight, GfxModeDepth etc (see 2D commands).
;; Any difference between CountGfxModes3D and CountGfxModes has been made largely irrelevant by newer cards as they now tend to support 3D in all graphics modes. However there are a few older cards that do not support 3D in some graphics modes, hence the inclusion of the CountGfxModes3D command.
;;param None.
function CountGfxModes3D()
; CountGfxModes3D()  
; -----------------  
Graphics 800,600,0,2  
For i=1 To CountGfxModes3D()  
Print "Mode "+i+": ("+GfxModeWidth(i)+"x"+GfxModeHeight(i)+"x"+GfxModeDepth(i)+")"  
Next  
WaitKey()  
end function
;; Returns the number of surfaces in a mesh.
;; Surfaces are sections of mesh.  A mesh may contain only one section, or very many.
;; See also: GetSurface.
;; Click <a href=http://www.blitzbasic.co.nz/b3ddocs/command.php?name=CountSurfaces&ref=comments target=_blank>here</a> to view the latest version of this page online</body>
;; </html>
;;param mesh - mesh handle
function CountSurfaces ( mesh )
end function
;; Returns the number of triangles in a surface.
;; Click <a href=http://www.blitzbasic.co.nz/b3ddocs/command.php?name=CountTriangles&ref=comments target=_blank>here</a> to view the latest version of this page online</body>
;; </html>
;;param surface - surface handle
function CountTriangles ( surface )
end function
;; Returns the number of vertices in a surface.
;; Click <a href=http://www.blitzbasic.co.nz/b3ddocs/command.php?name=CountVertices&ref=comments target=_blank>here</a> to view the latest version of this page online</body>
;; </html>
;;param surface - surface handle
function CountVertices ( surface )
end function
;; Creates a brush and returns a brush handle.
;; The optional green, red and  blue values allow you to set the colour of the brush. Values should be in the  range 0-255. If omitted the values default to 255.
;; A brush is a collection of properties such as Colour, Alpha, Shininess, Texture  etc that are all stored as part of the brush. Then, all these properties can  be applied to an entity, mesh or surface at once just by using PaintEntity, PaintMesh  or PaintSurface.
;; When creating your own mesh, if you wish for certain surfaces to look differently  from one another, then you will need to use brushes to paint individual surfaces.  Using commands such as EntityColor, EntityAlpha will apply the effect to all  surfaces at once, which may not be what you wish to achieve.
;; See also: LoadBrush.
;;param red# (optional) - brush red value
;;param green# (optional) - brush green value
;;param blue# (optional) - brush blue value
function CreateBrush ( [red#][,green#][,blue#] )
; CreateBrush Example  
; -------------------  
Graphics3D 640,480  
SetBuffer BackBuffer()  
camera=CreateCamera()  
light=CreateLight()  
RotateEntity light,90,0,0  
cube=CreateCube()  
PositionEntity cube,0,0,5  
; Load texture  
tex=LoadTexture("media/b3dlogo.jpg")  
; Create brush  
brush=CreateBrush()  
; Apply texture to brush  
BrushTexture brush,tex  
; And some shininess  
BrushShininess brush,1  
; Paint mesh with brush  
PaintMesh cube,brush  
While Not KeyDown( 1 )  
pitch#=0  
yaw#=0  
roll#=0  
If KeyDown( 208 )=True Then pitch#=-1  
If KeyDown( 200 )=True Then pitch#=1  
If KeyDown( 203 )=True Then yaw#=-1  
If KeyDown( 205 )=True Then yaw#=1  
If KeyDown( 45 )=True Then roll#=-1  
If KeyDown( 44 )=True Then roll#=1  
TurnEntity cube,pitch#,yaw#,roll#  
RenderWorld  
Flip  
Wend  
End  
end function
;; Creates a camera entity and returns its handle.
;; Without  at least one camera, you won't be able to see anything in your 3D world. With more than one camera, you will be to achieve effect such as  split-screen modes and rear-view mirrors.
;; A camera can only render to the backbuffer. If you wish to display 3D  graphics on an image or a texture then copy the contents of the backbuffer  to the appropriate buffer.
;; The optional parent parameter allow you to specify a parent entity for  the camera so that when the parent is moved the child camera will move with  it. However, this relationship is one way; applying movement commands to the  child will not affect the parent.
;; Specifying a parent entity will still result in the camera being created  at position 0,0,0 rather than at the parent entity's position.
;;param parent (optional) - parent entity of camera
function CreateCamera ( [parent] )
; CreateCamera Example  
; --------------------  
Graphics3D 640,480  
SetBuffer BackBuffer()  
; Create camera  
camera=CreateCamera()  
light=CreateLight()  
cone=CreateCone()  
PositionEntity cone,0,0,5  
While Not KeyDown( 1 )  
RenderWorld  
Flip  
Wend  
End  
end function
;; Creates a cone mesh/entity and returns its handle.
;; The cone will be centred  at 0,0,0 and the base of the cone will have a radius of 1.
;; The segments value must be in the range 3-100 inclusive, although this is  only checked in debug mode. A common mistake is to leave debug mode off and  specify the parent parameter (usually an eight digit memory address) in the  place of the segments value. As the amount of polygons used to create a cone  is exponentially proportional to the segments value, this will result in Blitz  trying to create a cone with unimaginable amounts of polygons! Depending on  how unlucky you are, your computer will then crash.
;; Example segments values (solid=true):
;; 4: 6 polygons - a pyramid
;; 8: 14 polygons - bare minimum amount of polygons for a cone
;; 16: 30 polygons - smooth cone at medium-high distances
;; 32: 62 polygons - smooth cone at close distances
;; The optional parent parameter allow you to specify a parent entity for the  cone so that when the parent is moved the child cone will move with it. However,  this relationship is one way; applying movement commands to the child will not  affect the parent.
;; Specifying a parent entity will still result in the cone being created at  position 0,0,0 rather than at the parent entity's position.
;; See also: CreateCube, CreateSphere, CreateCylinder.
;;param segments (optional) - cone detail. Defaults to 8.
;;param solid (optional) - true for a cone with a base, false for a cone without a base.  Defaults to true.
;;param parent (optional) - parent entity of cone
function CreateCone ( [segments][,solid][,parent] )
; CreateCone Example  
; ------------------  
Graphics3D 640,480  
SetBuffer BackBuffer()  
camera=CreateCamera()  
light=CreateLight()  
RotateEntity light,90,0,0  
; Create cone  
cone=CreateCone()  
PositionEntity cone,0,0,5  
While Not KeyDown( 1 )  
RenderWorld  
Flip  
Wend  
End  
end function
;; Creates a cube mesh/entity and returns its handle.
;; The cube will extend from  -1,-1,-1 to +1,+1,+1.
;; The optional parent parameter allow you to specify a parent entity for  the cube so that when the parent is moved the child cube will move with it.  However, this relationship is one way; applying movement commands to the  child will not affect the parent.
;; Specifying a parent entity will still result in the cube being created at  position 0,0,0 rather than at the parent entity's position.
;; Creation of cubes, cylinders and cones are a great way of getting scenes set up quickly, as they can act as placeholders for more complex pre-modeled meshes later on in program development.
;; See also: CreateSphere(),  CreateCylinder(), CreateCone().
;;param [parent] (optional) - This allows you to set the parent entity of Cube.
function CreateCube( [parent] )
; CreateCube Example  
; ------------------  
Graphics3D 640,480  
SetBuffer BackBuffer()  
camera=CreateCamera()  
light=CreateLight()  
RotateEntity light,90,0,0  
; Create cube  
cube=CreateCube()  
PositionEntity cube,0,0,5  
While Not KeyDown( 1 )  
RenderWorld  
Flip  
Wend  
End  
end function
;; Creates a cylinder mesh/entity and returns its handle.
;; The cylinder will  be centred at 0,0,0 and will have a radius of 1.
;; The segments value must be in the range 3-100 inclusive, although this is  only checked in debug mode. A common mistake is to leave debug mode off and  specify the parent parameter (usually an eight digit memory address) in the  place of the segments value. As the amount of polygons used to create a cylinder  is exponentially proportional to the segments value, this will result in Blitz  trying to create a cylinder with unimaginable amounts of polygons! Depending  on how unlucky you are, your computer may then crash.
;; Example segments values (solid=true):
;; 3: 8 polygons - a prism
;; 8: 28 polygons - bare minimum amount of polygons for a cylinder
;; 16: 60 polygons - smooth cylinder at medium-high distances
;; 32: 124 polygons - smooth cylinder at close distances
;; The optional parent parameter allow you to specify a parent entity for the  cylinder so that when the parent is moved the child cylinder will move with  it. However, this relationship is one way; applying movement commands to the  child will not affect the parent.
;; Specifying a parent entity will still result in the cylinder being created  at position 0,0,0 rather than at the parent entity's position.
;; See also: CreateCube, CreateSphere, CreateCone.
;;param segments (optional) - cylinder detail. Defaults to 8.
;;param solid (optional) - true for a cylinder, false for a tube. Defaults to true.
;;param parent (optional) - parent entity of cylinder
function CreateCylinder ( [segments][,solid][,parent] )
; CreateCylinder Example  
; ----------------------  
Graphics3D 640,480  
SetBuffer BackBuffer()  
camera=CreateCamera()  
light=CreateLight()  
RotateEntity light,90,0,0  
; Create cylinder  
cylinder=CreateCylinder()  
PositionEntity cylinder,0,0,5  
While Not KeyDown( 1 )  
RenderWorld  
Flip  
Wend  
End  
end function
;; Creates a light.
;; Lights work by affecting the colour of all vertices within  the light's range. You need at to create at least one light if you wish to use 3D graphics otherwise everything will appear flat.
;; The optional type parameter allows you to specify the type of light you wish to create. A value of 1 creates a directional light. This works similar to a  sun shining on a house. All walls facing a certain direction are lit the same.  How much they are lit by depends on the angle of the light reaching them.  Directional lights have infinite 'position' and infinite range.
;; A value of 2 creates a point (or omni) light. This works a little bit like a light bulb  in a house, starting from a central point and gradually fading outwards.
;; A value of 3 creates a spot light. This is a cone of light. This works similar  to shining a torch in a house. It starts with an inner angle of light, and then  extends towards an outer angle of light.  You can adjust the angles of a 'spot' light with the LightConeAngles command.
;; The optional parent parameter allow you to specify a parent entity for the  light so that when the parent is moved the child light will move with it. However,  this relationship is one way; applying movement commands to the child will not affect the parent.
;; Specifying a parent entity will still result in the light being created at  position 0,0,0 rather than at the parent entity's position.
;; Other notes:
;; There is a DirectX limit on the number of lights available per scene - this is either 8 or 16 depending on your video card, but you should always assume 8.
;; Also, you should remember that each light added effects the rendering speed.
;; Lights do not cast shadows, like they do in real life.
;; Most games get around these issues by the use of a pre-calculated 'baked' lightmap texture for the static geometry in the scene.
;; Other lighting techniques include: adjusting vertex colors, dynamic shadows, and/or dynamic lights (ie. moving the lights around in the scene as they are needed).
;; See also: <a class=small href=LightRange.htm>LightRange</a>, <a class=small href=LightColor.htm>LightColor</a>, <a class=small href=LightConeAngles.htm>LightConeAngles</a>, <a class=small href=AmbientLight.htm>AmbientLight</a>.
;;param type (optional) - type of light
;;param 1: directional (default)
;;param 2: point
;;param 3: spot
;;param parent (optional) - parent entity of light
function CreateLight ( [type][,parent] )
Graphics3D 640,480  
camera = CreateCamera()  
MoveEntity camera,0,0,-3  
ball = CreateSphere()  
lite = CreateLight(1) ; change this to 2 or 3 to see different lights  
MoveEntity lite,5,0,0  
PointEntity lite,ball ; make sure light is pointing at ball  
While Not KeyDown(1)  
RenderWorld:Flip  
Wend  
End  
end function
;; Creates a listener entity and returns its handle. Currently, only a single  listener is supported.
;;param parent - parent entity of listener. A parent entity, typically a camera,  must be specified to 'carry' the listener around.
;;param rolloff_factor# (optional) - the rate at which volume diminishes with distance.  Defaults to 1.
;;param doppler_scale# (optional) - the severity of the doppler effect. Defaults to  1.
;;param distance_scale# (optional) - artificially scales distances. Defaults to 1.
function CreateListener ( parent[,rolloff_factor#][,doppler_scale#][,distance_scale#] )
; CreateListener Example  
; ----------------------  
Graphics3D 640,480  
SetBuffer BackBuffer()  
camera=CreateCamera()  
PositionEntity camera,0,1,-10  
light=CreateLight()  
RotateEntity light,90,0,0  
plane=CreatePlane()  
ground_tex=LoadTexture("media/Chorme-2.bmp")  
EntityTexture plane,ground_tex  
cube=CreateCube()  
cube_tex=LoadTexture("media/b3dlogo.jpg")  
EntityTexture cube,cube_tex  
PositionEntity cube,0,1,0  
microphone=CreateListener(camera) ; Create listener, make it child of camera  
sound=Load3DSound("media/ufo.wav") ; Load 3D sound  
While Not KeyDown(1)  
If KeyDown(205)=True Then TurnEntity camera,0,-1,0  
If KeyDown(203)=True Then TurnEntity camera,0,1,0  
If KeyDown(208)=True Then MoveEntity camera,0,0,-0.05  
If KeyDown(200)=True Then MoveEntity camera,0,0,0.05  
; If left mouse button hit then emit sound from cube  
If MouseHit(1) = True Then EmitSound(sound,cube)  
RenderWorld  
Text 0,0,"Use cursor keys to move about"  
Text 0,20,"Press left mouse button to make a sound be emitted from the cube"  
Flip  
Wend  
End  
end function
;; Create a 'blank' mesh entity and returns its handle.
;; When a mesh is first created it has no surfaces, vertices or triangles associated with it.
;; To add geometry to this mesh, you will need to:
;; CreateSurface() ; To make a surface
;; AddVertex ; You will need to add at least 3 to make a Triangle
;; AddTriangle ; This will add a triangle by connecting the Vertices (points) you added to the mesh.
;;param parent (optional) - This optional parameter allows you to specify another entity which will act as the parent to this mesh.
function CreateMesh([parent])
; CreateMesh Example  
; ------------------  
; In this example, we will create a custom mesh. This custom mesh will be in the shape of a ramp.  
Graphics3D 640,480  
SetBuffer BackBuffer()  
camera=CreateCamera()  
light=CreateLight()  
RotateEntity light,45,0,0  
; Create blank mesh  
ramp=CreateMesh()  
; Create blank surface which is attached to mesh (surfaces must always be attached to a mesh)  
surf=CreateSurface(ramp)  
; Now we have our blank mesh and surface, we can start adding vertices to it, to form the shape of our  
; ramp.  
; Vertices are invisible 'points' in a 3D object that we can attach triangles too later.  
; To create a single triangle, you need three vertices, one for each corner.  
; However, you can share vertices between triangles, so you do not always need 3 new vertices per  
; triangle.  
; In the case of our ramp mesh, we will require 6 vertices, one for each corner  
v0=AddVertex(surf,0,0,0) ; bottom corner 1  
v1=AddVertex(surf,0,0,1) ; bottom corner 2  
v2=AddVertex(surf,4,0,1) ; bottom corner 3  
v3=AddVertex(surf,4,0,0) ; bottom corner 4  
v4=AddVertex(surf,0,2,0) ; top corner 1  
v5=AddVertex(surf,0,2,1) ; top corner 2  
; Having created our blank mesh and surface, and added our vertices to form the shape of the mesh, we  
; now need to add triangles to it in order to make it solid and visible to the user. We create  
; triangles simply by connecting vertices up, very much like a 3D dot-to-dot.  
; When adding triangles, we need to remember that they are only one sided, and the side they are  
; visible from is determined by the order in which we specify the vertices when using AddTriangle.  
; If the vertices, in the order that they are specified, are aligned in a clockwise fashion relative  
; to the camera then they will appear visible, otherwise they won't.  
; So, to make our ramp visible from the outside, we will be connecting all vertices in a clockwise  
; fashion, relative to the camera.  
t0=AddTriangle(surf,v0,v3,v2) ; bottom triangle 1  
t1=AddTriangle(surf,v0,v2,v1) ; bottom triangle 2  
t2=AddTriangle(surf,v0,v4,v3) ; front triangle  
t3=AddTriangle(surf,v1,v2,v5) ; back triangle  
t4=AddTriangle(surf,v0,v1,v5) ; side triangle 1  
t5=AddTriangle(surf,v0,v5,v4) ; side triangle 2  
t6=AddTriangle(surf,v2,v4,v5) ; top triangle 1  
t7=AddTriangle(surf,v2,v3,v4) ; top triangle 2  
; Now we will position our ramp in front of the camera so we can see it!  
PositionEntity ramp,0,-4,10  
; Enable wireframe mode so we can see structure of model more clearly  
WireFrame True  
; And a quick loop that renders the scene and displays the contents on the screen until we press esc  
While Not KeyDown(1)  
; Constantly turn our ramp entity to show it off a bit  
TurnEntity ramp,0,1,0  
RenderWorld  
Flip  
Wend  
; The end!  
End  
end function
;; Creates a mirror entity and returns its handle.
;; A mirror entity is basically  a flat, infinite 'ground'. This ground is invisible, except it vertically flips  anything above it, below it and vice versa. It is useful for games where you  want have the effect of a shiny floor showing a reflection. For a true shiny  floor effect, try combining a mirror entity with a textured plane entity that  has an alpha level of 0.5.
;; The optional parent parameter allows you to specify a parent  entity for the mirror so that when the parent is moved the child mirror will  move with it. However, this relationship is one way; applying movement commands  to the child will not affect the parent.
;; Specifying a parent entity will still result in the mirror being created  at position 0,0,0 rather than at the parent entity's position.
;; See also: CreatePlane.
;;param parent - parent entity of mirror
function CreateMirror ( [parent] )
; CreateMirror Example  
; --------------------  
Graphics3D 640,480  
SetBuffer BackBuffer()  
camera=CreateCamera()  
PositionEntity camera,0,1,-5  
light=CreateLight()  
RotateEntity light,90,0,0  
; Create cone  
cone=CreateCone(32)  
PositionEntity cone,0,2,0  
; Create plane  
plane=CreatePlane()  
grass_tex=LoadTexture( "media/chorme-2.bmp" )  
EntityTexture plane,grass_tex  
EntityAlpha plane,0.5  
; Create mirror  
mirror=CreateMirror()  
While Not KeyDown( 1 )  
If KeyDown( 203 )=True Then MoveEntity cone,-0.1,0,0  
If KeyDown( 205 )=True Then MoveEntity cone,0.1,0,0  
If KeyDown( 208 )=True Then MoveEntity cone,0,-0.1,0  
If KeyDown( 200 )=True Then MoveEntity cone,0,0.1,0  
If KeyDown( 44 )=True Then MoveEntity cone,0,0,-0.1  
If KeyDown( 30 )=True Then MoveEntity cone,0,0,0.1  
RenderWorld  
Text 0,0,"Use cursor/A/Z keys to move cone above infinite mirror"  
Flip  
Wend  
End  
end function
;; Creates a pivot entity.
;; A pivot entity is an invisible  point in 3D space that's main use is to act as a parent entity to other entities.  The pivot can then be used to control lots of entities at once, or act as new  centre of rotation for other entities.
;; To enforce this relationship; use EntityParent  or make use of the optional parent entity parameter available with all entity  load/creation commands.
;; Indeed, this parameter is also available with the CreatePivot command if  you wish for the pivot to have a parent entity itself.
;;param parent (optional) - parent entity of pivot
function CreatePivot ( [parent] )
; CreatePivot Example  
; -------------------  
Graphics3D 640,480  
SetBuffer BackBuffer()  
camera=CreateCamera()  
PositionEntity camera,0,0,-10  
light=CreateLight()  
RotateEntity light,90,0,0  
; Create pivot. This is invisible.  
pivot=CreatePivot()  
; Create planet (i.e. a sphere). Make the pivot the parent of the planet.  
planet=CreateSphere(16,pivot)  
; Position planet so that it is offset from the pivot  
PositionEntity planet,5,0,0  
While Not KeyDown(1)  
; Turn pivot, thus making planet spin around it  
TurnEntity pivot,0,1,0  
RenderWorld  
Flip  
Wend  
End  
end function
;; Creates a plane entity and returns its handle.
;; A plane entity is basically  a flat, infinite 'ground'. It is useful for outdoor games where you never want  the player to see/reach the edge of the game world.
;; The optional sub_divs parameter determines how many sub divisions of polygons  the plane will have. Although a plane is flat and so adding extra polygons will  not make it smoother, adding more polygons will allow more vertices to be lit  for more detailed lighting effects.
;; The optional parent parameter allows you to specify a parent  entity for the plane so that when the parent is moved the child plane will move  with it. However, this relationship is one way; applying movement commands to  the child will not affect the parent.
;; Specifying a parent entity will still result in the plane being created at  position 0,0,0 rather than at the parent entity's position.
;; See also: CreateMirror.
;;param sub_divs (optional) - sub divisions of plane. Should be in the range 1-16.  The default value is 1.
;;param parent (optional) - parent entity of plane
function CreatePlane ( [sub_divs][,parent] )
; CreatePlane Example  
; -------------------  
Graphics3D 640,480  
SetBuffer BackBuffer()  
camera=CreateCamera()  
PositionEntity camera,0,1,0  
light=CreateLight()  
RotateEntity light,90,0,0  
; Create plane  
plane=CreatePlane()  
grass_tex=LoadTexture( "media/mossyground.bmp" )  
EntityTexture plane,grass_tex  
While Not KeyDown( 1 )  
If KeyDown( 205 )=True Then TurnEntity camera,0,-1,0  
If KeyDown( 203 )=True Then TurnEntity camera,0,1,0  
If KeyDown( 208 )=True Then MoveEntity camera,0,0,-0.05  
If KeyDown( 200 )=True Then MoveEntity camera,0,0,0.05  
RenderWorld  
Text 0,0,"Use cursor keys to move about the infinite plane"  
Flip  
Wend  
End  
end function
;; Creates a sphere mesh/entity and returns its handle.
;; The sphere will  be centred  at 0,0,0 and will have a radius of 1.
;; The segments value must be in the range 2-100 inclusive, although this is  only checked in debug mode. A common mistake  is to leave debug mode off and specify the parent parameter  (usually an eight digit memory address) in the place of the segments value.  As the amount of polygons used to create a sphere is exponentially  proportional to the segments value, this will result in Blitz trying to create a sphere  with unimaginable amounts of polygons! Depending on how unlucky you are,  your computer will then crash.
;; Example segments values:
;; 8: 224 polygons - bare minimum amount of polygons for a sphere
;; 16: 960 polygons - smooth looking sphere at medium-high distances
;; 32: 3968 polygons - smooth sphere at close distances
;; The  optional parent parameter allow you to specify a parent entity for the  sphere so that when the parent is moved the child sphere will move with it.  However, this relationship is one way; applying movement commands to the  child will not affect the parent.
;; Specifying a parent entity will still result in the sphere being created  at position 0,0,0 rather than at the parent entity's position.
;; See also: CreateCube,  CreateCylinder, CreateCone.
;;param segments (optional) - sphere detail. Defaults to 8.
;;param parent (optional) - parent entity of sphere
function CreateSphere ( [segments][,parent] )
; CreateSphere Example  
; --------------------  
Graphics3D 640,480  
SetBuffer BackBuffer()  
camera=CreateCamera()  
light=CreateLight()  
RotateEntity light,90,0,0  
; Create sphere  
sphere=CreateSphere()  
PositionEntity sphere,0,0,5  
While Not KeyDown( 1 )  
RenderWorld  
Flip  
Wend  
End  
end function
;; Creates a sprite entity and returns its handle.  Sprites are simple flat (usually textured) rectangles made from two triangles.  Unlike other entity objects they don't actually have a mesh that can be manipulated.
;; The sprite will be positioned  at 0,0,0 and extend from 1,-1 to +1,+1.
;; Sprites have two real strengths. The first is that they consist of only two  polygons; meaning you can use many of them at once. This makes them ideal for  particle effects and 2D-using-3D games where you want lots of sprites on-screen  at once.
;; Secondly, sprites can be assigned a view mode using SpriteViewMode. By default this view mode is  set to 1, which means the sprite will always face the camera. So no matter what  the orientation of the camera is relative to the sprite, you will never actually  notice that they are flat; by giving them a spherical texture, you can make  them appear to look no different than a normal sphere.
;; The optional parent parameter allow you to specify a parent entity for the  sprite so that when the parent is moved the child sprite will move with it.  However, this relationship is one way; applying movement commands to the child  will not affect the parent.
;; Specifying a parent entity will still result in the sprite being created  at position 0,0,0 rather than at the parent entity's position.
;; Note:  Sprites have their own commands for rotation and scaling.
;; See also: <a class=small href=LoadSprite.htm>LoadSprite</a>, <a class=small href=RotateSprite.htm>RotateSprite</a>, <a class=small href=ScaleSprite.htm>ScaleSprite</a>, <a class=small href=HandleSprite.htm>HandleSprite</a>, <a class=small href=SpriteViewMode.htm>SpriteViewMode</a>, <a class=small href=PositionEntity.htm>PositionEntity</a>, <a class=small href=MoveEntity.htm>MoveEntity</a>, <a class=small href=TranslateEntity.htm>TranslateEntity</a>, <a class=small href=EntityAlpha.htm>EntityAlpha</a>, <a class=small href=FreeEntity.htm>FreeEntity</a>.
;;param parent (optional) - parent entity of sprite
function CreateSprite ( [parent] )
Graphics3D 640,480  
cam = CreateCamera()  
MoveEntity cam,0,0,-5  
sp = CreateSprite()  
RotateSprite sp,20  
RenderWorld:Flip  
WaitKey  
End  
end function
;; Creates a surface attached to a mesh and returns the surface's handle.
;; Surfaces are sections of mesh which are then used to attach triangles to. You  must have at least one surface per mesh in order to create a visible mesh, however  you can use as many as you like. Splitting a mesh up into lots of sections allows  you to affect those sections individually, which can be a lot more useful than  if all the surfaces are combined into just one.
;;param mesh - mesh handle
;;param brush (optional) - brush handle
function CreateSurface ( mesh[,brush] )
Graphics3D 640,480  
SetBuffer BackBuffer()  
mesh = CreateMesh()  
surf = CreateSurface(mesh)  
v0 = AddVertex (surf, -5,-5,0,  0  ,0)  
v1 = AddVertex (surf,  5,-5,0,  1  ,0)  
v2 = AddVertex (surf,  0, 5,0,  0.5,1)  
tri = AddTriangle (surf,v0,v2,v1)  
cam = CreateCamera()  
MoveEntity cam, 0,0,-7  
RenderWorld  
Flip  
WaitKey  
End  
end function
;; Creates a terrain entity and returns its handle.
;; The terrain  extends from 0,0,0 to grid_size,1,grid_size.
;; A terrain is a special type of polygon object that uses real-time level of  detail (LOD) to display landscapes which should theoretically consist of over  a million polygons with only a few thousand. The way it does this is by constantly  rearranging a certain amount of polygons to display high levels of detail close  to the viewer and low levels further away.
;; This constant rearrangement of polygons is occasionally noticeable however,  and is a well-known side-effect of all LOD landscapes. This 'pop-in' effect  can be reduced in lots of ways though, as the other terrain help files will  go on to explain.
;; The optional parent parameter allows you to specify a parent entity for the  terrain so that when the parent is moved the child terrain will move with it.  However, this relationship is one way; applying movement commands to the child  will not affect the parent.
;; Specifying a parent entity will still result in the terrain being created  at position 0,0,0 rather than at the parent entity's position.
;; See also: LoadTerrain.
;;param grid_size - no of grid squares along each side of terrain, and must be a  power of 2 value, e.g. 32, 64, 128, 256, 512, 1024.
;;param parent (optional) - parent entity of terrain
function CreateTerrain ( grid_size[,parent] )
; CreateTerrain Example  
; ---------------------  
Graphics3D 640,480  
SetBuffer BackBuffer()  
camera=CreateCamera()  
PositionEntity camera,0,1,0  
light=CreateLight()  
RotateEntity light,90,0,0  
; Create terrain  
terrain=CreateTerrain(128)  
; Texture terrain  
grass_tex=LoadTexture( "media/mossyground.bmp" )  
EntityTexture terrain,grass_tex  
While Not KeyDown( 1 )  
If KeyDown( 205 )=True Then TurnEntity camera,0,-1,0  
If KeyDown( 203 )=True Then TurnEntity camera,0,1,0  
If KeyDown( 208 )=True Then MoveEntity camera,0,0,-0.05  
If KeyDown( 200 )=True Then MoveEntity camera,0,0,0.05  
RenderWorld  
Text 0,0,"Use cursor keys to move about the terrain"  
Flip  
Wend  
End  
end function
;; Creates a texture and returns its handle.
;; Width and height are the size  of the texture. Note that the actual texture size may be different from the  width and height requested, as different types of 3D hardware support different  sizes of texture.
;; The optional flags parameter allows you to apply certain effects to the texture.  Flags can be added to combine two or more effects, e.g. 3 (1+2) = texture with  color and alpha maps.
;; Here some more detailed descriptions of the flags:
;; 1: Color - colour map, what you see is what you get.
;; 2: Alpha - alpha map. If an image contains an alpha map, this will be used to  make certain areas of the texture transparent. Otherwise, the colour map will  be used as an alpha map. With alpha maps, the dark areas always equal high-transparency,  light areas equal low-transparency.
;; 4: Masked - all areas of a texture coloured 0,0,0 will not be drawn to the screen.
;; 8: Mipmapped - low detail versions of the texture will be used at high distance.  Results in a smooth, blurred look.
;; 16: Clamp u - Any part of a texture that lies outsides the U coordinates of 0-1 will not be drawn. Prevents texture-wrapping.
;; 32: Clamp v - Any part of a texture that lies outsides the v coordinates of 0-1 will not be drawn. Prevents texture-wrapping.
;; 64: Spherical environment map - a form of environment mapping. This works by taking a single image, and then applying it to a 3D mesh in such a way that the image appears to be reflected. When used with a texture that contains light sources, it can give some meshes such as a teapot a shiny appearance.
;; 128: Cubic environment map - a form of environment mapping. Cube mapping is similar to spherical mapping, except it uses six images each representing a particular 'face' of an imaginary cube, to give the appearance of an image that perfectly reflects its surroundings.
;; When creating cubic environment maps with the CreateTexture command, cubemap textures *must* be square 'power of 2' sizes. See the <a class=small href=../3d_commands/SetCubeFace.htm>SetCubeFace</a> command for information on how to then draw to the cubemap.
;; When loading cubic environments maps into Blitz using LoadTexture, all six images relating to the six faces of the cube must be contained within the one texture, and be laid out in a horizontal strip in the following order - left, forward, right, backward, up, down. The images comprising the cubemap must all be power of two sizes.
;; Please note that not some older graphics cards do not support cubic mapping. In order to find out if a user's graphics card can support it, use <a class=small href=../3d_commands/GfxDriverCaps3D.htm>GfxDriverCaps3D</a> .
;; 256: Store texture in vram. In some circumstances, this makes for much faster dynamic textures - ie. when using CopyRect between two textures. When drawing to cube maps in real-time, it is preferable to use this flag.
;; 512: Force the use of high color textures in low bit depth graphics modes. This is useful for when you are in 16-bit color mode, and wish to create/load textures with the alpha flag - it should give better results.
;; Once you have created a texture, use SetBuffer TextureBuffer to draw to it. However, to display 2D graphics on a texture, it is usually quicker to draw to an image and then copy it to the texturebuffer, and to display 3D graphics on a texture, your only option is to copy from the backbuffer to the texturebuffer.
;; See also: <a class=small href=LoadTexture.htm>LoadTexture</a>, <a class=small href=LoadAnimTexture.htm>LoadAnimTexture</a>.
;;param width - width of texture
;;param height - height of texture
;;param flags (optional) - texture flag:
;;param 1: Color (default)
;;param 2: Alpha
;;param 4: Masked
;;param 8: Mipmapped
;;param 16: Clamp U
;;param 32: Clamp V
;;param 64: Spherical environment map
;;param 128: Cubic environment map
;;param 256: Store texture in vram
;;param 512: Force the use of high color textures
;;param frames (optional) - no of frames texture will have. Defaults to 1.
function CreateTexture ( width,height[,flags][,frames] )
; CreateTexture Example  
; ---------------------  
Graphics3D 640,480  
SetBuffer BackBuffer()  
camera=CreateCamera()  
light=CreateLight()  
RotateEntity light,90,0,0  
cube=CreateCube()  
PositionEntity cube,0,0,5  
; Create texture of size 256x256  
tex=CreateTexture( 256,256 )  
; Set buffer - texture buffer  
SetBuffer TextureBuffer( tex )  
; Clear texture buffer with background white color  
ClsColor 255,255,255  
Cls  
; Draw text on texture  
font=LoadFont( "arial",24 )  
SetFont font  
Color 0,0,0  
Text 0,0,"This texture"  
Text 0,40,"was created using" : Color 0,0,255  
Text 0,80,"CreateTexture()" : Color 0,0,0  
Text 0,120,"and drawn to using" : Color 0,0,255  
Text 0,160,"SetBuffer TextureBuffer()"  
; Texture cube with texture  
EntityTexture cube,tex  
; Set buffer - backbuffer  
SetBuffer BackBuffer()  
While Not KeyDown( 1 )  
pitch#=0  
yaw#=0  
roll#=0  
If KeyDown( 208 )=True Then pitch#=-1  
If KeyDown( 200 )=True Then pitch#=1  
If KeyDown( 203 )=True Then yaw#=-1  
If KeyDown( 205 )=True Then yaw#=1  
If KeyDown( 45 )=True Then roll#=-1  
If KeyDown( 44 )=True Then roll#=1  
TurnEntity cube,pitch#,yaw#,roll#  
RenderWorld  
Flip  
Wend  
End  
end function
;; Returns the pitch angle, that src_entity should be rotated by in order to face dest_entity.
;; This command can be used to be point one entity at another, rotating on the x axis only.
;; See also: <a class=small href=DeltaYaw.htm>DeltaYaw</a>.
;; Click <a href=http://www.blitzbasic.co.nz/b3ddocs/command.php?name=DeltaPitch&ref=comments target=_blank>here</a> to view the latest version of this page online</body>
;; </html>
;;param src_entity - source entity handle
;;param dest_entity - destination entity handle
function DeltaPitch# ( src_entity,dest_entity )
end function
;; Returns the yaw angle, that src_entity should be rotated by in order to face dest_entity.
;; This command can be used to be point one entity at another, rotating on the y axis only.
;; See also: <a class=small href=DeltaPitch.htm>DeltaPitch</a>.
;;param src_entity - source entity handle
;;param dest_entity - destination entity handle
function DeltaYaw# ( src_entity,dest_entity )
SetBuffer BackBuffer()  
camera=CreateCamera()  
; Make camera orthagraphic for flat, 2D view  
CameraProjMode camera,2  
; Position and rotate camera so we have overhead (top-down) view  
PositionEntity camera,0,5,0  
RotateEntity camera,90,0,0  
; Create red cone (the arrow)  
arrow=CreateCone()  
RotateMesh arrow,90,180,0  
ScaleMesh arrow,.1,.1,.2  
EntityColor arrow,255,0,0  
; Create blue sphere (the spot)  
spot=CreateSphere()  
ScaleMesh spot,.1,.1,.1  
EntityColor spot,0,0,255  
While Not KeyDown(1)  
; If w,a,s,d pressed then move spot  
If KeyDown(17)=True Then MoveEntity spot,0,0,0.01 ; w - up  
If KeyDown(30)=True Then MoveEntity spot,-0.01,0,0 ; a - left  
If KeyDown(31)=True Then MoveEntity spot,0,0,-0.01 ; s - down  
If KeyDown(32)=True Then MoveEntity spot,0.01,0,0 ; d - right  
; Rotate arrow using delta yaw value. Arrow will point at spot.  
RotateEntity arrow,0,DeltaYaw#(spot,arrow),0  
RenderWorld  
Text 0,0,"Note: Camera view is overhead. The arrow will y-rotate using DeltaYaw value."  
Text 0,20,"Use w,a,s,d to move spot."  
Text 0,40,"Delta yaw: "+DeltaYaw#(spot,arrow)  
Flip  
Wend  
End  
end function
;; Enables or disables hardware dithering.
;; Hardware dithering is useful when running games in 16-bit colour mode. Due to  the fact that 16-bit mode offers less colours than the human eye can detect,  separate bands of colour are often noticeable on shaded objects. However, hardware  dithering will dither the entire screen to give the impression that more colours  are being used than is actually the case.
;; Due to the fact that 24-bit and 32-bit offer more colours than the human eye  can detect, hardware dithering is made pretty much redundant in those modes.
;;param enable - True to enable dithering, False to disable.
;;param The default Dither  mode is True.
function Dither enable
; Dither Example  
; --------------  
Graphics3D 640,480,16  
SetBuffer BackBuffer()  
camera=CreateCamera()  
light=CreateLight()  
; Rotate light so that it creates maximum shading effect on sphere  
RotateEntity light,90,0,0  
sphere=CreateSphere( 32 )  
PositionEntity sphere,0,0,2  
While Not KeyDown( 1 )  
; Toggle dither enable value between true and false when spacebar is pressed  
If KeyHit( 57 )=True Then enable=1-enable  
; Enable/disable hardware dithering  
Dither enable  
RenderWorld  
Text 0,0,"Press spacebar to toggle between Dither True/False"  
If enable=True Then Text 0,20,"Dither True" Else Text 0,20,"Dither False"  
Flip  
Wend  
End  
end function
;; Emits a sound attached to the specified entity and returns a sound channel.
;; The sound must have been loaded using Load3DSound for 3D effects.
;;param sound - sound handle
;;param entity - entity handle
function EmitSound( sound,entity )
; EmitSound Example  
; -----------------  
Graphics3D 640,480  
SetBuffer BackBuffer()  
camera=CreateCamera()  
PositionEntity camera,0,1,-10  
light=CreateLight()  
RotateEntity light,90,0,0  
plane=CreatePlane()  
ground_tex=LoadTexture("media/Chorme-2.bmp")  
EntityTexture plane,ground_tex  
cube=CreateCube()  
cube_tex=LoadTexture("media/b3dlogo.jpg")  
EntityTexture cube,cube_tex  
PositionEntity cube,0,1,0  
microphone=CreateListener(camera) ; Create listener, make it child of camera  
sound=Load3DSound("media/ufo.wav") ; Load 3D sound  
While Not KeyDown(1)  
If KeyDown(205)=True Then TurnEntity camera,0,-1,0  
If KeyDown(203)=True Then TurnEntity camera,0,1,0  
If KeyDown(208)=True Then MoveEntity camera,0,0,-0.05  
If KeyDown(200)=True Then MoveEntity camera,0,0,0.05  
; If left mouse button hit then emit sound from cube  
If MouseHit(1) = True Then EmitSound(sound,cube)  
RenderWorld  
Text 0,0,"Use cursor keys to move about"  
Text 0,20,"Press left mouse button to make a sound be emitted from the cube"  
Flip  
Wend  
End  
end function
;; Sets the entity alpha level of an entity.
;; The alpha# value should be in a floating point value in the range 0-1. The default entity alpha setting is 1.
;; The alpha level is how transparent an entity is. A value of 1 will mean the entity is opaque. A value of 0 will mean the entity is completely transparent, i.e. invisible. Values between 0 and 1 will cause varying amount of transparency. This is useful for imitating the look of objects such as glass and other translucent materials.
;; An EntityAlpha value of 0 is especially useful as Blitz3D will not render entities with such a value, but will still involve the entities in collision tests. This is unlike HideEntity, which doesn't involve entities in collisions.
;;param Entity - entity handle
;;param Alpha# - alpha level of entity
function EntityAlpha entity,alpha#
; EntityAlpha Example  
; -------------------  
Graphics3D 640,480  
SetBuffer BackBuffer()  
camera=CreateCamera()  
light=CreateLight()  
RotateEntity light,90,0,0  
cube=CreateCube()  
Back=CreateCube()  
PositionEntity cube,0,0,5  
PositionEntity back,0,0,15  
ScaleEntity Back,10,2,1  
EntityColor back,255,0,0  
; Set initial entity color values  
Alpha#=1  
While Not KeyDown( 1 )  
; Change alpha value depending on key pressed  
If alpha#<0.01 Then alpha# = 0  
If alpha#>1 Then alpha# = 1  
If KeyDown( 2 )=True And Alpha#>0 Then Alpha#=Alpha#-0.01  
If KeyDown( 3 )=True And alpha#<1 Then Alpha#=Alpha#+0.01  
; Set entity alpha value  
EntityAlpha cube,Alpha#  
TurnEntity cube,0.1,0.1,0.1  
TurnEntity back,1,0,0  
RenderWorld  
Text 0,0,"Press keys 1-2 to change EntityAlpha"  
Text 0,20,"Entity Alpha: "+Alpha  
Flip  
Wend  
End  
end function
;;param <p><b><a target="_top" href="../index.htm">Index</a></b></p>
;;param </body>
;;param </html>
function EntityAnimating ( entity )
end function
;;param <p><b><a target="_top" href="../index.htm">Index</a></b></p>
;;param </body>
;;param </html>
function EntityAnimTime# ( entity )
end function
;; Enables auto fading for an entity. This will cause an entity's alpha level  to be adjusted at distances between near and far to create a 'fade-in' effect.
;; Click <a href=http://www.blitzbasic.co.nz/b3ddocs/command.php?name=EntityAutoFade&ref=comments target=_blank>here</a> to view the latest version of this page online</body>
;; </html>
;;param entity - entity handle
;;param near# - distance in front of the camera at which entity's will start being faded
;;param far# - distance in front of the camera at which entity's will stop being faded  (and will be invisible)
function EntityAutoFade entity,near#,far#
end function
;; Sets the blending mode of an entity. This blending mode determines the way in which the new RGBA of the pixel being rendered is combined with the RGB of the background.
;; To calculate the new RGBA of the pixel being rendered, the texture RGBA for the pixel (see <a class=small href=../3d_commands/TextureBlend.htm>TextureBlend</a> for more information on how the texture RGBA is calculated) is taken, its alpha component multiplied by the entities/brushes (where applicable) alpha value and its color compentent multiplied by the entities/brushes colour. This is the RGBA which will then be blended into the background pixel, and how this is done depends on the EntityBlend value.
;; Alpha:
;; This blends the pixels according to the Alpha value. This is rougly done to the formula:
;; Rr = ( An * Rn ) + ( ( 1.0 - An ) * Ro )
;; Gr = ( An * Gn ) + ( ( 1.0 - An ) * Go )
;; Br = ( An * Bn ) + ( ( 1.0 - An ) * Bo )
;; Where R = Red, G = Green, B = Blue, n = new pixel colour values, r = resultant colour values, o = old pixel colour values.
;; Alpha blending is the default blending mode and is used with most world objects.
;; Multiply:
;; This blend mode will darken the underlying pixels. If you think of each RGB value as being on a scale from 0% to 100%, where 0 = 0% and 255 = 100%, the multiply blend mode will multiply the red, green and blue values individually together in order to get the new RGB value, roughly according to:
;; Rr = ( ( Rn / 255.0 ) * ( Ro / 255.0 ) ) * 255.0
;; Gr = ( ( Gn / 255.0 ) * ( Go / 255.0 ) ) * 255.0
;; Br = ( ( Bn / 255.0 ) * ( Bo / 255.0 ) ) * 255.0
;; The alpha value has no effect with multiplicative blending. Blending a RGB value of 255, 255, 255 will make no difference, while an RGB value of 128, 128, 128 will darken the pixels by a factor of 2 and an RGB value of 0, 0, 0 will completely blacken out the resultant pixels. An RGB value of 0, 255, 255 will remove the red component of the underlying pixel while leaving the other color values
;; untouched.
;; Multiply blending is most often used for lightmaps, shadows or anything else that needs to 'darken' the resultant pixels.
;; Add:
;; Additive blending will add the new color values to the old, roughly according to:
;; Rr = ( Rn * An ) + Ro
;; Gr = ( Gn * An ) + Go
;; Br = ( Bn * An ) + Bo
;; The resultant RGB values are clipped out at 255, meaning that multiple additive effects can quickly cause visible banding from smooth gradients.
;; Additive blending is extremely useful for effects such as laser shots and fire.
;; See also: <a class=small href=TextureBlend.htm>TextureBlend</a>, <a class=small href=EntityAlpha.htm>EntityAlpha</a>.
;;param Entity - Entity handle
;;param Blend - Blend mode of the entity.
;;param 1: Alpha (default)
;;param 2: Multiply
;;param 3: Add
function EntityBlend Entity, Blend
Graphics3D 640,480  
SetBuffer BackBuffer()  
SeedRnd MilliSecs()  
; create camera  
camera=CreateCamera()  
CameraClsColor camera,160,160,160  
PositionEntity camera,0,0,-30  
middle=CreatePivot()  
EntityParent camera,middle  
; create add texture - white cirlce on a black background  
For n=0 To 50  
Color 5+(n*5),5+(n*5),5+(n*5)  
Oval 10+n,10+n,236-(n*2),236-(n*2),1  
Next  
blob_tex=CreateTexture(256,256)  
blob=CreateImage(256,256)  
GrabImage blob,0,0  
CopyRect 0,0,256,256,0,0,ImageBuffer(blob),TextureBuffer(blob_tex)  
FreeImage blob  
max_blobs=100  
; create blobs using add blend mode  
Dim blobs(max_blobs) ; blob sprites  
Dim xyblobs#(max_blobs,2) ; blob vector  
For n=0 To max_blobs  
blobs(n)=CreateSprite()  
EntityFX blobs(n),1  
EntityBlend blobs(n),3 ;set blend mode to add  
EntityTexture blobs(n),blob_tex  
xyblobs(n,0)=Rnd(-.1,.1)  
xyblobs(n,1)=Rnd(-.1,.1)  
xyblobs(n,2)=Rnd(-.1,.1)  
EntityColor blobs(n),Rand(0,255),Rand(0,255),Rand(0,255) ;give it a colour  
Next  
; create cube texture  
Color 255,255,255  
Rect 0,0,256,256,1  
For n=0 To 7  
If n=0 Then Color 0,0,0  
If n=1 Then Color 0,0,255  
If n=2 Then Color 0,255,0  
If n=3 Then Color 0,255,255  
If n=4 Then Color 255,0,0  
If n=5 Then Color 255,0,255  
If n=6 Then Color 255,255,0  
If n=7 Then Color 255,255,255  
Rect n*32,n*32,32,32,1  
Next  
Color 0,0,0  
For n=0 To 255 Step 32  
Line 0,n,255,n  
Line n,0,n,255  
Next  
cube_tex=CreateTexture(256,256)  
cube=CreateImage(256,256)  
GrabImage cube,0,0  
CopyRect 0,0,256,256,0,0,ImageBuffer(cube),TextureBuffer(cube_tex)  
FreeImage cube  
; create cube  
cube=CreateCube()  
ScaleEntity cube,11,11,11  
EntityTexture cube,cube_tex  
EntityFX cube,17 ;set fullbright and 2 sided textures  
EntityBlend cube,2 ;set multiply blend  
Repeat  
; move the blobs around  
For n=0 To max_blobs  
MoveEntity blobs(n),xyblobs(n,0),xyblobs(n,1),xyblobs(n,2)  
;bounce off sides  
If EntityX(blobs(n))<-10 Or EntityX(blobs(n))>10 Then xyblobs(n,0)=-xyblobs(n,0)  
If EntityY(blobs(n))<-10 Or EntityY(blobs(n))>10 Then xyblobs(n,1)=-xyblobs(n,1)  
If EntityZ(blobs(n))<-10 Or EntityZ(blobs(n))>10 Then xyblobs(n,2)=-xyblobs(n,2)  
Next  
; turn camera  
TurnEntity middle,.1,.2,.3  
UpdateWorld  
RenderWorld  
Flip  
Until KeyHit(1)  
end function
;; Sets the dimensions of an entity's collision box.
;; See also: <a class=small href=EntityRadius.htm>EntityRadius</a>, <a class=small href=Collisions.htm>Collisions</a>, <a class=small href=EntityType.htm>EntityType</a>.
;; Click <a href=http://www.blitzbasic.co.nz/b3ddocs/command.php?name=EntityBox&ref=comments target=_blank>here</a> to view the latest version of this page online</body>
;; </html>
;;param entity - entity handle#
;;param x# - x position of entity's collision box
;;param y# - y position of entity's collision box
;;param z# - z position of entity's collision box
;;param width# - width of entity's collision box
;;param height# - height of entity's collision box
;;param depth# - depth of entity's collision box
function EntityBox entity,x#,y#,z#,width#,height#,depth#
end function
;; Returns a string containing the class of the specified entity.
;; Possible return values are:
;; Pivot
;; Light
;; Camera
;; Mirror
;; Listener
;; Sprite
;; Terrain
;; Plane
;; Mesh
;; MD2
;; BSP
;; Note that the command will fail if a valid entity handle is not supplied, and will not just return an empty string.
;;param entity - a valid entity handle
function EntityClass$( entity )
; EntityClass Example  
; -------------------  
Graphics3D 640,480,16  
SetBuffer BackBuffer()  
SeedRnd MilliSecs()  
; Select a random number between 0 and 7 then create a certain class of entity depending on the number selected  
i=Rand(0,7)  
Select i  
Case 0 ent=CreatePivot()  
Case 1 ent=CreateLight()  
Case 2 ent=CreateCamera()  
Case 3 ent=CreateMirror()  
Case 4 ent=CreateSprite()  
Case 5 ent=CreateTerrain(32)  
Case 6 ent=CreatePlane()  
Case 7 ent=CreateMesh()  
;Case 8 ent=CreateListener(parent)  
;Case 9 ent=LoadMD2(md2_file$)  
;Case 10 ent=LoadBSP(bsp_file$)  
End Select  
; Get the class of the entity  
class$=EntityClass$(ent)  
; Output the class to the screen  
Text 0,0,"A "+class$+" was created."  
Text 0,20,"Press a key."  
WaitKey()  
End  
end function
;; Returns the handle of the entity of the specified type that collided with  the specified entity.
;; See also: <a class=small href=CollisionX.htm>CollisionX</a>, <a class=small href=CollisionY.htm>CollisionY</a>, <a class=small href=CollisionZ.htm>CollisionZ</a>, <a class=small href=CollisionNX.htm>CollisionNX</a>, <a class=small href=CollisionNY.htm>CollisionNY</a>, <a class=small href=CollisionNZ.htm>CollisionNZ</a>, <a class=small href=CountCollisions.htm>CountCollisions</a>, <a class=small href=EntityCollided.htm>EntityCollided</a>, <a class=small href=CollisionTime.htm>CollisionTime</a>, <a class=small href=CollisionEntity.htm>CollisionEntity</a>, <a class=small href=CollisionSurface.htm>CollisionSurface</a>, <a class=small href=CollisionTriangle.htm>CollisionTriangle</a>.
;; Click <a href=http://www.blitzbasic.co.nz/b3ddocs/command.php?name=EntityCollided&ref=comments target=_blank>here</a> to view the latest version of this page online</body>
;; </html>
;;param entity - entity handle
;;param type - type of entity
function EntityCollided ( entity,type )
end function
;; Sets the color of an entity.
;; The Red, Green and Blue values should be in the range 0-255 with 0 being darkest and 255 brightest. The default entity color is 255,255,255 (White).
;;param entity - entity handle
;;param Red# - red value of entity
;;param Green# - green value of entity
;;param Blue# - blue value of entity
function EntityColor entity,red#,green#,blue#
; EntityColor Example  
; -------------------  
Graphics3D 640,480  
SetBuffer BackBuffer()  
camera=CreateCamera()  
light=CreateLight()  
RotateEntity light,90,0,0  
cube=CreateCube()  
PositionEntity cube,0,0,5  
; Set initial entity color values  
red#=255  
green#=255  
blue#=255  
While Not KeyDown( 1 )  
; Change red, green, blue values depending on key pressed  
If KeyDown( 2 )=True And red#>0 Then red#=red#-1  
If KeyDown( 3 )=True And red#<255 Then red#=red#+1  
If KeyDown( 4 )=True And green#>0 Then green#=green#-1  
If KeyDown( 5 )=True And green#<255 Then green#=green#+1  
If KeyDown( 6 )=True And blue#>0 Then blue#=blue#-1  
If KeyDown( 7 )=True And blue#<255 Then blue#=blue#+1  
; Set entity color using red, green, blue values  
EntityColor cube,red#,green#,blue#  
TurnEntity cube,0.1,0.1,0.1  
RenderWorld  
Text 0,0,"Press keys 1-6 to change EntityColor red#,green#,blue# values  
Text 0,20,"Entity Red: "+red#  
Text 0,40,"Entity Green: "+green#  
Text 0,60,"Entity Blue: "+blue#  
Flip  
Wend  
End  
end function
;; Returns the distance between src_entity and dest_entity.
;; Click <a href=http://www.blitzbasic.co.nz/b3ddocs/command.php?name=EntityDistance&ref=comments target=_blank>here</a> to view the latest version of this page online</body>
;; </html>
;;param src_entity - source entity handle
;;param dest_entity - destination entity handle
function EntityDistance# ( src_entity,dest_entity)
end function
;; Sets miscellaneous effects for an entity.
;; Flags can be added to combine  two or more effects. For example, specifying a flag of 3 (1+2) will result in  a full-bright and vertex-coloured brush.
;; Flag 32, to force alpha-blending, must be used in order to enable vertex alpha (see VertexColor).
;; See also: <a class=small href=VertexColor.htm>VertexColor</a>.
;; Click <a href=http://www.blitzbasic.co.nz/b3ddocs/command.php?name=EntityFX&ref=comments target=_blank>here</a> to view the latest version of this page online</body>
;; </html>
;;param entity - entity handle
;;param fx -
;;param 0: nothing (default)
;;param 1: full-bright
;;param 2: use vertex colors instead of brush color
;;param 4: flatshaded
;;param 8: disable fog
;;param 16: disable backface culling
;;param 32: force alpha-blending
function EntityFX entity,fx
end function
;; Returns true if the specified entity is visible to the specified camera.
;; If the entity is a mesh, its bounding box will be checked for visibility.
;; For all other types of entities, only their centre position will be checked.
;;param entity - entity handle
;;param camera - camera handle
function EntityInView ( entity,camera )
; EntityInView Example  
; --------------------  
Graphics3D 640,480  
SetBuffer BackBuffer()  
camera=CreateCamera()  
PositionEntity camera,0,2,-10  
light=CreateLight()  
RotateEntity light,90,0,0  
plane=CreatePlane()  
ground_tex=LoadTexture("media/Chorme-2.bmp")  
EntityTexture plane,ground_tex  
cube=CreateCube()  
cube_tex=LoadTexture("media/b3dlogo.jpg")  
EntityTexture cube,cube_tex  
PositionEntity cube,0,1,0  
While Not KeyDown( 1 )  
If KeyDown( 205 )=True Then TurnEntity camera,0,-1,0  
If KeyDown( 203 )=True Then TurnEntity camera,0,1,0  
If KeyDown( 208 )=True Then MoveEntity camera,0,0,-0.05  
If KeyDown( 200 )=True Then MoveEntity camera,0,0,0.05  
; Use camera project to get 2D coordinates from 3D coordinates of cube  
CameraProject(camera,EntityX(cube),EntityY(cube),EntityZ(cube))  
RenderWorld  
; If cube is in view then draw text, if not then draw nothing otherwise text  will be drawn at 0,0  
If EntityInView(cube,camera)=True  
; Use ProjectedX() and ProjectedY() to get 2D coordinates from when CameraProject  was used.  
; Use these coordinates to draw text at a 2D position, on top of a 3D scene.  
Text ProjectedX#(),ProjectedY#(),"Cube"  
EndIf  
Text 0,0,"Use cursor keys to move about"  
Text 0,20,"ProjectedX: "+ProjectedX#()  
Text 0,40,"ProjectedY: "+ProjectedY#()  
Text 0,60,"ProjectedZ: "+ProjectedZ#()  
Text 0,80,"EntityInView: "+EntityInView(cube,camera)  
Flip  
Wend  
End  
end function
;; Returns the name of an entity. An entity's name may be set in a modelling  program, or manually set using NameEntity.
;; See also: <a class=small href=NameEntity.htm>NameEntity</a>.
;; Click <a href=http://www.blitzbasic.co.nz/b3ddocs/command.php?name=EntityName&ref=comments target=_blank>here</a> to view the latest version of this page online</body>
;; </html>
;;param entity - entity handle
function EntityName$ ( entity )
end function
;; Sets the drawing order for an entity.
;; An order value of 0 will mean the  entity is drawn normally. A value greater than 0 will mean that entity is drawn  first, behind everything else. A value less than 0 will mean the entity is drawn  last, in front of everything else.
;; Setting an entity's order to non-0 also disables z-buffering for the entity,  so should be only used for simple, convex entities like skyboxes, sprites etc.
;; EntityOrder affects the specified entity but none of its child entities,  if any exist.
;; Click <a href=http://www.blitzbasic.co.nz/b3ddocs/command.php?name=EntityOrder&ref=comments target=_blank>here</a> to view the latest version of this page online</body>
;; </html>
;;param entity - entity handle
;;param order - order that entity will be drawn in
function EntityOrder entity,order
end function
;; Attaches an entity to a parent.
;; Parent may be 0, in which case the entity  will have no parent.
;; Click <a href=http://www.blitzbasic.co.nz/b3ddocs/command.php?name=EntityParent&ref=comments target=_blank>here</a> to view the latest version of this page online</body>
;; </html>
;;param entity - entity handle
;;param parent - parent entity handle
;;param global (optional) - true for the child entity to retain its global position  and orientation. Defaults to true.
function EntityParent entity,parent[,global]
end function
;; Returns the nearest entity 'ahead' of the specified entity. An entity must  have a non-zero EntityPickMode to be pickable.
;; See also: <a class=small href=EntityPick.htm>EntityPick</a>, <a class=small href=LinePick.htm>LinePick</a>, <a class=small href=CameraPick.htm>CameraPick</a>, <a class=small href=EntityPickMode.htm>EntityPickMode</a>.
;; Click <a href=http://www.blitzbasic.co.nz/b3ddocs/command.php?name=EntityPick&ref=comments target=_blank>here</a> to view the latest version of this page online</body>
;; </html>
;;param entity - entity handle
;;param range# - range of pick area around entity
function EntityPick ( entity,range# )
end function
;; Sets the pick mode for an entity.
;; The optional obscurer parameter is used  with EntityVisible to determine just what can  get in the way of the line-of-sight between 2 entities. This allows some entities  to be pickable using the other pick commands, but to be ignored (i.e. 'transparent')  when using EntityVisible. So, its very much EntityVisible specific.
;; Please note that only Sphere and Box picking will work with Blitz3D sprites. For polygon picking, you will need a valid mesh.
;; See also: <a class=small href=EntityPick.htm>EntityPick</a>, <a class=small href=LinePick.htm>LinePick</a>, <a class=small href=CameraPick.htm>CameraPick</a>, <a class=small href=EntityPickMode.htm>EntityPickMode</a>.
;; Click <a href=http://www.blitzbasic.co.nz/b3ddocs/command.php?name=EntityPickMode&ref=comments target=_blank>here</a> to view the latest version of this page online</body>
;; </html>
;;param entity - entity handle
;;param pick_geometry - type of geometry used for picking:
;;param 0: Unpickable (default)
;;param 1: Sphere (EntityRadius is used)
;;param 2: Polygon
;;param 3: Box (EntityBox is used)
;;param obscurer (optional) - True to determine that the entity 'obscures' other entities  during an EntityVisible call. Defaults to True.
function EntityPickMode entity,pick_geometry[,obscurer]
end function
;; Returns the pitch angle of an entity.
;; The pitch angle is also the x angle of an entity.
;;param entity - name of entity that will have pitch angle returned
;;param global (optional) - true if the pitch angle returned should be relative to 0 rather than a parent entity's pitch angle. False by default.
function EntityPitch# ( entity[,global] )
; EntityPitch Example  
; -------------------  
Graphics3D 640,480  
SetBuffer BackBuffer()  
camera=CreateCamera()  
light=CreateLight()  
cone=CreateCone( 32 )  
PositionEntity cone,0,0,5  
While Not KeyDown( 1 )  
pitch#=0  
yaw#=0  
roll#=0  
If KeyDown( 208 )=True Then pitch#=-1  
If KeyDown( 200 )=True Then pitch#=1  
If KeyDown( 203 )=True Then yaw#=-1  
If KeyDown( 205 )=True Then yaw#=1  
If KeyDown( 45 )=True Then roll#=-1  
If KeyDown( 44 )=True Then roll#=1  
TurnEntity cone,pitch#,yaw#,roll#  
RenderWorld  
Text 0,0,"Use cursor/Z/X keys to turn cone"  
; Return entity pitch angle of cone  
Text 0,20,"Pitch: "+EntityPitch#( cone )  
Flip  
Wend  
End  
end function
;; Sets the radius of an entity's collision ellipsoid.
;; An entity radius should be set for all entities involved in ellipsoidal collisions, which is all source entities (as collisions are always ellipsoid-to-something), and  whatever destination entities are involved in ellipsoid-to-ellipsoid collisions (collision method No.1).
;; See also: <a class=small href=EntityBox.htm>EntityBox</a>, <a class=small href=Collisions.htm>Collisions</a>, <a class=small href=EntityType.htm>EntityType</a>.
;;param entity - entity handle
;;param x_radius# - x radius of entity's collision ellipsoid
;;param y_radius# (optional) - y radius of entity's collision ellipsoid. If omitted the x_radius# will be used for the y_radius#.
function EntityRadius entity,x_radius#[,y_radius#]
; EntityRadius Example  
; --------------------  
Graphics3D 640,480  
SetBuffer BackBuffer()  
camera=CreateCamera()  
light=CreateLight()  
sphere=CreateSphere( 32 )  
PositionEntity sphere,-2,0,5  
cone=CreateCone( 32 )  
EntityType cone,type_cone  
PositionEntity cone,2,0,5  
; Set collision type values  
type_sphere=1  
type_cone=2  
; Set sphere radius value  
sphere_radius#=1  
; Set sphere and cone entity types  
EntityType sphere,type_sphere  
EntityType cone,type_cone  
; Enable collisions between type_sphere and type_cone, with ellipsoid->polygon method and slide response  
Collisions type_sphere,type_cone,2,2  
While Not KeyDown( 1 )  
x#=0  
y#=0  
z#=0  
If KeyDown( 203 )=True Then x#=-0.1  
If KeyDown( 205 )=True Then x#=0.1  
If KeyDown( 208 )=True Then y#=-0.1  
If KeyDown( 200 )=True Then y#=0.1  
If KeyDown( 44 )=True Then z#=-0.1  
If KeyDown( 30 )=True Then z#=0.1  
MoveEntity sphere,x#,y#,z#  
; If square brackets keys pressed then change sphere radius value  
If KeyDown( 26 )=True Then sphere_radius#=sphere_radius#-0.1  
If KeyDown( 27 )=True Then sphere_radius#=sphere_radius#+0.1  
; Set entity radius of sphere  
EntityRadius sphere,sphere_radius#  
; Perform collision checking  
UpdateWorld  
RenderWorld  
Text 0,0,"Use cursor/A/Z keys to move sphere"  
Text 0,20,"Press [ or ] to change EntityRadius radius_x# value"  
Text 0,40,"EntityRadius sphere,"+sphere_radius  
Flip  
Wend  
End  
end function
;; Returns the roll angle of an entity.
;; The roll angle is also the z angle of an entity.
;;param entity - name of entity that will have roll angle returned
;;param global (optional) - true if the roll angle returned should be relative to 0 rather than a parent entity's  roll angle. False by default.
function EntityRoll# ( entity[,global] )
; EntityRoll Example  
; -------------------  
Graphics3D 640,480  
SetBuffer BackBuffer()  
camera=CreateCamera()  
light=CreateLight()  
cone=CreateCone( 32 )  
PositionEntity cone,0,0,5  
While Not KeyDown( 1 )  
pitch#=0  
yaw#=0  
roll#=0  
If KeyDown( 208 )=True Then pitch#=-1  
If KeyDown( 200 )=True Then pitch#=1  
If KeyDown( 203 )=True Then yaw#=-1  
If KeyDown( 205 )=True Then yaw#=1  
If KeyDown( 45 )=True Then roll#=-1  
If KeyDown( 44 )=True Then roll#=1  
TurnEntity cone,pitch#,yaw#,roll#  
RenderWorld  
Text 0,0,"Use cursor/Z/X keys to turn cone"  
; Return entity roll angle of cone  
Text 0,20,"Roll: "+EntityRoll#( cone )  
Flip  
Wend  
End  
end function
;; Sets the specular shininess of an entity.
;; The shininess# value should be a floting point number in the range 0-1. The default shininess setting is 0.
;; Shininess is how much brighter certain areas of an object will appear to be when a light is shone directly at them.
;; Setting a shininess value of 1 for a medium to high poly sphere, combined  with the creation of a light shining in the direction of it, will give it the  appearance of a shiny snooker ball.
;;param Entity - entity handle
;;param Shininess# - shininess of entity
function EntityShininess Entity, Shininess#
; EntityShininess Example  
; -----------------------  
Graphics3D 640,480  
SetBuffer BackBuffer()  
camera=CreateCamera()  
; Set ambient light to a low level, to increase effect of shininess  
AmbientLight 32,32,32  
light=CreateLight()  
RotateEntity light,45,45,0  
sphere=CreateSphere(100)  
EntityColor Sphere,255,0,0  
PositionEntity sphere,0,0,4  
; Set initial shine value  
shine#=0  
While Not KeyDown(1)  
; Change shine value depending on key pressed  
If KeyDown(2)=True And shine#>0 Then shine#=shine#-0.01  
If KeyDown(3)=True And shine#<1 Then shine#=shine#+0.01  
; Set entity shininess using shine value  
EntityShininess sphere,shine#  
RenderWorld  
Text 0,0,"Press keys 1-2 to change EntityShininess Setting"  
Text 0,20,"Entity Shininess: "+shine#  
Flip  
Wend  
End  
end function
;; Applies a texture to an entity.
;; The optional frame parameter specifies  which texture animation frame should be used as the texture.
;; The optional index parameter specifies which index number should be assigned  to the texture. Index numbers are used for the purpose of multitexturing. See TextureBlend.
;; A little note about multitexturing and slowdown. Graphics cards support a  maximum amount of textures per object, which can be used with very little, if  any, slowdown. For most cards this is two, but for a GeForce3 it is four. However,  once you use more than this amount, Blitz will emulate the effect itself by  duplicating objects and textures. Obviously, this may then cause slowdown.
;;param entity - entity handle
;;param texture - texture handle
;;param frame (optional) - frame of texture. Defaults to 0.
;;param index (optional) - index number of texture. Should be in the range to 0-7. Defaults  to 0.
function EntityTexture entity,texture[,frame][,index]
; EntityTexture Example  
; ---------------------  
Graphics3D 640,480  
SetBuffer BackBuffer()  
camera=CreateCamera()  
light=CreateLight()  
RotateEntity light,90,0,0  
cube=CreateCube()  
PositionEntity cube,0,0,5  
; Load texture  
tex=LoadTexture( "media/b3dlogo.jpg" )  
; Texture entity  
EntityTexture cube,tex  
While Not KeyDown( 1 )  
pitch#=0  
yaw#=0  
roll#=0  
If KeyDown( 208 )=True Then pitch#=-1  
If KeyDown( 200 )=True Then pitch#=1  
If KeyDown( 203 )=True Then yaw#=-1  
If KeyDown( 205 )=True Then yaw#=1  
If KeyDown( 45 )=True Then roll#=-1  
If KeyDown( 44 )=True Then roll#=1  
TurnEntity cube,pitch#,yaw#,roll#  
RenderWorld  
Flip  
Wend  
End  
end function
;; Sets the collision type for an entity.
;; A collision_type value of 0 indicates that no collision checking will occur with that entity. A collision value of 1-999 will mean collision checking will occur.
;; See also: <a class=small href=Collisions.htm>Collisions</a>, <a class=small href=GetEntityType.htm>GetEntityType</a>, <a class=small href=EntityBox.htm>EntityBox</a>, <a class=small href=EntityRadius.htm>EntityRadius</a>.
;; Click <a href=http://www.blitzbasic.co.nz/b3ddocs/command.php?name=EntityType&ref=comments target=_blank>here</a> to view the latest version of this page online</body>
;; </html>
;;param entity - entity handle
;;param collision_type - collision type of entity. Must be in the range 0-999.
;;param recursive (optional) - true to apply collision type to entity's children. Defaults  to false.
function EntityType entity,collision_type[,recursive]
end function
;; Returns true if src_entity and dest_entity can 'see' each other.
;; Click <a href=http://www.blitzbasic.co.nz/b3ddocs/command.php?name=EntityVisible&ref=comments target=_blank>here</a> to view the latest version of this page online</body>
;; </html>
;;param src_entity - source entity handle
;;param dest_entity - destination entity handle
function EntityVisible ( src_entity,dest_entity )
end function
;; The X-coordinate of the entity.
;; If the global flag is set to False then the parent's local coordinate system is used.
;; NOTE: If the entity has no parent then local and global coordinates are the same.
;; In this case you can think of the 3d world as the parent.
;; Global coordinates refer to the 3d world. Blitz 3D uses a left-handed system:
;; X+ is to the right
;; Y+ is up
;; Z+ is forward ( into the screen )
;; Every entity also has its own Local coordinate system.
;; The global system never changes.
;; But the local system is carried along as an entity moves and turns.
;; This same concept is used in the entity movement commands:
;; MoveEntity entity, 0,0,1
;; No matter what the orientation this moves one unit forward.
;;param entity = handle of Loaded or Created Entity
;;param global = True for Global coordinates,  False for Local. Optional, defaults to False.
function EntityX# ( entity[,global] )
; EntityX / EntityY / EntityZ example.  
; Escape quits, other keys move or pause the display.  
Const width = 640, height = 480  
Const KEY_ESC = 1, KEY_LEFT = 203, KEY_RIGHT = 205  
Graphics3D 640, 480  
AmbientLight 50, 50, 50  
Global isMoving = False     ; used to pause/resume movement  
Global count                ; how many updates have been done  
; Set up a camera, light and three entities...  
cam = CreateCamera()  
PositionEntity cam, 0, 2, -50  
CameraZoom cam, 4  
lt = CreateLight() : TurnEntity lt, 30, 40, 0  
Global oSphere, pCone, cSphere  
oSphere = CreateSphere()  
EntityColor oSphere, 250, 50, 0        ; Orange = Origin, parent of cone  
pCone = CreateCone( 8, True, oSphere)  ;  will be a parent of small sphere  
ScaleEntity pCone, .8, 2.0, .8  
PositionEntity pCone, 8, 0, 0  
EntityColor pCone, 255, 255, 0  
cSphere = CreateSphere( 8, pCone )         ; child of the cone  
EntityColor cSphere, 150, 150, 0  
ScaleEntity cSphere, .4/.8, .4/2.0, .4/.8  ; try commenting out this line  
PositionEntity cSphere, 0, 2, 0            ; above parent  
; ... and we are ready run.  
While Not KeyDown( KEY_ESC )  
UpdateEverything  
RenderWorld  
ShowInfo  
Flip  
Wend  
End  
Function UpdateEverything( )  
; Nothing moves relative to its parent, so local coordinates are constant.  
; Try uncommenting the PositionEntity command to change this.  
If GetKey() Then isMoving = Not isMoving  
If isMoving  
TurnEntity oSphere, 0, .5, 0  
TurnEntity pCone,  .2,  0, 0  
count = count + 1  
a# = count Mod 360  
;	PositionEntity cSphere, 0, 2 + Sin( a ), 0 ; experiment with this  
End If  
End Function  
Function ShowInfo( )   ; global and local coordinates for all entities  
Local x$, y$, z$  
Color 255, 255, 255  
Text 185, 20, "Global"  
Text 495, 20, "Local"  
Color 250, 50, 0  
Text  20, 50, "oSphere: " + XYZ( oSphere, True )  
Text 400, 50, XYZ( oSphere, False )  
Color 255, 255, 0  
Text  20, 75, "  pCone: " + XYZ( pCone, True )  
Text 400, 75, XYZ( pCone, False )  
Color 150, 150, 0  
Text  20, 100, "cSphere: " + XYZ( cSphere, True )  
Text 400, 100, XYZ( cSphere, False )  
End Function  
; ******************************************************************  
; These two functions just format the text display.  
; Without them there are too many numbers crowding the screen.  
Function Round#( x#, m# )		; returns x rounded to multiple of m  
If m < 0.0 Then m = -m  
s# = Sgn( x )  
If x < 0.0 Then x = -x  
diff# = x Mod m  
If diff < .5 * m  
Return ( x - diff ) * s  
Else  
Return ( m + x - diff ) * s  
End If  
End Function  
Function XYZ$( entity, globalFlag )  
ex# = Round( EntityX( entity, globalFlag ), .001 )  
ey# = Round( EntityY( entity, globalFlag ), .001 )  
ez# = Round( EntityZ( entity, globalFlag ), .001 )  
Return RSet( ex, 8 ) + RSet( ey, 8 ) + RSet( ez, 8 )  
End Function  
end function
;; The Y-coordinate of the entity.
;; If the global flag is set to False then the parent's local coordinate system is used.
;; See EntityX() for an overview of Local and Global coordinates.
;;param entity = handle of Loaded or Created Entity
;;param global = True for Global coordinates,  False for Local. Optional, defaults to False.
function EntityY# ( entity[,global] )
; EntityX / EntityY / EntityZ example.  
; Escape quits, other keys move or pause the display.  
Const width = 640, height = 480  
Const KEY_ESC = 1, KEY_LEFT = 203, KEY_RIGHT = 205  
Graphics3D 640, 480  
AmbientLight 50, 50, 50  
Global isMoving = False     ; used to pause/resume movement  
Global count                ; how many updates have been done  
; Set up a camera, light and three entities...  
cam = CreateCamera()  
PositionEntity cam, 0, 2, -50  
CameraZoom cam, 4  
lt = CreateLight() : TurnEntity lt, 30, 40, 0  
Global oSphere, pCone, cSphere  
oSphere = CreateSphere()  
EntityColor oSphere, 250, 50, 0        ; Orange = Origin, parent of cone  
pCone = CreateCone( 8, True, oSphere)  ;  will be a parent of small sphere  
ScaleEntity pCone, .8, 2.0, .8  
PositionEntity pCone, 8, 0, 0  
EntityColor pCone, 255, 255, 0  
cSphere = CreateSphere( 8, pCone )         ; child of the cone  
EntityColor cSphere, 150, 150, 0  
ScaleEntity cSphere, .4/.8, .4/2.0, .4/.8  ; try commenting out this line  
PositionEntity cSphere, 0, 2, 0            ; above parent  
; ... and we are ready run.  
While Not KeyDown( KEY_ESC )  
UpdateEverything  
RenderWorld  
ShowInfo  
Flip  
Wend  
End  
Function UpdateEverything( )  
; Nothing moves relative to its parent, so local coordinates are constant.  
; Try uncommenting the PositionEntity command to change this.  
If GetKey() Then isMoving = Not isMoving  
If isMoving  
TurnEntity oSphere, 0, .5, 0  
TurnEntity pCone,  .2,  0, 0  
count = count + 1  
a# = count Mod 360  
;	PositionEntity cSphere, 0, 2 + Sin( a ), 0 ; experiment with this  
End If  
End Function  
Function ShowInfo( )   ; global and local coordinates for all entities  
Local x$, y$, z$  
Color 255, 255, 255  
Text 185, 20, "Global"  
Text 495, 20, "Local"  
Color 250, 50, 0  
Text  20, 50, "oSphere: " + XYZ( oSphere, True )  
Text 400, 50, XYZ( oSphere, False )  
Color 255, 255, 0  
Text  20, 75, "  pCone: " + XYZ( pCone, True )  
Text 400, 75, XYZ( pCone, False )  
Color 150, 150, 0  
Text  20, 100, "cSphere: " + XYZ( cSphere, True )  
Text 400, 100, XYZ( cSphere, False )  
End Function  
; ******************************************************************  
; These two functions just format the text display.  
; Without them there are too many numbers crowding the screen.  
Function Round#( x#, m# )		; returns x rounded to multiple of m  
If m < 0.0 Then m = -m  
s# = Sgn( x )  
If x < 0.0 Then x = -x  
diff# = x Mod m  
If diff < .5 * m  
Return ( x - diff ) * s  
Else  
Return ( m + x - diff ) * s  
End If  
End Function  
Function XYZ$( entity, globalFlag )  
ex# = Round( EntityX( entity, globalFlag ), .001 )  
ey# = Round( EntityY( entity, globalFlag ), .001 )  
ez# = Round( EntityZ( entity, globalFlag ), .001 )  
Return RSet( ex, 8 ) + RSet( ey, 8 ) + RSet( ez, 8 )  
End Function  
end function
;; Returns the yaw angle of an entity.
;; The yaw angle is also the y angle of an entity.
;;param entity - name of entity that will have yaw angle returned
;;param global (optional) - true if the yaw angle returned should be relative to 0 rather than a parent entity's  yaw angle. False by default.
function EntityYaw# ( entity[,global] )
; EntityYaw Example  
; -----------------  
Graphics3D 640,480  
SetBuffer BackBuffer()  
camera=CreateCamera()  
light=CreateLight()  
cone=CreateCone( 32 )  
PositionEntity cone,0,0,5  
While Not KeyDown( 1 )  
pitch#=0  
yaw#=0  
roll#=0  
If KeyDown( 208 )=True Then pitch#=-1  
If KeyDown( 200 )=True Then pitch#=1  
If KeyDown( 203 )=True Then yaw#=-1  
If KeyDown( 205 )=True Then yaw#=1  
If KeyDown( 45 )=True Then roll#=-1  
If KeyDown( 44 )=True Then roll#=1  
TurnEntity cone,pitch#,yaw#,roll#  
RenderWorld  
Text 0,0,"Use cursor/Z/X keys to turn cone"  
; Return entity yaw angle of cone  
Text 0,20,"Yaw: "+EntityYaw#( cone )  
Flip  
Wend  
End  
end function
;; The Z-coordinate of the entity.
;; If the global flag is set to False then the parent's local coordinate system is used.
;; See EntityX() for an overview of Local and Global coordinates.
;;param entity = handle of Loaded or Created Entity
;;param global = True for Global coordinates,  False for Local. Optional, defaults to False.
function EntityZ# ( entity[,global] )
; EntityX / EntityY / EntityZ example.  
; Escape quits, other keys move or pause the display.  
Const width = 640, height = 480  
Const KEY_ESC = 1, KEY_LEFT = 203, KEY_RIGHT = 205  
Graphics3D 640, 480  
AmbientLight 50, 50, 50  
Global isMoving = False     ; used to pause/resume movement  
Global count                ; how many updates have been done  
; Set up a camera, light and three entities...  
cam = CreateCamera()  
PositionEntity cam, 0, 2, -50  
CameraZoom cam, 4  
lt = CreateLight() : TurnEntity lt, 30, 40, 0  
Global oSphere, pCone, cSphere  
oSphere = CreateSphere()  
EntityColor oSphere, 250, 50, 0        ; Orange = Origin, parent of cone  
pCone = CreateCone( 8, True, oSphere)  ;  will be a parent of small sphere  
ScaleEntity pCone, .8, 2.0, .8  
PositionEntity pCone, 8, 0, 0  
EntityColor pCone, 255, 255, 0  
cSphere = CreateSphere( 8, pCone )         ; child of the cone  
EntityColor cSphere, 150, 150, 0  
ScaleEntity cSphere, .4/.8, .4/2.0, .4/.8  ; try commenting out this line  
PositionEntity cSphere, 0, 2, 0            ; above parent  
; ... and we are ready run.  
While Not KeyDown( KEY_ESC )  
UpdateEverything  
RenderWorld  
ShowInfo  
Flip  
Wend  
End  
Function UpdateEverything( )  
; Nothing moves relative to its parent, so local coordinates are constant.  
; Try uncommenting the PositionEntity command to change this.  
If GetKey() Then isMoving = Not isMoving  
If isMoving  
TurnEntity oSphere, 0, .5, 0  
TurnEntity pCone,  .2,  0, 0  
count = count + 1  
a# = count Mod 360  
;	PositionEntity cSphere, 0, 2 + Sin( a ), 0 ; experiment with this  
End If  
End Function  
Function ShowInfo( )   ; global and local coordinates for all entities  
Local x$, y$, z$  
Color 255, 255, 255  
Text 185, 20, "Global"  
Text 495, 20, "Local"  
Color 250, 50, 0  
Text  20, 50, "oSphere: " + XYZ( oSphere, True )  
Text 400, 50, XYZ( oSphere, False )  
Color 255, 255, 0  
Text  20, 75, "  pCone: " + XYZ( pCone, True )  
Text 400, 75, XYZ( pCone, False )  
Color 150, 150, 0  
Text  20, 100, "cSphere: " + XYZ( cSphere, True )  
Text 400, 100, XYZ( cSphere, False )  
End Function  
; ******************************************************************  
; These two functions just format the text display.  
; Without them there are too many numbers crowding the screen.  
Function Round#( x#, m# )		; returns x rounded to multiple of m  
If m < 0.0 Then m = -m  
s# = Sgn( x )  
If x < 0.0 Then x = -x  
diff# = x Mod m  
If diff < .5 * m  
Return ( x - diff ) * s  
Else  
Return ( m + x - diff ) * s  
End If  
End Function  
Function XYZ$( entity, globalFlag )  
ex# = Round( EntityX( entity, globalFlag ), .001 )  
ey# = Round( EntityY( entity, globalFlag ), .001 )  
ez# = Round( EntityZ( entity, globalFlag ), .001 )  
Return RSet( ex, 8 ) + RSet( ey, 8 ) + RSet( ez, 8 )  
End Function  
end function
;; This command allows you to convert an animation with an MD2-style series  of anim sequences into a pure Blitz anim sequence, and play it back as such  using Animate.
;;param entity - entity handle
;;param first_frame - first frame of anim sequence to extract
;;param last_frame - last frame of anim sequence to extract
;;param anim_seq (optional) - anim sequence to extract from. This is usually 0, and  as such defaults to 0.
function ExtractAnimSeq( entity,first_frame,last_frame[,anim_seq] )
mesh=LoadAnimMesh( base_mesh$ ) ;anim sequence 0.  
ExtractAnimSeq( mesh,0,30 ) ;anim sequence 1: frames 0...30 are 'run'  
ExtractAnimSeq( mesh,31,40 ) ;anim sequence 2: frames 31...40 are 'jump' etc  etc...  
Animate mesh,3,1,2 ;play one-shot jump anim  
end function
;; Returns the first child of the specified entity with name matching child_name$.
;; Click <a href=http://www.blitzbasic.co.nz/b3ddocs/command.php?name=FindChild&ref=comments target=_blank>here</a> to view the latest version of this page online</body>
;; </html>
;;param entity - entity handle
;;param child_name$ - child name to find within entity
function FindChild ( entity,child_name$ )
end function
;; Attempts to find a surface attached to the specified mesh and created with  the specified brush. Returns the surface handle if found or 0 if not.
;; See  also: CountSurfaces, GetSurface.
;; Click <a href=http://www.blitzbasic.co.nz/b3ddocs/command.php?name=FindSurface&ref=comments target=_blank>here</a> to view the latest version of this page online</body>
;; </html>
;;param mesh - mesh handle
;;param brush - brush handle
function FindSurface ( mesh,brush)
end function
;; Scales and translates all vertices of a mesh so that the mesh occupies the specified box.
;; Do not use a width#, height# or depth# value of 0, otherwise all mesh data will be destroyed and your mesh will not be displayed. Use a value of 0.001 instead for a flat mesh along one axis.
;; See also: <a class=small href=ScaleMesh.htm>ScaleMesh</a>, <a class=small href=ScaleEntity.htm>ScaleEntity</a>.
;;param mesh - mesh handle
;;param x# - x position of mesh
;;param y# - y position of mesh
;;param z# - z position of mesh
;;param width# - width of mesh
;;param height# - height of mesh
;;param depth# - depth of mesh
;;param uniform (optional) - if true, the mesh will be scaled by the same amounts in x, y and z, so will not be distorted. Defaults to false.
function FitMesh mesh,x#,y#,z#,width#,height#,depth#[,uniform]
; FitMesh Example  
; ---------------  
; In this example we will demonstrate the use of the FitMesh command.  
; First we will use FitMesh on a semi-transparent blue box. This will represent the dimensions we will  
; be using with FitMesh.  
; Then we will use these dimensions on a red cone, so that it appears to fit inside the box when the  
; space bar is pressed.  
; The first time the space bar is pressed a uniform FitMesh will be performed, which means the cone  
; will be scaled equally along all axis so that at least one axis fits the dimensions specified.  
; The second time the space bar is pressed a non-unifrom FitMesh will be performed, meaning the cone  
; will be scaled non-equally along all axes so that all axes fit the dimensions specified.  
Graphics3D 640,480  
SetBuffer BackBuffer()  
camera=CreateCamera()  
light=CreateLight()  
; Create cube  
cube=CreateCube()  
; Set cube colour to blue  
EntityColor cube,0,0,255  
; Make cube semi-transparent so we will be able to see cone inside it later  
EntityAlpha cube,0.5  
; Use FitMesh on cube to make it a cuboid  
FitMesh cube,-1,-.5,-1,2,1,2  
; Position cube in front of camera so we can see it  
PositionEntity cube,0,-1,5  
; Create cone  
cone=CreateCone()  
; Set cone color to red  
EntityColor cone,255,0,0  
; Position cone in front of camera so we can see it  
PositionEntity cone,0,-1,5  
; Set uniform value to 1 so when space is first pressed, FitMesh will be uniform  
uniform=1  
While Not KeyDown(1)  
; If space bar pressed....  
If KeyHit(57)=True  
; Set syntax string to show syntax useage  
syntax$="FitMesh cone,-1,-.5,-1,2,1,2,"+uniform  
; Use FitMesh with cone, using same values as used with cube earlier. Cone should now fit in cube.  
FitMesh cone,-1,-.5,-1,2,1,2,uniform  
; Change uniform value from 1 to 0 so when space bar is pressed again FitMesh will be non-uniform  
uniform=0  
EndIf  
RenderWorld  
Text 0,0,"Press space to use uniform FitMesh with cone"  
Text 0,20,"Press space again to use non-uniform FitMesh with cone"  
Text 0,40,syntax$  
Flip  
Wend  
End  
end function
;; Flips all the triangles in a mesh.
;; This is useful for a couple of reasons.  Firstly though, it is important to understand a little bit of the theory behind  3D graphics. A 3D triangle is represented by three points; only when these points  are presented to the viewer in a clockwise-fashion is the triangle visible.  So really, triangles only have one side.
;; Normally, for example in the case of a sphere, a model's triangles face the  inside of the model, so it doesn't matter that you can't see them. However,  what about if you wanted to use the sphere as a huge sky for your world, i.e.  so you only needed to see the inside? In this case you would just use FlipMesh.
;; Another use for FlipMesh is to make objects two-sided, so you can see them from  the inside and outside if you can't already. In this case, you can copy the  original mesh using CopyEntity, specifying the  original mesh as the parent, and flip it using FlipMesh. You will now have two  meshes occupying the same space - this will make it double-sided, but beware,  it will also double the polygon count!
;; The above technique is worth trying when an external modelling program has  exported a model in such a way that some of the triangles appear to be missing.
;;param mesh - mesh handle
function FlipMesh mesh
; FlipMesh Example  
; ----------------  
Graphics3D 640,480  
SetBuffer BackBuffer()  
camera=CreateCamera()  
light=CreateLight()  
; Create sphere  
sphere=CreateSphere()  
; Scale sphere  
ScaleEntity sphere,100,100,100  
; Texture sphere with sky texture  
sky_tex=LoadTexture("media/sky.bmp")  
EntityTexture sphere,sky_tex  
; Flip mesh so we can see the inside of it  
FlipMesh sphere  
Color 0,0,0  
While Not KeyDown( 1 )  
RenderWorld  
Text 0,0,"You are viewing a flipped sphere mesh - makes a great sky!"  
Flip  
Wend  
End  
end function
;; Frees up a brush.
;; Click <a href=http://www.blitzbasic.co.nz/b3ddocs/command.php?name=FreeBrush&ref=comments target=_blank>here</a> to view the latest version of this page online</body>
;; </html>
;;param brush - brush handle
function FreeBrush brush
end function
;; FreeEntity will free up the internal resources associated  with a particular entity and remove it from the scene.
;; This command will also free all children entities parented to the entity.
;; Note that the variable holding the handle (and any variables referencing children handles) are not reset as it is up to the Blitz programmer to zero or ignore their contents following a call to FreeEntity().
;;param EntityHandle - Handle returned by an Entity creating function such as CreateCube(), CreateLight(), LoadMesh() etc.
function FreeEntity EntityHandle
; FreeEntity Example  
; This example creates an entity and  
; allows you to move it, but shows  
; that a handle is no longer valid after  
; FreeEntity is used on it.  
; Run in Debug Mode  
Graphics3D 640,480  
AppTitle "FreeEntity Example"  
Cam = CreateCamera()  
Lit = CreateLight()  
PositionEntity Lit,-5,-5,0  
PositionEntity Cam,0,0,-5  
AnEntity = CreateCube()    ; This is our Test Entity  
RotateMesh AnEntity,45,45,45  
While Not KeyDown(1) ; Until we press ESC  
; Use the Left or Right Arrows to Move the Entity  
If KeyDown(203) Then MoveEntity AnEntity,-0.1,0,0  
If KeyDown(205) Then MoveEntity AnEntity,0.1,0,0  
; Use the Space Key to Free the Entity. It will disappear  
; The next time you try to move it, you will get an error  
; Notice that the Handle Variable doesn't change after the  
; Entity is free. It simply becomes invalid.  
If KeyHit(57) Then FreeEntity AnEntity ; Hit Space to Free!  
RenderWorld ; Draw the Scene  
; What is in the AnEntity handle?  
Text 10,10,"Entity Handle: "+AnEntity  
Flip ; Flip it into View  
Wend  
End  
end function
;; Frees up a texture from memory.
;; Freeing a texture means you will not be  able to use it again; however, entities already textured with it will not lose  the texture.
;;param texture - texture handle
function FreeTexture texture
; FreeTexture Example  
; -------------------  
Graphics3D 640,480  
SetBuffer BackBuffer()  
camera=CreateCamera()  
light=CreateLight()  
RotateEntity light,90,0,0  
cube=CreateCube()  
PositionEntity cube,0,0,5  
; Load texture  
tex=LoadTexture( "media/b3dlogo.jpg" )  
; Texture cube with texture  
EntityTexture cube,tex  
While Not KeyDown( 1 )  
; If spacebar pressed then free texture  
If KeyHit( 57 )=True Then FreeTexture tex  
pitch#=0  
yaw#=0  
roll#=0  
If KeyDown( 208 )=True Then pitch#=-1  
If KeyDown( 200 )=True Then pitch#=1  
If KeyDown( 203 )=True Then yaw#=-1  
If KeyDown( 205 )=True Then yaw#=1  
If KeyDown( 45 )=True Then roll#=-1  
If KeyDown( 44 )=True Then roll#=1  
TurnEntity cube,pitch#,yaw#,roll#  
RenderWorld  
Text 0,0,"Press spacebar to free texture"  
Text 0,20,"As you can see this will not affect already textured entities"  
Flip  
Wend  
End  
end function
;; Returns the texture that is applied to the specified brush.
;; The optional index parameter allows you to specify which particular texture you'd like returning, if there are more than one textures applied to a brush.
;; You should release the texture returned by GetBrushTexture after use to prevent leaks! Use <a class=small href=../3d_commands/FreeTexture.htm>FreeTexture</a> to do this.
;; To find out the name of the texture, use <a class=small href=../3d_commands/TextureName.htm>TextureName</a>
;; See also: <a class=small href=TextureName.htm>TextureName</a>, <a class=small href=FreeTexture.htm>FreeTexture</a>, <a class=small href=GetEntityBrush.htm>GetEntityBrush</a>, <a class=small href=GetSurfaceBrush.htm>GetSurfaceBrush</a>.
;;param brush - brush handle
;;param index (optional) - index of texture applied to brush, from 0-7. Defaults to 0.
function GetBrushTexture(brush[,index=0])
; GetBrushTexture Example  
; -----------------------  
Graphics3D 640,480  
SetBuffer BackBuffer()  
camera=CreateCamera()  
light=CreateLight()  
RotateEntity light,90,0,0  
; Load mesh  
crate=LoadMesh("media/wood-crate/wcrate1.3ds")  
PositionEntity crate,0,0,100  
; Get mesh surface  
surf=GetSurface(crate,1)  
; Get surface brush  
crate_brush=GetSurfaceBrush(surf)  
; Get brush texture  
crate_tex=GetBrushTexture(crate_brush,0)  
While Not KeyDown( 1 )  
RenderWorld  
; Display full texture name  
Text 0,0,"Texture name, as returned by TextureName$():"  
Text 0,20,TextureName$(crate_tex)  
; Display trimmed texture name  
Text 0,40,"Texture name with path stripped:"  
Text 0,60,StripPath$(TextureName$(crate_tex))  
Flip  
Wend  
End  
Function StripPath$(file$)  
If Len(file$)>0  
For i=Len(file$) To 1 Step -1  
mi$=Mid$(file$,i,1)  
If mi$="\" Or mi$="/" Then Return name$ Else name$=mi$+name$  
Next  
EndIf  
Return name$  
End Function  
end function
;; Returns a child of an entity.
;; Click <a href=http://www.blitzbasic.co.nz/b3ddocs/command.php?name=GetChild&ref=comments target=_blank>here</a> to view the latest version of this page online</body>
;; </html>
;;param entity - entity handle
;;param index - index of child entity. Should be in the range 1...CountChildren(  entity ) inclusive.
function GetChild (entity,index)
end function
;; Returns a brush with the same properties as is applied to the specified entity.
;; If this command does not appear to be returning a valid brush, try using <a class=small href=../3d_commands/GetSurfaceBrush.htm>GetSurfaceBrush</a> instead with the first surface available.
;; Remember, GetEntityBrush actually creates a new brush so don't forget to free it afterwards using FreeBrush to prevent memory leaks.
;; Once you have got the brush handle from an entity, you can use GetBrushTexture and TextureName to get the details of what texture(s) are applied to the brush.
;; See also: <a class=small href=GetSurfaceBrush.htm>GetSurfaceBrush</a>, <a class=small href=FreeBrush.htm>FreeBrush</a>, <a class=small href=GetBrushTexture.htm>GetBrushTexture</a>, <a class=small href=TextureName.htm>TextureName</a>.
;; Click <a href=http://www.blitzbasic.co.nz/b3ddocs/command.php?name=GetEntityBrush&ref=comments target=_blank>here</a> to view the latest version of this page online</body>
;; </html>
;;param entity - entity handle
function GetEntityBrush(entity)
end function
;; Returns the collision type of an entity as set by the EntityType command.
;; See also: <a class=small href=EntityType.htm>EntityType</a>, <a class=small href=EntityBox.htm>EntityBox</a>, <a class=small href=EntityRadius.htm>EntityRadius</a>, <a class=small href=Collisions.htm>Collisions</a>, <a class=small href=ResetEntity.htm>ResetEntity</a>.
;; Click <a href=http://www.blitzbasic.co.nz/b3ddocs/command.php?name=GetEntityType&ref=comments target=_blank>here</a> to view the latest version of this page online</body>
;; </html>
;;param entity - entity handle
function GetEntityType ( entity )
end function
;; Returns the value of an element from within an entity's transformation matrix.
;; The transformation matrix is what is used by Blitz internally to position, scale and rotate entities.
;; GetMatElement is intended for use by advanced users only.
;;param entity - entity handle
;;param row - matrix row index
;;param column - matrix column index
function GetMatElement# ( entity,row,column )
; GetMatElement Example  
; ---------------------  
Graphics3D 640,480  
SetBuffer BackBuffer()  
camera=CreateCamera()  
PositionEntity camera,0,0,-5  
light=CreateLight()  
RotateEntity light,90,0,0  
; Create sphere  
sphere=CreateSphere()  
; Position, scale, rotate entity - try messing with these values to change GetMatElement value  
PositionEntity sphere,0,0,0  
ScaleEntity sphere,1,2,3  
RotateEntity sphere,0,0,0  
While Not KeyDown(1)  
RenderWorld  
Text 0,0,GetMatElement#(sphere,1,1)  
Flip  
Wend  
End  
end function
;; Returns an entity's parent.
;; Click <a href=http://www.blitzbasic.co.nz/b3ddocs/command.php?name=GetParent&ref=comments target=_blank>here</a> to view the latest version of this page online</body>
;; </html>
;;param entity - entity handle
function GetParent ( entity )
end function
;; Returns the handle of the surface attached to the specified mesh and with  the specified index number.
;; Index should be in the range 1...CountSurfaces(  mesh ), inclusive.
;; You need to 'get a surface', i.e. get its handle, in order to be able to  then use that particular surface with other commands.
;; See also: <a class=small href=CountSurfaces.htm>CountSurfaces</a>, <a class=small href=FindSurface.htm>FindSurface</a>.
;; Click <a href=http://www.blitzbasic.co.nz/b3ddocs/command.php?name=GetSurface&ref=comments target=_blank>here</a> to view the latest version of this page online</body>
;; </html>
;;param mesh - mesh handle
;;param index - index of surface
function GetSurface ( mesh, index )
end function
;; Returns a brush with the same properties as is applied to the specified mesh surface.
;; If this command does not appear to be returning a valid brush, try using <a class=small href=../3d_commands/GetEntityBrush.htm>GetEntityBrush</a> instead.
;; Remember, GetSurfaceBrush actually creates a new brush so don't forget to free it afterwards using <a class=small href=../3d_commands/FreeBrush.htm>FreeBrush</a> to prevent memory leaks.
;; Once you have got the brush handle from a surface, you can use <a class=small href=../3d_commands/GetBrushTexture.htm>GetBrushTexture</a> and <a class=small href=../3d_commands/TextureName.htm>TextureName</a> to get the details of what texture(s) are applied to the brush.
;; See also: <a class=small href=GetEntityBrush.htm>GetEntityBrush</a>, <a class=small href=FreeBrush.htm>FreeBrush</a>, <a class=small href=GetSurface.htm>GetSurface</a>, <a class=small href=GetBrushTexture.htm>GetBrushTexture</a>, <a class=small href=TextureName.htm>TextureName</a>.
;;param surface - surface handle
function GetSurfaceBrush(surface)
; GetSurfaceBrush Example  
; -----------------------  
Graphics3D 640,480  
SetBuffer BackBuffer()  
camera=CreateCamera()  
light=CreateLight()  
RotateEntity light,90,0,0  
; Load mesh  
crate=LoadMesh("media/wood-crate/wcrate1.3ds")  
PositionEntity crate,0,0,100  
; Get mesh surface  
surf=GetSurface(crate,1)  
; Get surface brush  
crate_brush=GetSurfaceBrush(surf)  
; Get brush texture  
crate_tex=GetBrushTexture(crate_brush,0)  
While Not KeyDown( 1 )  
RenderWorld  
; Display full texture name  
Text 0,0,"Texture name, as returned by TextureName$():"  
Text 0,20,TextureName$(crate_tex)  
; Display trimmed texture name  
Text 0,40,"Texture name with path stripped:"  
Text 0,60,StripPath$(TextureName$(crate_tex))  
Flip  
Wend  
End  
Function StripPath$(file$)  
If Len(file$)>0  
For i=Len(file$) To 1 Step -1  
mi$=Mid$(file$,i,1)  
If mi$="\" Or mi$="/" Then Return name$ Else name$=mi$+name$  
Next  
EndIf  
Return name$  
End Function  
end function
;; GfxDriver3D returns True if the specified graphics driver is 3D-capable.
;; GfxDriver3D is generally used after obtaining a list of available graphics drivers from a user's system via CountGfxDrivers(). You apply this to each driver in turn (or a selected driver) to see if it is 3D-capable. If this returns False, the driver can not perform 3D operations.
;; On systems with more than one display driver, you can use this to check each for 3D capability before choosing one via the SetGfxDriver command.
;;param driver - display driver number to check, from 1 to CountGfxDrivers ()
function GfxDriver3D(driver)
For a = 1 To CountGfxDrivers ()  
If GfxDriver3D (a)  
Print GfxDriverName (a) + " is 3D-capable"  
Else  
Print GfxDriverName (a) + " is NOT 3D-capable"  
EndIf  
Delay 100  
Next  
end function
;; Returns the 'caps level' of the current graphics driver. Values are:
;; 100: card supports all 'standard' Blitz3D operations.
;; 110: card supports all standard ops plus cubic environment mapping.
;; 3D Graphics code must be set before you call this command in order for it to work.
;; See also: <a class=small href=CreateTexture.htm>CreateTexture</a>, <a class=small href=Graphics3D.htm>Graphics3D</a>.
;;param None.
function GfxDriverCaps3D()
Graphics3D 640,480,0,2  
caps=GfxDriverCaps3D()  
Select caps  
Case 100 msg$="Your graphics card does not support cubic environment mapping."  
Case 110 msg$="Your graphics card does support cubic environment mapping."  
End Select  
Print msg$  
WaitKey()  
end function
;; GfxMode3D returns True if the specified graphics mode is 3D-capable.
;; GfxMode3D is generally used after obtaining a list of available display modes from a user's system via CountGfxModes (). You apply this to each mode in turn (or a selected mode) to see if you can enter 3D mode. If this returns False, calling Graphics3D () with this mode's details will fail!
;; If you don't wish to perform this check, the only Graphics3D call you can make with a guarantee of working on 99% of 3D graphics cards is 'Graphics3D 640, 480'. However, see GfxModeExists' gfx3d parameter for another method of performing this check.
;;param mode - graphics mode number from 1 - CountGfxModes ()
function GfxMode3D(mode)
For a = 1 To CountGfxModes ()  
If GfxMode3D (a)  
Print "Mode " + a + " is 3D-capable"  
Else  
Print "Mode " + a + " is NOT 3D-capable"  
EndIf  
Delay 100  
Next  
end function
;; GfxMode3DExists will test to see whether the host machine's graphics card supports a certain video mode, as specified by the width, height and depth parameters, and also whether the specified video mode is 3D-capable.
;; If these answer to both these things is yes, then the command will return true, otherwise false.
;; Most, if not all modern graphics cards are 3D-capable in all available graphics modes, however a few older ones are 2D-capable only in certain video modes hence the need for GfxMode3DExists as well as GfxModeExists.
;;param width - the width of the display mode you want to check for
;;param height - the height of the display mode you want to check for
;;param depth - the depth of the display mode you want to check for
function GfxMode3DExists( width, height, depth)
If GfxMode3DExists (2500, 2500, 64)  
Graphics3D 2500, 2500, 64  
Else  
RuntimeError "Domestic PC display hardware isn't yet capable of silly resolutions  like 2500 x 2500 x 64-bit!"  
EndIf  
end function
;; <P>
;; GfxModeExists will return True if a display mode fitting the given parameters is available, and False if the specified mode is not available. If it returns False, a game should either use a different mode or exit politely (eg. RuntimeError "I have been lazy and hard-coded my game to use a particular display mode which your system doesn't have. I should really use CountGfxModes and GfxModeWidth/Height/Depth to find out what your system can do. Oh, well, this saved 10 minutes out of my life!"... note subtle sarcastic hint!).
;;param height - the height of the display mode you want to check for<BR>
;;param depth - the depth of the display mode you want to check for<BR>
function GfxModeExists
Graphics 2500, 2500, 64<BR>  
Else<BR>  
RuntimeError "Domestic PC display hardware isn't yet capable of silly resolutions like 2500 x 2500 x 64-bit!"<BR>  
end function
;; Sets 3D Graphics mode. This command must be executed before any other 3D command, otherwise programs will return an error.
;; Width and height set the resolution of the screen and common values are 640,480  and 800,600. The resolution must be compatible with the 3D card and monitor  being used.
;; Depth sets the colour mode of the screen. If this value is omitted or set to 0, then the highest available colour depth available will be used. Other values  usually available are 16, 24 and 32. 16-bit colour mode displays the least amount  of colours, 65536. 24-bit and 32-bit colour modes display over 16 million colours and as a result offer a better quality picture, although may result in slower programs than 16-bit.
;; See also: <a class=small href=Graphics.htm>Graphics</a>, <a class=small href=EndGraphics.htm>EndGraphics</a>.
;;param width - width of screen resolution
;;param height - height of screen resolution
;;param depth (optional) - colour depth of screen. Defaults to highest colour depth  available.
;;param mode (optional) - mode of display. Defaults to 0.
;;param 0: windowed (if possible) in debug mode, fullscreen in non-debug mode
;;param 1: fullscreen always
;;param 2. windowed always
;;param 3: windowed/scaled always
function Graphics3D
; Graphics3D Example  
; ------------------  
; Set 3D graphics mode  
Graphics3D 640,480,16,0  
SetBuffer BackBuffer()  
camera=CreateCamera()  
light=CreateLight()  
cone=CreateCone( 32 )  
PositionEntity cone,0,0,5  
While Not KeyDown( 1 )  
RenderWorld  
Flip  
Wend  
End  
end function
;; Sets a sprite handle. Defaults to 0,0.
;; A sprite extends from -1,-1 to +1,+1.
;; See also: <a class=small href=LoadSprite.htm>LoadSprite</a>, <a class=small href=CreateSprite.htm>CreateSprite</a>.
;;param sprite - sprite handle. Not to be confused with HandleSprite - ie. the handle  used to position the sprite, rather than the sprite's actual handle
function HandleSprite sprite,x_handle#,y_handle#
Graphics3D 640,480  
cam = CreateCamera()  
MoveEntity cam,0,0,-5  
sp = CreateSprite()  
handlepos# = 0.0  
While Not KeyDown(1)  
RenderWorld  
Color 100,100,100  
Plot 320,240  
Text 320,250,"Handle",True  
Flip  
HandleSprite sp,handlepos,handlepos  
handlepos = handlepos + 0.01  
Wend  
End  
end function
;; Hides an entity, so that it is no longer visible, and is no longer involved  in collisions.
;; The main purpose of hide entity is to allow you to create entities  at the beginning of a program, hide them, then copy them and show as necessary  in the main game. This is more efficient than creating entities mid-game.
;; If you wish to hide an entity so that it is no longer visible but still involved  in collisions, then use EntityAlpha 0 instead.  This will make an entity completely transparent.
;; HideEntity affects the specified entity and all of its child entities, if  any exist.
;; Click <a href=http://www.blitzbasic.co.nz/b3ddocs/command.php?name=HideEntity&ref=comments target=_blank>here</a> to view the latest version of this page online</body>
;; </html>
;;param entity - entity handle
function HideEntity entity
end function
;; Enables or disables hardware multitexturing.
;; Multitexturing is a technique used to display more than one texture on an object  at once. Sometimes, 3D hardware has built-in support for this, so that using  two textures or more per object will not be any slower than using just one.
;; However, some cards have problems dealing with hardware multitexturing, and  for these situations you have the option of disabling it.
;; When hardware texturing isn't being used, Blitz3D will use its own software  technique, which involves duplicating objects that just have one texture each.
;; Click <a href=http://www.blitzbasic.co.nz/b3ddocs/command.php?name=HWMultiTex&ref=comments target=_blank>here</a> to view the latest version of this page online</body>
;; </html>
;;param enable - True to enable hardware multitexturing, False to disable.
;;param The  default HWMultiTex mode is True.
function HWMultiTex enable
end function
;; Returns the number of hardware texturing units available.
;;param None.
function HWTexUnits()
Graphics3D 640,480,0,2  
Print HWTexUnits()  
WaitKey()  
end function
;; Sets the color of a light.
;; An r,g,b value of 255,255,255 will brighten  anything the light shines on.
;; An r,g,b value of 0,0,0 will have no affect on anything it shines on.
;; An r,g,b value of -255,-255,-255 will darken anything it shines on. This is  known as 'negative lighting', and is useful for shadow effects.
;; See also: <a class=small href=CreateLight.htm>CreateLight</a>, <a class=small href=LightRange.htm>LightRange</a>, <a class=small href=LightConeAngles.htm>LightConeAngles</a>.
;;param light - light handle
;;param red# - red value of light
;;param green# - green value of light
;;param blue# - blue value of light
function LightColor light,red#,green#,blue#
Graphics3D 640,480  
camera = CreateCamera()  
MoveEntity camera,0,0,-3  
ball = CreateSphere()  
lite = CreateLight() ; try different lights 1 to 3  
MoveEntity lite,5,0,-15  
PointEntity lite,ball  
While Not KeyDown(1)  
RenderWorld:Flip  
If KeyHit(57) Then LightColor lite,Rnd(255),Rnd(255),Rnd(255) ; press SPACEBAR to try different light colors  
Wend  
End  
end function
;; Sets the 'cone' angle for a 'spot' light.
;; The default light cone angles setting  is 0,90.
;; See also: <a class=small href=CreateLight.htm>CreateLight</a>, <a class=small href=LightRange.htm>LightRange</a>, <a class=small href=LightColor.htm>LightColor</a>.
;;param light - light handle
;;param inner_angle# - inner angle of cone
;;param outer_angle# - outer angle of cone
function LightConeAngles light,inner_angle#,outer_angle#
Graphics3D 640,480  
camera = CreateCamera()  
MoveEntity camera,0,0,-40  
flat = CreatePlane(10)  
TurnEntity flat,-90,0,0  
lite = CreateLight(3) ; try different lights 1 to 3  
MoveEntity lite,0,0,-15  
While Not KeyDown(1)  
RenderWorld:Flip  
If KeyHit(57) Then ; press SPACEBAR to randomly change the 'cone' of light  
LightConeAngles lite, Rand(120),Rand(120)  
EndIf  
Wend  
End  
end function
;; Performs a 'fake' lighting operation on a mesh.
;; You need to use EntityFX ent,2 to enable vertex colors on the target mesh before you can see any results.
;; Since V1.83 the default vertex color for primitives is 255,255,255 and not 0,0,0, so for LightMesh to effect primitives as it did before, you will first need to reset the vertex colors to 0,0,0. You can do this using LightMesh mesh,-255,-255,-255.
;;param mesh - mesh handle
;;param red# - mesh red value
;;param green# - mesh green value
;;param blue# - mesh blue value
;;param range# (optional) - light range
;;param light_x# (optional) - light x position
;;param light_y# (optional) - light y position
;;param light_z# (optional) - light z position
function LightMesh mesh,red#,green#,blue#[,range#][,light_x#][,light_y#][,light_z#]
Graphics3D 640,480 ,16,2  
camera=CreateCamera()  
ent=CreateSphere()  
EntityFX ent,2 ; enable vertex colors  
Lightmesh ent,-255,-255,-255 ; reset vertex colors from 255,255,255 (default) to 0,0,0  
LightMesh ent,255,255,0,50,-20,20,-20 ; apply fake lighting  
MoveEntity camera,0,2,-10  
PointEntity camera,ent  
While Not KeyDown(1)  
RenderWorld  
Flip  
Wend  
End  
end function
;; Sets the range of a light.
;; The range of a light is how far it reaches.  Everything outside the range of the light will not be affected by it.
;; The value is very approximate, and should be experimented with for best results.
;; See also: <a class=small href=CreateLight.htm>CreateLight</a>, <a class=small href=LightColor.htm>LightColor</a>, <a class=small href=LightConeAngles.htm>LightConeAngles</a>.
;;param light - light handle
;;param range# - range of light (default: 1000.0)
function LightRange light,range#
Graphics3D 640,480  
camera = CreateCamera()  
MoveEntity camera,0,0,-3  
ball = CreateSphere()  
lite = CreateLight(2) ; try different lights 1 to 3  
MoveEntity lite,5,0,-5  
PointEntity lite,ball  
range# = 0.5  
LightRange lite,range  
While Not KeyDown(1)  
RenderWorld:Flip  
If KeyHit(57) Then ; hit SPACEBAR to increase light range  
range = range + 0.5  
LightRange lite,range  
EndIf  
Wend  
End  
end function
;; Returns the first entity between x,y,z to x+dx,y+dy,z+dz.
;; See also: <a class=small href=EntityPick.htm>EntityPick</a>, <a class=small href=LinePick.htm>LinePick</a>, <a class=small href=CameraPick.htm>CameraPick</a>, <a class=small href=EntityPickMode.htm>EntityPickMode</a>.
;; Click <a href=http://www.blitzbasic.co.nz/b3ddocs/command.php?name=LinePick&ref=comments target=_blank>here</a> to view the latest version of this page online</body>
;; </html>
;;param x# - x coordinate of start of line pick
;;param y# - y coordinate of start of line pick
;;param z# - z coordinate of start of line pick
;;param dx# - distance x of line pick
;;param dy# - distance y of line pick
;;param dz# - distance z of line pick
;;param radius (optional) - radius of line pick
function LinePick ( x#,y#,z#,dx#,dy#,dz#[,radius#] )
end function
;; Loads a sound and returns its handle for use with EmitSound.
;;param file$ - filename of sound file to be loaded and used as 3D sound
function Load3DSound ( file$ )
; Load3DSound Example  
; -------------------  
Graphics3D 640,480  
SetBuffer BackBuffer()  
camera=CreateCamera()  
PositionEntity camera,0,1,-10  
light=CreateLight()  
RotateEntity light,90,0,0  
plane=CreatePlane()  
ground_tex=LoadTexture("media/Chorme-2.bmp")  
EntityTexture plane,ground_tex  
cube=CreateCube()  
cube_tex=LoadTexture("media/b3dlogo.jpg")  
EntityTexture cube,cube_tex  
PositionEntity cube,0,1,0  
microphone=CreateListener(camera) ; Create listener, make it child of camera  
sound=Load3DSound("media/ufo.wav") ; Load 3D sound  
While Not KeyDown(1)  
If KeyDown(205)=True Then TurnEntity camera,0,-1,0  
If KeyDown(203)=True Then TurnEntity camera,0,1,0  
If KeyDown(208)=True Then MoveEntity camera,0,0,-0.05  
If KeyDown(200)=True Then MoveEntity camera,0,0,0.05  
; If left mouse button hit then emit sound from cube  
If MouseHit(1) = True Then EmitSound(sound,cube)  
RenderWorld  
Text 0,0,"Use cursor keys to move about"  
Text 0,20,"Press left mouse button to make a sound be emitted from the cube"  
Flip  
Wend  
End  
end function
;; LoadAnimMesh, similar to LoadMesh, Loads a mesh from an .X, .3DS or .B3D file and returns a mesh handle.
;; The difference between LoadMesh and LoadAnimMesh is that any hierarchy and animation information present in the file is retained. You can then either activate the animation by using the Animate command or find child entities within the hierarchy by using the FindChild(), GetChild() functions.
;; The optional parent parameter allows you to specify a parent entity for the mesh so that when the parent is moved the child mesh will move with it. However, this relationship is one way;  applying movement commands to the child will not affect the parent.
;; Specifying a parent entity will still result in the mesh being created at position 0,0,0 rather than at the parent entity's position.
;;param Filename$ - Name of the file containing the model to load.
;;param Parent (optional) - Specify an entity to act as a Parent to the loaded mesh.
function LoadAnimMesh( Filename$, [Parent] )
; LoadAnimMesh Example  
; --------------------  
; In this example we will demonstrate the use of the LoadAnimMesh command.  
; Quite simply, we will load an anim mesh from file, animate it, and then view it.  
Graphics3D 640,480  
SetBuffer BackBuffer()  
camera=CreateCamera()  
PositionEntity camera,0,20,-100 ; position camera so that robot will be in view when loaded  
light=CreateLight()  
RotateEntity light,90,0,0  
; Load anim mesh  
robot=LoadAnimMesh("media/makbot/mak_robotic.3ds")  
; Animate mesh - this will begin an animation sequence which is updated when UpdateWorld is called  
Animate robot,2  
While Not KeyDown(1)  
UpdateWorld ; Update anim - without this our anim mesh will freeze  
RenderWorld ; Render everything  
Flip ; Show everything  
Wend  
End  
end function
;; Appends an animation sequence from a file to an entity.
;; Returns the animation  sequence number added.
;; Click <a href=http://www.blitzbasic.co.nz/b3ddocs/command.php?name=LoadAnimSeq&ref=comments target=_blank>here</a> to view the latest version of this page online</body>
;; </html>
;;param entity - entity handle
;;param filename$ - filename of animated 3D object
function LoadAnimSeq ( entity,filename$ )
end function
;; Loads an animated texture from an image file and returns the texture's handle.
;; The flags parameter allows you to apply certain effects to the texture. Flags  can be added to combine two or more effects, e.g. 3 (1+2) = texture with colour  and alpha maps.
;; See <a class=small href=../3d_commands/CreateTexture.htm>CreateTexture</a> for more detailed descriptions of the texture flags.
;; The frame_width, frame_height, first_frame and frame_count parameters determine how Blitz will separate the image file into individual animation frames.
;; See also: <a class=small href=CreateTexture.htm>CreateTexture</a>, <a class=small href=LoadTexture.htm>LoadTexture</a>.
;;param file$ - name of image file with animation frames laid out in left-right,  top-to-bottom order
;;param flags (optional) - texture flag:
;;param 1: Color (default)
;;param 2: Alpha
;;param 4: Masked
;;param 8: Mipmapped
;;param 16: Clamp U
;;param 32: Clamp V
;;param 64: Spherical reflection map
;;param 128: Cubic environment map
;;param 256: Store texture in vram
;;param 512: Force the use of high color textures
;;param frame_width - width of each animation frame
;;param frame_height - height of each animation frame
;;param first_frame - the first frame to be used as an animation frame
;;param frame_count - the amount of frames to be used
function LoadAnimTexture ( file$,flags,frame_width,frame_height,first_frame,frame_count )
; LoadAnimTexture Example  
; -----------------------  
Graphics3D 640,480  
SetBuffer BackBuffer()  
camera=CreateCamera()  
light=CreateLight()  
RotateEntity light,90,0,0  
cube=CreateCube()  
PositionEntity cube,0,0,5  
; Load anim texture  
anim_tex=LoadAnimTexture( "media/boomstrip.bmp",49,64,64,0,39 )  
While Not KeyDown( 1 )  
; Cycle through anim frame values. 100 represents delay, 39 represents no. of  anim frames  
frame=MilliSecs()/100 Mod 39  
; Texture cube with anim texture frame  
EntityTexture cube,anim_tex,frame  
pitch#=0  
yaw#=0  
roll#=0  
If KeyDown( 208 )=True Then pitch#=-1  
If KeyDown( 200 )=True Then pitch#=1  
If KeyDown( 203 )=True Then yaw#=-1  
If KeyDown( 205 )=True Then yaw#=1  
If KeyDown( 45 )=True Then roll#=-1  
If KeyDown( 44 )=True Then roll#=1  
TurnEntity cube,pitch#,yaw#,roll#  
RenderWorld  
Flip  
Wend  
End  
end function
;; Creates a brush, loads and assigns a texture to it, and returns a brush handle.
;; Click <a href=http://www.blitzbasic.co.nz/b3ddocs/command.php?name=LoadBrush&ref=comments target=_blank>here</a> to view the latest version of this page online</body>
;; </html>
;;param texture_file$ - filename of texture
;;param flags - brush flags
;;param flags (optional) - flags can be added to combine effects:
;;param 1: Color
;;param 2: Alpha
;;param 4: Masked
;;param 8: Mipmapped
;;param 16: Clamp U
;;param 32: Clamp V
;;param 64: Spherical reflection map
;;param u_scale - brush u_scale
;;param v_scale - brush v_scale
function LoadBrush ( texture_file$[,flags][,u_scale][,v_scale]
end function
;; Loads a BSP model and returns its handle.
;; Some points about Blitz3D's BSP support:
;; * Shaders are *not* supported!
;; * BSP's cannot be painted, textured, colored, faded etc. in Blitz.
;; * A BSP model is just an entity. Use the standard entity commands to scale,  rotate and position the BSP, and the standard collision commands to setup collisions  with the BSP.
;; * BSP models are not lit by either 'normal' AmbientLight,  or by any directional lighting. This allows you to setup lighting for in-game  models without affecting the BSP lighting. However, BSP models *will* be lit  by point or spot lights.
;; * Textures for the BSP model must be in the same directory as the BSP file itself.
;; The optional parent parameter allows you to specify a parent entity for the  BSP so that when the parent is moved the child BSP will move with it. However,  this relationship is one way; applying movement commands to the child will not  affect the parent.
;; Specifying a parent entity will still result in the BSP being created at position 0,0,0 rather than at the parent entity's position.
;; See also: <a class=small href=BSPAmbientLight.htm>BSPAmbientLight</a>, <a class=small href=BSPLighting.htm>BSPLighting</a>.
;;param file$ - filename of BSP model
;;param gamma_adjust# (optional) - intensity of BSP lightmaps. Values should be in the  range 0-1. Defaults to 0.
;;param parent (optional) - parent entity of BSP
function LoadBSP( file$[,gamma_adjust#][,parent] )
Graphics3D 640,480  
campiv = CreatePivot()  
cam = CreateCamera(campiv)  
CameraRange cam, 0.1,2000  
level=LoadBSP( "nyk3dm1\nyk3dm1.bsp",.8 ) ; load a 'legal' quake3 bsp map  
BSPAmbientLight level, 0,255,0 ; make the ambient light green  
;BSPLighting level, False ; uncomment this line to turn lightmap off  
While Not KeyDown(1) ; if ESCAPE pressed then exit  
RenderWorld:Flip  
mys = MouseYSpeed()  
If Abs(EntityPitch(cam)+mys) < 75 ; restrict pitch of camera  
TurnEntity cam, mys,0,0  
EndIf  
TurnEntity campiv,0,-MouseXSpeed(),0  
If MouseDown(1) Then ; press mousebutton to move forward  
TFormVector 0,0,3,cam,campiv  
MoveEntity campiv,TFormedX(),TFormedY(),TFormedZ()  
EndIf  
MoveMouse 320,240 ; centre mouse cursor  
Wend  
End  
end function
;; Sets a matrix for 3d files loaded with the specified file extension.
;; This  can be used to change coordinate systems when loading.
;; By default, the following loader matrices are used:
;; LoaderMatrix "x",1,0,0,0,1,0,0,0,1 ; no change in coord system
;; LoaderMatrix "3ds",1,0,0,0,0,1,0,1,0 ; swap y/z axis'
;; You can use LoaderMatrix to flip meshes/animations if necessary, eg:
;; LoaderMatrix "x",-1,0,0,0,1,0,0,0,1 ; flip x-cords for ".x" files
;; LoaderMatrix "3ds",-1,0,0,0,0,-1,0,1,0 ; swap y/z, negate x/z for ".3ds" files
;; Click <a href=http://www.blitzbasic.co.nz/b3ddocs/command.php?name=LoaderMatrix&ref=comments target=_blank>here</a> to view the latest version of this page online</body>
;; </html>
;;param file extension$ - file extension of 3d file, e.g. ".x",".3ds"
;;param xx# - 1,1 element of 3x3 matrix
;;param xy# - 2,1 element of 3x3 matrix
;;param xz# - 3,1 element of 3x3 matrix
;;param yx# - 1,2 element of 3x3 matrix
;;param yy# - 2,2 element of 3x3 matrix
;;param yz# - 3,2 element of 3x3 matrix
;;param zx# - 1,3 element of 3x3 matrix
;;param zy# - 2,3 element of 3x3 matrix
;;param zz# - 3,3 element of 3x3 matrix
function LoaderMatrix file_extension$,xx#,xy#,xz#,yx#,yy#,yz#,zx#,zy#,zz#
end function
;; Loads an md2 entity and returns its handle.
;; An md2's texture has to be  loaded and applied to the md2 separately, otherwise the md2 will appear untextured.
;; Md2's have their own set of animation commands, and will not work with normal  animation commands.
;; The optional parent parameter allows you to specify a parent entity for the  md2 so that when the parent is moved the child md2 will move with it. However,  this relationship is one way; applying movement commands to the child will not  affect the parent.
;; Specifying a parent entity will still result in the md2 being created at  position 0,0,0 rather than at the parent entity's position.
;;param md2_file$ - filename of md2
;;param parent (optional) - parent entity of md2
function LoadMD2 ( md2_file$[,parent] )
; LoadMD2 Example  
; ---------------  
Graphics3D 640,480  
SetBuffer BackBuffer()  
camera=CreateCamera()  
light=CreateLight()  
RotateEntity light,90,0,0  
; Load md2  
gargoyle=LoadMD2( "media/gargoyle/gargoyle.md2" )  
; Load md2 texture  
garg_tex=LoadTexture( "media/gargoyle/gargoyle.bmp" )  
; Apply md2 texture to md2  
EntityTexture gargoyle,garg_tex  
PositionEntity gargoyle,0,-45,100  
RotateEntity gargoyle,0,180,0  
While Not KeyDown( 1 )  
RenderWorld  
Flip  
Wend  
End  
end function
;; LoadMesh, as the name suggests, Loads a mesh from an .X, .3DS or .B3D file (Usually created in advance by one of a number of 3D model creation packages) and returns the mesh handle.
;; Any hierarchy and animation information in the file will be ignored. Use LoadAnimMesh to maintain hierarchy and  animation information.
;; The optional parent parameter allows you to specify a parent entity for the mesh so that when the parent is moved the child mesh will move with it. However, this relationship is one way;  applying movement commands to the child will not affect the parent.
;; Specifying a parent entity will still result in the mesh being created at position 0,0,0 rather than at the parent entity's position.
;; See also: LoadAnimMesh.
;;param Filename$ - Name of the file containing the model to load.
;;param Parent (optional) - Specify an entity to act as a Parent to the loaded mesh.
function LoadMesh(filename$,[Parent])
; LoadMesh Example  
; ----------------  
Graphics3D 640,480  
SetBuffer BackBuffer()  
camera=CreateCamera()  
light=CreateLight()  
RotateEntity light,90,0,0  
; Load mesh  
drum=LoadMesh("media/oil-drum/oildrum.3ds")  
PositionEntity drum,0,0,MeshDepth(drum)*2  
While Not KeyDown( 1 )  
RenderWorld  
Flip  
Wend  
End  
end function
;; Creates a sprite entity, and assigns a texture to it.
;; See also: <a class=small href=LoadSprite.htm>LoadSprite</a>, <a class=small href=RotateSprite.htm>RotateSprite</a>, <a class=small href=ScaleSprite.htm>ScaleSprite</a>, <a class=small href=HandleSprite.htm>HandleSprite</a>, <a class=small href=SpriteViewMode.htm>SpriteViewMode</a>, <a class=small href=PositionEntity.htm>PositionEntity</a>, <a class=small href=MoveEntity.htm>MoveEntity</a>, <a class=small href=TranslateEntity.htm>TranslateEntity</a>, <a class=small href=EntityAlpha.htm>EntityAlpha</a>, <a class=small href=FreeEntity.htm>FreeEntity</a>.
;;param text_file$ - filename of image file to be used as sprite
;;param tex_flag (optional) - texture flag:
;;param 1: Color
;;param 2: Alpha
;;param 4: Masked
;;param 8: Mipmapped
;;param 16: Clamp U
;;param 32: Clamp V
;;param 64: Spherical reflection map
;;param parent - parent of entity
function LoadSprite ( tex_file$[,tex_flag][,parent] )
Graphics3D 640,480  
campivot = CreatePivot()  
cam = CreateCamera(campivot)  
MoveEntity cam,0,0,-5  
sp = LoadSprite("grass.bmp")  
SpriteViewMode sp,4  
While Not KeyDown(1)  
RenderWorld:Flip  
TurnEntity campivot,1,1,3  
Wend  
End  
end function
;; Loads a terrain from an image file and returns the terrain's handle.
;; The  image's red channel is used to determine heights. Terrain is initially the same  width and depth as the image, and 1 unit high.
;; Tips on generating nice terrain:
;; * Smooth or blur the height map
;; * Reduce the y scale of the terrain
;; * Increase the x/z scale of the terrain
;; * Reduce the camera range
;; When texturing an entity, a texture with a scale of 1,1,1 (default) will  be the same size as one of the terrain's grid squares. A texture that is scaled  to the same size as the size of the bitmap used to load it or the no. of grid  square used to create it, will be the same size as the terrain.
;; The optional parent parameter allows you to specify a parent  entity for the terrain so that when the parent is moved the child terrain will  move with it. However, this relationship is one way; applying movement commands  to the child will not affect the parent.
;; Specifying a parent entity will still result in the terrain being created  at position 0,0,0 rather than at the parent entity's position.
;; A heightmaps dimensions (width and height) must be the same and must be a power of 2, e.g. 32, 64, 128, 256, 512, 1024.
;; See also: CreateTerrain.
;;param file$ - filename of image file to be used as height map
;;param parent (optional) - parent entity of terrain
function LoadTerrain ( file$[,parent] )
LoadTerrain Example  
-------------------  
Graphics3D 640,480  
SetBuffer BackBuffer()  
camera=CreateCamera()  
PositionEntity camera,1,1,1  
light=CreateLight()  
RotateEntity light,90,0,0  
; Load terrain  
terrain=LoadTerrain( "media/height_map.bmp" )  
; Set terrain detail, enable vertex morphing  
TerrainDetail terrain,4000,True  
; Scale terrain  
ScaleEntity terrain,1,50,1  
; Texture terrain  
grass_tex=LoadTexture( "media/mossyground.bmp" )  
EntityTexture terrain,grass_tex,0,1  
While Not KeyDown( 1 )  
If KeyDown( 205 )=True Then TurnEntity camera,0,-1,0  
If KeyDown( 203 )=True Then TurnEntity camera,0,1,0  
If KeyDown( 208 )=True Then MoveEntity camera,0,0,-0.1  
If KeyDown( 200 )=True Then MoveEntity camera,0,0,0.1  
x#=EntityX(camera)  
y#=EntityY(camera)  
z#=EntityZ(camera)  
terra_y#=TerrainY(terrain,x#,y#,z#)+5  
PositionEntity camera,x#,terra_y#,z#  
RenderWorld  
Text 0,0,"Use cursor keys to move about the terrain"  
Flip  
Wend  
End  
end function
;; Load a texture from an image file and returns the texture's handle.  Supported file formats include: BMP, PNG, TGA and JPG.  Only PNG and TGA support alpha.
;; The optional flags parameter allows you to apply certain effects to the texture. Flags can be added to combine two or more effects, e.g. 3 (1+2) = texture with colour and alpha maps.
;; See <a class=small href=../3d_commands/CreateTexture.htm>CreateTexture</a> for more detailed descriptions of the texture flags.
;; Something to consider when applying texture flags to loaded textures is that the texture may have already had certain flags applied to it via the <a class=small href=../3d_commands/TextureFilter.htm>TextureFilter</a> command. The default for the <a class=small href=../3d_commands/TextureFilter.htm>TextureFilter</a> command is 9 (1+8), which is a coloured, mipmapped texture. This cannot be overridden via the flags parameter of the LoadTexture command - if you wish for the filters to be removed you will need to use the <a class=small href=../3d_commands/ClearTextureFilters.htm>ClearTextureFilters</a> command, which must be done after setting the graphics mode (setting the graphics mode restores the default texture filters).
;; See also: <a class=small href=CreateTexture.htm>CreateTexture</a>, <a class=small href=LoadAnimTexture.htm>LoadAnimTexture</a>.
;;param file$ - filename of image file to be used as texture
;;param flags (optional) - texture flag:
;;param 1: Color (default)
;;param 2: Alpha
;;param 4: Masked
;;param 8: Mipmapped
;;param 16: Clamp U
;;param 32: Clamp V
;;param 64: Spherical environment map
;;param 128: Cubic environment map
;;param 256: Store texture in vram
;;param 512: Force the use of high color textures
function LoadTexture ( file$[,flags] )
; LoadTexture Example  
; -------------------  
Graphics3D 640,480  
SetBuffer BackBuffer()  
camera=CreateCamera()  
light=CreateLight()  
RotateEntity light,90,0,0  
cube=CreateCube()  
PositionEntity cube,0,0,5  
; Load texture  
tex=LoadTexture( "media/b3dlogo.jpg" )  
; Texture cube with texture  
EntityTexture cube,tex  
While Not KeyDown( 1 )  
pitch#=0  
yaw#=0  
roll#=0  
If KeyDown( 208 )=True Then pitch#=-1  
If KeyDown( 200 )=True Then pitch#=1  
If KeyDown( 203 )=True Then yaw#=-1  
If KeyDown( 205 )=True Then yaw#=1  
If KeyDown( 45 )=True Then roll#=-1  
If KeyDown( 44 )=True Then roll#=1  
TurnEntity cube,pitch#,yaw#,roll#  
RenderWorld  
Flip  
Wend  
End  
end function
function Command Reference
end function
;; Returns 1 (True) if md2 is currently animating, 0 (False) if not.
;;param md2 - md2 handle
function MD2Animating ( md2 )
; MD2Animating Example  
; --------------------  
Graphics3D 640,480  
SetBuffer BackBuffer()  
camera=CreateCamera()  
light=CreateLight()  
RotateEntity light,90,0,0  
; Load md2  
gargoyle=LoadMD2( "media/gargoyle/gargoyle.md2")  
; Load md2 texture  
garg_tex=LoadTexture( "media/gargoyle/gargoyle.bmp" )  
; Apply md2 texture to md2  
EntityTexture gargoyle,garg_tex  
PositionEntity gargoyle,0,-45,100  
RotateEntity gargoyle,0,180,0  
While Not KeyDown( 1 )  
; Toggle animation stop/start when spacebar pressed  
If KeyHit( 57 )=True start=1-start : AnimateMD2 gargoyle,start,0.1,32,46  
UpdateWorld  
RenderWorld  
Text 0,0,"Press spacebar to stop/start md2 animation"  
; Output current md2 animation status to screen  
Text 0,20,"MD2Animating: "+MD2Animating( gargoyle )  
Flip  
Wend  
End  
end function
;; Returns the animation length of an md2 model.
;; The animation length is  the total amount of animation frames that consist within the md2 file.
;;param md2 - md2 handle
function MD2AnimLength ( md2 )
; MD2AnimLength Example  
; ---------------------  
Graphics3D 640,480  
SetBuffer BackBuffer()  
camera=CreateCamera()  
light=CreateLight()  
RotateEntity light,90,0,0  
; Load md2  
gargoyle=LoadMD2( "media/gargoyle/gargoyle.md2")  
; Load md2 texture  
garg_tex=LoadTexture( "media/gargoyle/gargoyle.bmp" )  
; Apply md2 texture to md2  
EntityTexture gargoyle,garg_tex  
PositionEntity gargoyle,0,-45,100  
RotateEntity gargoyle,0,180,0  
While Not KeyDown( 1 )  
RenderWorld  
; Output animation length to screen  
Text 0,0,"MD2AnimLength: "+MD2AnimLength( gargoyle )  
Flip  
Wend  
End  
end function
;; Returns the animation time of an md2 model.
;; The animation time is the  exact moment that the md2 is at with regards its frames of animation. For example,  if the md2 at a certain moment in time is moving between the third and fourth  frames, then MD2AnimTime will return a number in the region 3-4.
;;param md2 - md2 handle
function MD2AnimTime ( md2 )
; MD2AnimTime Example  
; -------------------  
Graphics3D 640,480  
SetBuffer BackBuffer()  
camera=CreateCamera()  
light=CreateLight()  
RotateEntity light,90,0,0  
; Load md2  
gargoyle=LoadMD2( "media/gargoyle/gargoyle.md2")  
; Load md2 texture  
garg_tex=LoadTexture( "media/gargoyle/gargoyle.bmp" )  
; Apply md2 texture to md2  
EntityTexture gargoyle,garg_tex  
; Animate md2  
AnimateMD2 gargoyle,1,0.1,32,46  
PositionEntity gargoyle,0,-45,100  
RotateEntity gargoyle,0,180,0  
While Not KeyDown( 1 )  
UpdateWorld  
RenderWorld  
; Output current animation frame to screen  
Text 0,0,"MD2AnimTime: "+MD2AnimTime( gargoyle )  
Flip  
Wend  
End  
end function
;; Returns the depth of a mesh. This is calculated by the actual vertex positions and so the scale of the entity (set by ScaleEntity) will not have an effect on the resultant depth. Mesh operations, on the other hand, will effect the result.
;; See also: <a class=small href=MeshWidth.htm>MeshWidth</a>, <a class=small href=MeshHeight.htm>MeshHeight</a>.
;; Click <a href=http://www.blitzbasic.co.nz/b3ddocs/command.php?name=MeshDepth&ref=comments target=_blank>here</a> to view the latest version of this page online</body>
;; </html>
;;param mesh - mesh handle
function MeshDepth# (mesh)
end function
;; Returns true if the specified meshes are currently intersecting.
;; This  is a fairly slow routine - use with discretion...
;; This command is  currently the only  polygon->polygon collision checking routine available in Blitz3D.
;;param mesh_a - mesh_a handle
;;param mesh_b - mesh_b handle
function MeshesIntersect (mesh_a,mesh_b )
; MeshesIntersect Example  
; -----------------------  
Graphics3D 640,480  
SetBuffer BackBuffer()  
camera=CreateCamera()  
light=CreateLight()  
RotateEntity light,90,0,0  
drum=LoadMesh("media/oil-drum/oildrum.3ds")  
PositionEntity drum,-20,0,100  
crate=LoadMesh("media/wood-crate/wcrate1.3ds")  
PositionEntity crate,20,0,100  
While Not KeyDown( 1 )  
TurnEntity drum,1,1,1  
TurnEntity crate,-1,-1,-1  
RenderWorld  
; Test to see if drum and crate meshes are intersecting; if so then display  message to confirm this  
If MeshesIntersect(drum,crate)=True Then Text 0,0,"Meshes are intersecting!"  
Flip  
Wend  
End  
end function
;; Returns the height of a mesh. This is calculated by the actual vertex positions and so the scale of the entity (set by ScaleEntity) will not have an effect on the resultant height. Mesh operations, on the other hand, will effect the result.
;; See also: <a class=small href=MeshWidth.htm>MeshWidth</a>, <a class=small href=MeshDepth.htm>MeshDepth</a>.
;; Click <a href=http://www.blitzbasic.co.nz/b3ddocs/command.php?name=MeshHeight&ref=comments target=_blank>here</a> to view the latest version of this page online</body>
;; </html>
;;param mesh - mesh handle
function MeshHeight# (mesh )
end function
;; Returns the width of a mesh. This is calculated by the actual vertex positions and so the scale of the entity (set by ScaleEntity) will not have an effect on the resultant width. Mesh operations, on the other hand, will effect the result.
;; See also: <a class=small href=MeshHeight.htm>MeshHeight</a>, <a class=small href=MeshDepth.htm>MeshDepth</a>.
;; Click <a href=http://www.blitzbasic.co.nz/b3ddocs/command.php?name=MeshWidth&ref=comments target=_blank>here</a> to view the latest version of this page online</body>
;; </html>
;;param mesh - mesh handle
function MeshWidth# (mesh)
end function
;; Sets the height of a point on a terrain.
;;param terrain - terrain handle
;;param grid_x - grid x coordinate of terrain
;;param grid_y - grid y coordinate of terrain
;;param height# - height of point on terrain. Should be in the range 0-1.
;;param realtime (optional) - True to modify terrain immediately. False to modify terrain  when RenderWorld in next called. Defaults to False.
function ModifyTerrain terrain,grid_x,grid_z,height#[,realtime]
; ModifyTerrain Example  
; ---------------------  
Graphics3D 640,480  
SetBuffer BackBuffer()  
terra_size=32 ; initial size of terrain, and no. of grids segments, along each  side  
x_scale=10 ; x scale of terrain  
y_scale=50 ; y scale of terrain  
z_scale=10 ; z scale of terrain  
marker_x=terra_size/2 ; initial x position of marker  
marker_z=terra_size/2 ; initial z position of marker  
camera=CreateCamera()  
PositionEntity camera,(terra_size*x_scale)/2,50,0 ; position wherever; just  try and get good view of terrain!  
RotateEntity camera,30,0,0 ; again, try and get good view of terrain  
light=CreateLight()  
RotateEntity light,90,0,0  
; Create terrain  
terra=CreateTerrain(terra_size)  
ScaleEntity terra,x_scale,y_scale,z_scale  
; Texture terrain  
grass_tex=LoadTexture("media/mossyground.bmp")  
EntityTexture terra,grass_tex  
; Create marker  
marker=CreateSphere()  
ScaleEntity marker,1,1,1  
EntityColor marker,255,0,0  
While Not KeyDown(1)  
; Change marker position values depending on cursor key pressed  
If KeyHit(205)=True Then marker_x=marker_x+1  
If KeyHit(203)=True Then marker_x=marker_x-1  
If KeyHit(208)=True Then marker_z=marker_z-1  
If KeyHit(200)=True Then marker_z=marker_z+1  
; Get terrain height at marker position  
marker_y#=TerrainHeight(terra,marker_x,marker_z)  
; If A pressed then increase marker_y value and modify terrain  
If KeyDown(30)=True  
If marker_y#<1 Then marker_y#=marker_y#+0.005  
ModifyTerrain terra,marker_x,marker_z,marker_y#  
EndIf  
; If Z pressed then decrease marker_y value and modify terrain  
If KeyDown(44)=True  
If marker_y#>0 Then marker_y#=marker_y#-0.005  
ModifyTerrain terra,marker_x,marker_z,marker_y#  
EndIf  
; Position marker, taking into account x, y and z scales of terrain  
PositionEntity marker,marker_x*x_scale,marker_y#*y_scale,marker_z*z_scale  
RenderWorld  
Text 0,0,"Use cursor keys to move marker over the terrain"  
Text 0,20,"Press A or Z to alter height of terrain at marker's position"  
Text 0,40,"Terrain Height: "+marker_y#  
Flip  
Wend  
End  
end function
;; Moves an entity relative to its current position and orientation.
;; What this means is that an entity will move in whatever direction it is facing. So for example if you have an game character is upright when first loaded into Blitz3D and it remains upright (i.e. turns left or right only), then moving it by a z amount will always see it move forward or backward, moving it by a y amount will always see it move up or down, and moving it by an x amount will always see it strafe.
;; See also: <a class=small href=TranslateEntity.htm>TranslateEntity</a>, <a class=small href=PositionEntity.htm>PositionEntity</a>, <a class=small href=PositionMesh.htm>PositionMesh</a>.
;;param entity - name of entity to be moved
;;param x# - x amount that entity will be moved by
;;param y# - y amount that entity will be moved by
;;param z# - z amount that entity will be moved by
function MoveEntity entity,x#,y#,z#
; MoveEntity Example  
; ------------------  
Graphics3D 640,480  
SetBuffer BackBuffer()  
camera=CreateCamera()  
light=CreateLight()  
cone=CreateCone( 32 )  
; Move cone in front of camera, so we can see it to begin with  
MoveEntity cone,0,0,10  
While Not KeyDown( 1 )  
; Reset movement values - otherwise, the cone will not stop!  
x#=0  
y#=0  
z#=0  
; Change rotation values depending on the key pressed  
If KeyDown( 203 )=True Then x#=-0.1  
If KeyDown( 205 )=True Then x#=0.1  
If KeyDown( 208 )=True Then y#=-0.1  
If KeyDown( 200 )=True Then y#=0.1  
If KeyDown( 44 )=True Then z#=-0.1  
If KeyDown( 30 )=True Then z#=0.1  
; Move cone using movement values  
MoveEntity cone,x#,y#,z#  
; If spacebar pressed then rotate cone by random amount  
If KeyHit( 57 )=True Then RotateEntity cone,Rnd( 0,360 ),Rnd( 0,360 ),Rnd(  0,360 )  
RenderWorld  
Text 0,0,"Use cursor/A/Z keys to move cone, spacebar to rotate cone by random  amount"  
Text 0,20,"X Movement: "+x#  
Text 0,40,"Y Movement: "+y#  
Text 0,60,"Z Movement: "+z#  
Flip  
Wend  
End  
end function
;; Sets an entity's name.
;; See also: <a class=small href=EntityName.htm>EntityName</a>.
;; Click <a href=http://www.blitzbasic.co.nz/b3ddocs/command.php?name=NameEntity&ref=comments target=_blank>here</a> to view the latest version of this page online</body>
;; </html>
;;param entity - entity handle
;;param name$ - name of entity
function NameEntity entity,name$
end function
;; Paints a entity with a brush.
;; The reason for using PaintEntity to apply  specific properties to a entity using a brush rather than just using EntityTexture,  EntityColor, EntityShininess etc, is that you can pre-define one brush, and  then paint entities over and over again using just the one command rather than  lots of separate ones.
;; Click <a href=http://www.blitzbasic.co.nz/b3ddocs/command.php?name=PaintEntity&ref=comments target=_blank>here</a> to view the latest version of this page online</body>
;; </html>
;;param entity - entity handle
;;param brush - brush handle
function PaintEntity entity,brush
end function
;; Paints a mesh with a brush.
;; This has the effect of instantly altering  the visible appearance of the mesh, assuming the brush's properties are different  to what was was applied to the surface before.
;; The reason for using PaintMesh to apply specific properties to a mesh using  a brush rather than just using EntityTexture, EntityColor, EntityShininess etc,  is that you can pre-define one brush, and then paint meshes over and over again  using just the one command rather than lots of separate ones.
;; See also: PaintEntity, PaintSurface.
;;param mesh - mesh handle
;;param brush - brush handle
function PaintMesh mesh,brush
; PaintMesh Example  
; -----------------  
Graphics3D 640,480  
SetBuffer BackBuffer()  
camera=CreateCamera()  
light=CreateLight()  
RotateEntity light,90,0,0  
cube=CreateCube()  
PositionEntity cube,0,0,5  
; Load texture  
tex=LoadTexture("media/b3dlogo.jpg")  
; Create brush  
brush=CreateBrush()  
; Apply texture to brush  
BrushTexture brush,tex  
; And some other effects  
BrushColor brush,0,0,255  
BrushShininess brush,1  
; Paint mesh with brush  
PaintMesh cube,brush  
While Not KeyDown( 1 )  
pitch#=0  
yaw#=0  
roll#=0  
If KeyDown( 208 )=True Then pitch#=-1  
If KeyDown( 200 )=True Then pitch#=1  
If KeyDown( 203 )=True Then yaw#=-1  
If KeyDown( 205 )=True Then yaw#=1  
If KeyDown( 45 )=True Then roll#=-1  
If KeyDown( 44 )=True Then roll#=1  
TurnEntity cube,pitch#,yaw#,roll#  
RenderWorld  
Flip  
Wend  
End  
end function
;; Paints a surface with a brush.
;; This has the effect of instantly altering  the visible appearance of that particular surface, i.e. section of mesh, assuming  the brush's properties are different to what was applied to the surface before.
;; See also: PaintEntity, PaintMesh.
;; Click <a href=http://www.blitzbasic.co.nz/b3ddocs/command.php?name=PaintSurface&ref=comments target=_blank>here</a> to view the latest version of this page online</body>
;; </html>
;;param surface - surface handle
;;param brush - brush handle
function PaintSurface surface,brush
end function
;; Returns the entity 'picked' by the most recently executed Pick command.  This might have been CameraPick, EntityPick or LinePick.
;; Returns 0 if no entity was picked.
;;param None.
function PickedEntity ( )
; PickedEntity Example  
; --------------------  
Graphics3D 640,480,0,2  
SetBuffer BackBuffer()  
camera=CreateCamera()  
PositionEntity camera,0,2,-10  
light=CreateLight()  
RotateEntity light,90,0,0  
plane=CreatePlane()  
EntityPickMode plane,2 ; Make the plane entity 'pickable'. Use pick_geometry  mode no.2 for polygon collision.  
ground_tex=LoadTexture("media/Chorme-2.bmp")  
EntityTexture plane,ground_tex  
cube=CreateCube()  
EntityPickMode cube,2 ; Make the cube entity 'pickable'. Use pick_geometry mode  no.2 for polygon collision.  
cube_tex=LoadTexture("media/b3dlogo.jpg")  
EntityTexture cube,cube_tex  
PositionEntity cube,0,1,0  
While Not KeyDown( 1 )  
If KeyDown( 205 )=True Then TurnEntity camera,0,-1,0  
If KeyDown( 203 )=True Then TurnEntity camera,0,1,0  
If KeyDown( 208 )=True Then MoveEntity camera,0,0,-0.05  
If KeyDown( 200 )=True Then MoveEntity camera,0,0,0.05  
; If left mouse button hit then use CameraPick with mouse coordinates  
; In this example, only three things can be picked: the plane, the cube, or  nothing  
If MouseHit(1)=True Then CameraPick(camera,MouseX(),MouseY())  
RenderWorld  
Text 0,0,"Use cursor keys to move about"  
Text 0,20,"Press left mouse button to use CameraPick with mouse coordinates"  
Text 0,40,"PickedX: "+PickedX#()  
Text 0,60,"PickedY: "+PickedY#()  
Text 0,80,"PickedZ: "+PickedZ#()  
Text 0,100,"PickedNX: "+PickedNX#()  
Text 0,120,"PickedNY: "+PickedNY#()  
Text 0,140,"PickedNZ: "+PickedNZ#()  
Text 0,160,"PickedTime: "+PickedTime#()  
Text 0,180,"PickedEntity: "+PickedEntity()  
Text 0,200,"PickedSurface: "+PickedSurface()  
Text 0,220,"PickedTriangle: "+PickedTriangle()  
Flip  
Wend  
End  
end function
;; Returns the x component of the normal of the most recently executed Pick  command. This might have been CameraPick, EntityPick or LinePick.
;;param None.
function PickedNX ( )
; PickedNX Example  
; ----------------  
Graphics3D 640,480,0,2  
SetBuffer BackBuffer()  
camera=CreateCamera()  
PositionEntity camera,0,2,-10  
light=CreateLight()  
RotateEntity light,90,0,0  
plane=CreatePlane()  
EntityPickMode plane,2 ; Make the plane entity 'pickable'. Use pick_geometry  mode no.2 for polygon collision.  
ground_tex=LoadTexture("media/Chorme-2.bmp")  
EntityTexture plane,ground_tex  
cube=CreateCube()  
EntityPickMode cube,2 ; Make the cube entity 'pickable'. Use pick_geometry mode  no.2 for polygon collision.  
cube_tex=LoadTexture("media/b3dlogo.jpg")  
EntityTexture cube,cube_tex  
PositionEntity cube,0,1,0  
While Not KeyDown( 1 )  
If KeyDown( 205 )=True Then TurnEntity camera,0,-1,0  
If KeyDown( 203 )=True Then TurnEntity camera,0,1,0  
If KeyDown( 208 )=True Then MoveEntity camera,0,0,-0.05  
If KeyDown( 200 )=True Then MoveEntity camera,0,0,0.05  
; If left mouse button hit then use CameraPick with mouse coordinates  
; In this example, only three things can be picked: the plane, the cube, or  nothing  
If MouseHit(1)=True Then CameraPick(camera,MouseX(),MouseY())  
RenderWorld  
Text 0,0,"Use cursor keys to move about"  
Text 0,20,"Press left mouse button to use CameraPick with mouse coordinates"  
Text 0,40,"PickedX: "+PickedX#()  
Text 0,60,"PickedY: "+PickedY#()  
Text 0,80,"PickedZ: "+PickedZ#()  
Text 0,100,"PickedNX: "+PickedNX#()  
Text 0,120,"PickedNY: "+PickedNY#()  
Text 0,140,"PickedNZ: "+PickedNZ#()  
Text 0,160,"PickedTime: "+PickedTime#()  
Text 0,180,"PickedEntity: "+PickedEntity()  
Text 0,200,"PickedSurface: "+PickedSurface()  
Text 0,220,"PickedTriangle: "+PickedTriangle()  
Flip  
Wend  
End  
end function
;; Returns the y component of the normal of the most recently executed Pick  command. This might have been CameraPick, EntityPick or LinePick.
;;param None.
function PickedNY ( )
; PickedNY Example  
; ----------------  
Graphics3D 640,480,0,2  
SetBuffer BackBuffer()  
camera=CreateCamera()  
PositionEntity camera,0,2,-10  
light=CreateLight()  
RotateEntity light,90,0,0  
plane=CreatePlane()  
EntityPickMode plane,2 ; Make the plane entity 'pickable'. Use pick_geometry  mode no.2 for polygon collision.  
ground_tex=LoadTexture("media/Chorme-2.bmp")  
EntityTexture plane,ground_tex  
cube=CreateCube()  
EntityPickMode cube,2 ; Make the cube entity 'pickable'. Use pick_geometry mode  no.2 for polygon collision.  
cube_tex=LoadTexture("media/b3dlogo.jpg")  
EntityTexture cube,cube_tex  
PositionEntity cube,0,1,0  
While Not KeyDown( 1 )  
If KeyDown( 205 )=True Then TurnEntity camera,0,-1,0  
If KeyDown( 203 )=True Then TurnEntity camera,0,1,0  
If KeyDown( 208 )=True Then MoveEntity camera,0,0,-0.05  
If KeyDown( 200 )=True Then MoveEntity camera,0,0,0.05  
; If left mouse button hit then use CameraPick with mouse coordinates  
; In this example, only three things can be picked: the plane, the cube, or  nothing  
If MouseHit(1)=True Then CameraPick(camera,MouseX(),MouseY())  
RenderWorld  
Text 0,0,"Use cursor keys to move about"  
Text 0,20,"Press left mouse button to use CameraPick with mouse coordinates"  
Text 0,40,"PickedX: "+PickedX#()  
Text 0,60,"PickedY: "+PickedY#()  
Text 0,80,"PickedZ: "+PickedZ#()  
Text 0,100,"PickedNX: "+PickedNX#()  
Text 0,120,"PickedNY: "+PickedNY#()  
Text 0,140,"PickedNZ: "+PickedNZ#()  
Text 0,160,"PickedTime: "+PickedTime#()  
Text 0,180,"PickedEntity: "+PickedEntity()  
Text 0,200,"PickedSurface: "+PickedSurface()  
Text 0,220,"PickedTriangle: "+PickedTriangle()  
Flip  
Wend  
End  
end function
;; Returns the z component of the normal of the most recently executed Pick  command. This might have been CameraPick, EntityPick or LinePick.
;;param None.
function PickedNZ ( )
; PickedNZ Example  
; ----------------  
Graphics3D 640,480,0,2  
SetBuffer BackBuffer()  
camera=CreateCamera()  
PositionEntity camera,0,2,-10  
light=CreateLight()  
RotateEntity light,90,0,0  
plane=CreatePlane()  
EntityPickMode plane,2 ; Make the plane entity 'pickable'. Use pick_geometry  mode no.2 for polygon collision.  
ground_tex=LoadTexture("media/Chorme-2.bmp")  
EntityTexture plane,ground_tex  
cube=CreateCube()  
EntityPickMode cube,2 ; Make the cube entity 'pickable'. Use pick_geometry mode  no.2 for polygon collision.  
cube_tex=LoadTexture("media/b3dlogo.jpg")  
EntityTexture cube,cube_tex  
PositionEntity cube,0,1,0  
While Not KeyDown( 1 )  
If KeyDown( 205 )=True Then TurnEntity camera,0,-1,0  
If KeyDown( 203 )=True Then TurnEntity camera,0,1,0  
If KeyDown( 208 )=True Then MoveEntity camera,0,0,-0.05  
If KeyDown( 200 )=True Then MoveEntity camera,0,0,0.05  
; If left mouse button hit then use CameraPick with mouse coordinates  
; In this example, only three things can be picked: the plane, the cube, or  nothing  
If MouseHit(1)=True Then CameraPick(camera,MouseX(),MouseY())  
RenderWorld  
Text 0,0,"Use cursor keys to move about"  
Text 0,20,"Press left mouse button to use CameraPick with mouse coordinates"  
Text 0,40,"PickedX: "+PickedX#()  
Text 0,60,"PickedY: "+PickedY#()  
Text 0,80,"PickedZ: "+PickedZ#()  
Text 0,100,"PickedNX: "+PickedNX#()  
Text 0,120,"PickedNY: "+PickedNY#()  
Text 0,140,"PickedNZ: "+PickedNZ#()  
Text 0,160,"PickedTime: "+PickedTime#()  
Text 0,180,"PickedEntity: "+PickedEntity()  
Text 0,200,"PickedSurface: "+PickedSurface()  
Text 0,220,"PickedTriangle: "+PickedTriangle()  
Flip  
Wend  
End  
end function
;; Returns the handle of the surface that was 'picked' by the most recently  executed Pick command. This might have been CameraPick, EntityPick or LinePick.
;;param None.
function PickedSurface ( )
; PickedSurface Example  
; ---------------------  
Graphics3D 640,480,0,2  
SetBuffer BackBuffer()  
camera=CreateCamera()  
PositionEntity camera,0,2,-10  
light=CreateLight()  
RotateEntity light,90,0,0  
plane=CreatePlane()  
EntityPickMode plane,2 ; Make the plane entity 'pickable'. Use pick_geometry  mode no.2 for polygon collision.  
ground_tex=LoadTexture("media/Chorme-2.bmp")  
EntityTexture plane,ground_tex  
cube=CreateCube()  
EntityPickMode cube,2 ; Make the cube entity 'pickable'. Use pick_geometry mode  no.2 for polygon collision.  
cube_tex=LoadTexture("media/b3dlogo.jpg")  
EntityTexture cube,cube_tex  
PositionEntity cube,0,1,0  
While Not KeyDown( 1 )  
If KeyDown( 205 )=True Then TurnEntity camera,0,-1,0  
If KeyDown( 203 )=True Then TurnEntity camera,0,1,0  
If KeyDown( 208 )=True Then MoveEntity camera,0,0,-0.05  
If KeyDown( 200 )=True Then MoveEntity camera,0,0,0.05  
; If left mouse button hit then use CameraPick with mouse coordinates  
; In this example, only three things can be picked: the plane, the cube, or  nothing  
If MouseHit(1)=True Then CameraPick(camera,MouseX(),MouseY())  
RenderWorld  
Text 0,0,"Use cursor keys to move about"  
Text 0,20,"Press left mouse button to use CameraPick with mouse coordinates"  
Text 0,40,"PickedX: "+PickedX#()  
Text 0,60,"PickedY: "+PickedY#()  
Text 0,80,"PickedZ: "+PickedZ#()  
Text 0,100,"PickedNX: "+PickedNX#()  
Text 0,120,"PickedNY: "+PickedNY#()  
Text 0,140,"PickedNZ: "+PickedNZ#()  
Text 0,160,"PickedTime: "+PickedTime#()  
Text 0,180,"PickedEntity: "+PickedEntity()  
Text 0,200,"PickedSurface: "+PickedSurface()  
Text 0,220,"PickedTriangle: "+PickedTriangle()  
Flip  
Wend  
End  
end function
;; Returns the time taken to calculate the most recently executed Pick command.  This might have been CameraPick, EntityPick or LinePick.
;;param None.
function PickedTime ( )
; PickedTime Example  
; ------------------  
Graphics3D 640,480,0,2  
SetBuffer BackBuffer()  
camera=CreateCamera()  
PositionEntity camera,0,2,-10  
light=CreateLight()  
RotateEntity light,90,0,0  
plane=CreatePlane()  
EntityPickMode plane,2 ; Make the plane entity 'pickable'. Use pick_geometry  mode no.2 for polygon collision.  
ground_tex=LoadTexture("media/Chorme-2.bmp")  
EntityTexture plane,ground_tex  
cube=CreateCube()  
EntityPickMode cube,2 ; Make the cube entity 'pickable'. Use pick_geometry mode  no.2 for polygon collision.  
cube_tex=LoadTexture("media/b3dlogo.jpg")  
EntityTexture cube,cube_tex  
PositionEntity cube,0,1,0  
While Not KeyDown( 1 )  
If KeyDown( 205 )=True Then TurnEntity camera,0,-1,0  
If KeyDown( 203 )=True Then TurnEntity camera,0,1,0  
If KeyDown( 208 )=True Then MoveEntity camera,0,0,-0.05  
If KeyDown( 200 )=True Then MoveEntity camera,0,0,0.05  
; If left mouse button hit then use CameraPick with mouse coordinates  
; In this example, only three things can be picked: the plane, the cube, or  nothing  
If MouseHit(1)=True Then CameraPick(camera,MouseX(),MouseY())  
RenderWorld  
Text 0,0,"Use cursor keys to move about"  
Text 0,20,"Press left mouse button to use CameraPick with mouse coordinates"  
Text 0,40,"PickedX: "+PickedX#()  
Text 0,60,"PickedY: "+PickedY#()  
Text 0,80,"PickedZ: "+PickedZ#()  
Text 0,100,"PickedNX: "+PickedNX#()  
Text 0,120,"PickedNY: "+PickedNY#()  
Text 0,140,"PickedNZ: "+PickedNZ#()  
Text 0,160,"PickedTime: "+PickedTime#()  
Text 0,180,"PickedEntity: "+PickedEntity()  
Text 0,200,"PickedSurface: "+PickedSurface()  
Text 0,220,"PickedTriangle: "+PickedTriangle()  
Flip  
Wend  
End  
end function
;; Returns the index number of the triangle that was 'picked' by the most recently  executed Pick command. This might have been CameraPick, EntityPick or LinePick.
;;param None.
function PickedTriangle ( )
; PickedTriangle Example  
; ----------------------  
Graphics3D 640,480,0,2  
SetBuffer BackBuffer()  
camera=CreateCamera()  
PositionEntity camera,0,2,-10  
light=CreateLight()  
RotateEntity light,90,0,0  
plane=CreatePlane()  
EntityPickMode plane,2 ; Make the plane entity 'pickable'. Use pick_geometry  mode no.2 for polygon collision.  
ground_tex=LoadTexture("media/Chorme-2.bmp")  
EntityTexture plane,ground_tex  
cube=CreateCube()  
EntityPickMode cube,2 ; Make the cube entity 'pickable'. Use pick_geometry mode  no.2 for polygon collision.  
cube_tex=LoadTexture("media/b3dlogo.jpg")  
EntityTexture cube,cube_tex  
PositionEntity cube,0,1,0  
While Not KeyDown( 1 )  
If KeyDown( 205 )=True Then TurnEntity camera,0,-1,0  
If KeyDown( 203 )=True Then TurnEntity camera,0,1,0  
If KeyDown( 208 )=True Then MoveEntity camera,0,0,-0.05  
If KeyDown( 200 )=True Then MoveEntity camera,0,0,0.05  
; If left mouse button hit then use CameraPick with mouse coordinates  
; In this example, only three things can be picked: the plane, the cube, or  nothing  
If MouseHit(1)=True Then CameraPick(camera,MouseX(),MouseY())  
RenderWorld  
Text 0,0,"Use cursor keys to move about"  
Text 0,20,"Press left mouse button to use CameraPick with mouse coordinates"  
Text 0,40,"PickedX: "+PickedX#()  
Text 0,60,"PickedY: "+PickedY#()  
Text 0,80,"PickedZ: "+PickedZ#()  
Text 0,100,"PickedNX: "+PickedNX#()  
Text 0,120,"PickedNY: "+PickedNY#()  
Text 0,140,"PickedNZ: "+PickedNZ#()  
Text 0,160,"PickedTime: "+PickedTime#()  
Text 0,180,"PickedEntity: "+PickedEntity()  
Text 0,200,"PickedSurface: "+PickedSurface()  
Text 0,220,"PickedTriangle: "+PickedTriangle()  
Flip  
Wend  
End  
end function
;; Returns the world x coordinate of the most recently executed Pick command.  This might have been CameraPick, EntityPick or LinePick.
;; The coordinate represents the exact point of where something was picked.
;; See also: PickedY, PickedZ.
;;param None.
function PickedX# ( )
; PickedX Example  
; ---------------  
Graphics3D 640,480,0,2  
SetBuffer BackBuffer()  
camera=CreateCamera()  
PositionEntity camera,0,2,-10  
light=CreateLight()  
RotateEntity light,90,0,0  
plane=CreatePlane()  
EntityPickMode plane,2 ; Make the plane entity 'pickable'. Use pick_geometry  mode no.2 for polygon collision.  
ground_tex=LoadTexture("media/Chorme-2.bmp")  
EntityTexture plane,ground_tex  
cube=CreateCube()  
EntityPickMode cube,2 ; Make the cube entity 'pickable'. Use pick_geometry mode  no.2 for polygon collision.  
cube_tex=LoadTexture("media/b3dlogo.jpg")  
EntityTexture cube,cube_tex  
PositionEntity cube,0,1,0  
While Not KeyDown( 1 )  
If KeyDown( 205 )=True Then TurnEntity camera,0,-1,0  
If KeyDown( 203 )=True Then TurnEntity camera,0,1,0  
If KeyDown( 208 )=True Then MoveEntity camera,0,0,-0.05  
If KeyDown( 200 )=True Then MoveEntity camera,0,0,0.05  
; If left mouse button hit then use CameraPick with mouse coordinates  
; In this example, only three things can be picked: the plane, the cube, or  nothing  
If MouseHit(1)=True Then CameraPick(camera,MouseX(),MouseY())  
RenderWorld  
Text 0,0,"Use cursor keys to move about"  
Text 0,20,"Press left mouse button to use CameraPick with mouse coordinates"  
Text 0,40,"PickedX: "+PickedX#()  
Text 0,60,"PickedY: "+PickedY#()  
Text 0,80,"PickedZ: "+PickedZ#()  
Text 0,100,"PickedNX: "+PickedNX#()  
Text 0,120,"PickedNY: "+PickedNY#()  
Text 0,140,"PickedNZ: "+PickedNZ#()  
Text 0,160,"PickedTime: "+PickedTime#()  
Text 0,180,"PickedEntity: "+PickedEntity()  
Text 0,200,"PickedSurface: "+PickedSurface()  
Text 0,220,"PickedTriangle: "+PickedTriangle()  
Flip  
Wend  
End  
end function
;; Returns the world y coordinate of the most recently executed Pick command.  This might have been CameraPick, EntityPick or LinePick.
;; The coordinate represents the exact point of where something was picked.
;; See also: PickedX, PickedZ.
;;param None.
function PickedY# ( )
; PickedY Example  
; ---------------  
Graphics3D 640,480,0,2  
SetBuffer BackBuffer()  
camera=CreateCamera()  
PositionEntity camera,0,2,-10  
light=CreateLight()  
RotateEntity light,90,0,0  
plane=CreatePlane()  
EntityPickMode plane,2 ; Make the plane entity 'pickable'. Use pick_geometry  mode no.2 for polygon collision.  
ground_tex=LoadTexture("media/Chorme-2.bmp")  
EntityTexture plane,ground_tex  
cube=CreateCube()  
EntityPickMode cube,2 ; Make the cube entity 'pickable'. Use pick_geometry mode  no.2 for polygon collision.  
cube_tex=LoadTexture("media/b3dlogo.jpg")  
EntityTexture cube,cube_tex  
PositionEntity cube,0,1,0  
While Not KeyDown( 1 )  
If KeyDown( 205 )=True Then TurnEntity camera,0,-1,0  
If KeyDown( 203 )=True Then TurnEntity camera,0,1,0  
If KeyDown( 208 )=True Then MoveEntity camera,0,0,-0.05  
If KeyDown( 200 )=True Then MoveEntity camera,0,0,0.05  
; If left mouse button hit then use CameraPick with mouse coordinates  
; In this example, only three things can be picked: the plane, the cube, or  nothing  
If MouseHit(1)=True Then CameraPick(camera,MouseX(),MouseY())  
RenderWorld  
Text 0,0,"Use cursor keys to move about"  
Text 0,20,"Press left mouse button to use CameraPick with mouse coordinates"  
Text 0,40,"PickedX: "+PickedX#()  
Text 0,60,"PickedY: "+PickedY#()  
Text 0,80,"PickedZ: "+PickedZ#()  
Text 0,100,"PickedNX: "+PickedNX#()  
Text 0,120,"PickedNY: "+PickedNY#()  
Text 0,140,"PickedNZ: "+PickedNZ#()  
Text 0,160,"PickedTime: "+PickedTime#()  
Text 0,180,"PickedEntity: "+PickedEntity()  
Text 0,200,"PickedSurface: "+PickedSurface()  
Text 0,220,"PickedTriangle: "+PickedTriangle()  
Flip  
Wend  
End  
end function
;; Returns the world z coordinate of the most recently executed Pick command.  This might have been CameraPick, EntityPick or LinePick.
;; The coordinate represents the exact point of where something was picked.
;; See also: PickedX, PickedY.
;;param None.
function PickedZ# ( )
; PickedZ Example  
; ---------------  
Graphics3D 640,480,0,2  
SetBuffer BackBuffer()  
camera=CreateCamera()  
PositionEntity camera,0,2,-10  
light=CreateLight()  
RotateEntity light,90,0,0  
plane=CreatePlane()  
EntityPickMode plane,2 ; Make the plane entity 'pickable'. Use pick_geometry  mode no.2 for polygon collision.  
ground_tex=LoadTexture("media/Chorme-2.bmp")  
EntityTexture plane,ground_tex  
cube=CreateCube()  
EntityPickMode cube,2 ; Make the cube entity 'pickable'. Use pick_geometry mode  no.2 for polygon collision.  
cube_tex=LoadTexture("media/b3dlogo.jpg")  
EntityTexture cube,cube_tex  
PositionEntity cube,0,1,0  
While Not KeyDown( 1 )  
If KeyDown( 205 )=True Then TurnEntity camera,0,-1,0  
If KeyDown( 203 )=True Then TurnEntity camera,0,1,0  
If KeyDown( 208 )=True Then MoveEntity camera,0,0,-0.05  
If KeyDown( 200 )=True Then MoveEntity camera,0,0,0.05  
; If left mouse button hit then use CameraPick with mouse coordinates  
; In this example, only three things can be picked: the plane, the cube, or  nothing  
If MouseHit(1)=True Then CameraPick(camera,MouseX(),MouseY())  
RenderWorld  
Text 0,0,"Use cursor keys to move about"  
Text 0,20,"Press left mouse button to use CameraPick with mouse coordinates"  
Text 0,40,"PickedX: "+PickedX#()  
Text 0,60,"PickedY: "+PickedY#()  
Text 0,80,"PickedZ: "+PickedZ#()  
Text 0,100,"PickedNX: "+PickedNX#()  
Text 0,120,"PickedNY: "+PickedNY#()  
Text 0,140,"PickedNZ: "+PickedNZ#()  
Text 0,160,"PickedTime: "+PickedTime#()  
Text 0,180,"PickedEntity: "+PickedEntity()  
Text 0,200,"PickedSurface: "+PickedSurface()  
Text 0,220,"PickedTriangle: "+PickedTriangle()  
Flip  
Wend  
End  
end function
;; Points one entity at another.
;; The optional roll parameter allows you to  specify a roll angle as pointing an entity only sets pitch and yaw angles.
;; If you wish for an entity to point at a certain position rather than another  entity, simply create a pivot entity at your desired position, point the entity  at this and then free the pivot.
;; Click <a href=http://www.blitzbasic.co.nz/b3ddocs/command.php?name=PointEntity&ref=comments target=_blank>here</a> to view the latest version of this page online</body>
;; </html>
;;param entity - entity handle
;;param target - target entity handle
;;param roll# (optional) - roll angle of entity
function PointEntity entity,target[,roll#]
end function
;; Positions an entity at an absolute position in 3D space.
;; Entities are positioned using an x,y,z coordinate system. x, y and z each have their own axis, and each axis has its own set of values. By specifying a value for each axis, you can position an entity anywhere in 3D space. 0,0,0 is the centre of 3D space, and if the camera is pointing in the default positive z direction, then positioning an entity with a z value of above 0 will make it appear in front of the camera, whereas a negative z value would see it disappear behind the camera. Changing the x value would see it moving sideways, and changing the y value would see it moving up/down.
;; Of course, the direction in which entities appear to move is relative to the position and orientation of the camera.
;; See also: <a class=small href=MoveEntity.htm>MoveEntity</a>, <a class=small href=TranslateEntity.htm>TranslateEntity</a>, <a class=small href=PositionMesh.htm>PositionMesh</a>.
;;param entity - name of entity to be positioned
;;param x# - x co-ordinate that entity will be positioned at
;;param y# - y co-ordinate that entity will be positioned at
;;param z# - z co-ordinate that entity will be positioned at
;;param global (optional) - true if the position should be relative to 0,0,0 rather than a parent entity's position. False by default.
function PositionEntity entity,x#,y#,z#,[,global]
; PositionEntity Example  
; ----------------------  
Graphics3D 640,480  
SetBuffer BackBuffer()  
camera=CreateCamera()  
light=CreateLight()  
cone=CreateCone( 32 )  
; Set position values so that cone is positioned in front of camera, so we  can see it to begin with  
x#=0  
y#=0  
z#=10  
While Not KeyDown( 1 )  
; Change position values depending on key pressed  
If KeyDown( 203 )=True Then x#=x#-0.1  
If KeyDown( 205 )=True Then x#=x#+0.1  
If KeyDown( 208 )=True Then y#=y#-0.1  
If KeyDown( 200 )=True Then y#=y#+0.1  
If KeyDown( 44 )=True Then z#=z#-0.1  
If KeyDown( 30 )=True Then z#=z#+0.1  
; Position cone using position values  
PositionEntity cone,x#,y#,z#  
RenderWorld  
Text 0,0,"Use cursor/A/Z keys to change cone position"  
Text 0,20,"X Position: "+x#  
Text 0,40,"Y Position: "+y#  
Text 0,60,"Z Position: "+z#  
Flip  
Wend  
End  
end function
;; Moves all vertices of a mesh.
;; See also: <a class=small href=PositionEntity.htm>PositionEntity</a>, <a class=small href=MoveEntity.htm>MoveEntity</a>, <a class=small href=TranslateEntity.htm>TranslateEntity</a>.
;;param mesh - mesh handle
;;param x# - x position of mesh
;;param y# - y position of mesh
;;param z# - z position of mesh
function PositionMesh mesh,x#,y#,z#
; PositionMesh Example  
; --------------------  
; In this example we will demonstrate the use of the PositionMesh command.  
; Unlike PositionEntity, PositionMesh actually modifies the actual mesh structure.  
; So whereas using PositionEntity 0,0,1 would only move an entity by one unit the first time it was  
; used, PositionMesh 0,0,1 will move the mesh by one unit every time it is used.  
; This is because PositionEntity positions an entity based on a fixed mesh structure, whereas  
; PositionMesh actually modifies the mesh structure itself.  
Graphics3D 640,480  
SetBuffer BackBuffer()  
camera=CreateCamera()  
light=CreateLight()  
; Create cube mesh  
cube=CreateCube()  
; Position cube in front of camera so we can see it  
PositionEntity cube,0,0,5  
While Not KeyDown(1)  
; If space bar pressed then position mesh 1 unit along the z axis. Also set syntax$ text.  
If KeyHit(57)=True Then PositionMesh cube,0,0,1 : syntax$="PositionMesh 0,0,1"  
RenderWorld  
Text 0,0,"Press space to position the mesh 1 unit along the z axis"  
Text 0,20,syntax$  
Flip  
Wend  
End  
end function
;; Positions a texture at an absolute position.
;; This will have an  immediate effect on all instances of the texture being used.
;; Positioning a texture is useful for performing scrolling texture effects,  such as for water etc.
;;param texture - texture handle
;;param u_position# - u position of texture
;;param v_position# - v position of texture
function PositionTexture texture,u_position#,v_position#
; PositionTexture Example  
; -----------------------  
Graphics3D 640,480  
SetBuffer BackBuffer()  
camera=CreateCamera()  
light=CreateLight()  
RotateEntity light,90,0,0  
cube=CreateCube()  
PositionEntity cube,0,0,5  
; Load texture  
tex=LoadTexture( "media/b3dlogo.jpg" )  
; Texture cube  
EntityTexture cube,tex  
; Set initial uv position values  
u_position#=1  
v_position#=1  
While Not KeyDown( 1 )  
; Change uv position values depending on key pressed  
If KeyDown( 208 )=True Then u_position#=u_position#-0.01  
If KeyDown( 200 )=True Then u_position#=u_position#+0.01  
If KeyDown( 203 )=True Then v_position#=v_position#-0.01  
If KeyDown( 205 )=True Then v_position#=v_position#+0.01  
; Position texture  
PositionTexture tex,u_position#,v_position#  
TurnEntity cube,0.1,0.1,0.1  
RenderWorld  
Text 0,0,"Use cursor keys to change uv position values"  
Text 0,20,"u_position#="+u_position#  
Text 0,40,"v_position#="+v_position#  
Flip  
Wend  
End  
end function
;; Returns the viewport x coordinate of the most recently executed CameraProject.
;;param None.
function ProjectedX# ( )
; ProjectedX Example  
; ------------------  
Graphics3D 640,480  
SetBuffer BackBuffer()  
camera=CreateCamera()  
PositionEntity camera,0,2,-10  
light=CreateLight()  
RotateEntity light,90,0,0  
plane=CreatePlane()  
ground_tex=LoadTexture("media/Chorme-2.bmp")  
EntityTexture plane,ground_tex  
cube=CreateCube()  
cube_tex=LoadTexture("media/b3dlogo.jpg")  
EntityTexture cube,cube_tex  
PositionEntity cube,0,1,0  
While Not KeyDown( 1 )  
If KeyDown( 205 )=True Then TurnEntity camera,0,-1,0  
If KeyDown( 203 )=True Then TurnEntity camera,0,1,0  
If KeyDown( 208 )=True Then MoveEntity camera,0,0,-0.05  
If KeyDown( 200 )=True Then MoveEntity camera,0,0,0.05  
; Use camera project to get 2D coordinates from 3D coordinates of cube  
CameraProject(camera,EntityX(cube),EntityY(cube),EntityZ(cube))  
RenderWorld  
; If cube is in view then draw text, if not then draw nothing otherwise text  will be drawn at 0,0  
If EntityInView(cube,camera)=True  
; Use ProjectedX() and ProjectedY() to get 2D coordinates from when CameraProject  was used.  
; Use these coordinates to draw text at a 2D position, on top of a 3D scene.  
Text ProjectedX#(),ProjectedY#(),"Cube"  
EndIf  
Text 0,0,"Use cursor keys to move about"  
Text 0,20,"ProjectedX: "+ProjectedX#()  
Text 0,40,"ProjectedY: "+ProjectedY#()  
Text 0,60,"ProjectedZ: "+ProjectedZ#()  
Text 0,80,"EntityInView: "+EntityInView(cube,camera)  
Flip  
Wend  
End  
end function
;; Returns the viewport y coordinate of the most recently executed CameraProject.
;;param None.
function ProjectedY# ( )
; ProjectedY Example  
; ------------------  
Graphics3D 640,480  
SetBuffer BackBuffer()  
camera=CreateCamera()  
PositionEntity camera,0,2,-10  
light=CreateLight()  
RotateEntity light,90,0,0  
plane=CreatePlane()  
ground_tex=LoadTexture("media/Chorme-2.bmp")  
EntityTexture plane,ground_tex  
cube=CreateCube()  
cube_tex=LoadTexture("media/b3dlogo.jpg")  
EntityTexture cube,cube_tex  
PositionEntity cube,0,1,0  
While Not KeyDown( 1 )  
If KeyDown( 205 )=True Then TurnEntity camera,0,-1,0  
If KeyDown( 203 )=True Then TurnEntity camera,0,1,0  
If KeyDown( 208 )=True Then MoveEntity camera,0,0,-0.05  
If KeyDown( 200 )=True Then MoveEntity camera,0,0,0.05  
; Use camera project to get 2D coordinates from 3D coordinates of cube  
CameraProject(camera,EntityX(cube),EntityY(cube),EntityZ(cube))  
RenderWorld  
; If cube is in view then draw text, if not then draw nothing otherwise text  will be drawn at 0,0  
If EntityInView(cube,camera)=True  
; Use ProjectedX() and ProjectedY() to get 2D coordinates from when CameraProject  was used.  
; Use these coordinates to draw text at a 2D position, on top of a 3D scene.  
Text ProjectedX#(),ProjectedY#(),"Cube"  
EndIf  
Text 0,0,"Use cursor keys to move about"  
Text 0,20,"ProjectedX: "+ProjectedX#()  
Text 0,40,"ProjectedY: "+ProjectedY#()  
Text 0,60,"ProjectedZ: "+ProjectedZ#()  
Text 0,80,"EntityInView: "+EntityInView(cube,camera)  
Flip  
Wend  
End  
end function
;; Returns the viewport z coordinate of the most recently executed CameraProject.
;;param None.
function ProjectedZ# ( )
; ProjectedZ Example  
; ------------------  
Graphics3D 640,480  
SetBuffer BackBuffer()  
camera=CreateCamera()  
PositionEntity camera,0,2,-10  
light=CreateLight()  
RotateEntity light,90,0,0  
plane=CreatePlane()  
ground_tex=LoadTexture("media/Chorme-2.bmp")  
EntityTexture plane,ground_tex  
cube=CreateCube()  
cube_tex=LoadTexture("media/b3dlogo.jpg")  
EntityTexture cube,cube_tex  
PositionEntity cube,0,1,0  
While Not KeyDown( 1 )  
If KeyDown( 205 )=True Then TurnEntity camera,0,-1,0  
If KeyDown( 203 )=True Then TurnEntity camera,0,1,0  
If KeyDown( 208 )=True Then MoveEntity camera,0,0,-0.05  
If KeyDown( 200 )=True Then MoveEntity camera,0,0,0.05  
; Use camera project to get 2D coordinates from 3D coordinates of cube  
CameraProject(camera,EntityX(cube),EntityY(cube),EntityZ(cube))  
RenderWorld  
; If cube is in view then draw text, if not then draw nothing otherwise text  will be drawn at 0,0  
If EntityInView(cube,camera)=True  
; Use ProjectedX() and ProjectedY() to get 2D coordinates from when CameraProject  was used.  
; Use these coordinates to draw text at a 2D position, on top of a 3D scene.  
Text ProjectedX#(),ProjectedY#(),"Cube"  
EndIf  
Text 0,0,"Use cursor keys to move about"  
Text 0,20,"ProjectedX: "+ProjectedX#()  
Text 0,40,"ProjectedY: "+ProjectedY#()  
Text 0,60,"ProjectedZ: "+ProjectedZ#()  
Text 0,80,"EntityInView: "+EntityInView(cube,camera)  
Flip  
Wend  
End  
end function
;; Renders the current scene to the BackBuffer onto the rectangle defined by each cameras CameraViewport( ). Every camera not hidden by HideEntity( ) or with a CameraProjMode( ) of 0 is rendered. Rendering to other buffers is currently not supported by Blitz3D.
;; The optional tween parameter should only be specified when RenderWorld is used in conjunction with CaptureWorld. CaptureWorld is used to store the 'old' position, rotation and scale, alpha and colour of each entity in the game world, and a tween value of < 1 will interpolate between these 'old' values and the 'current' ones. A tween value of 0 will render all entities at their state when CaptureWorld was last called, and a tween value of 1 will render all entities at their current state.
;; The use of tweening allows you to render more than one frame per game logic update, while still keeping the display smooth. This allows you to cut down on the CPU time that would be required to update your game logic every render. Note, however, that the bottleneck in almost all 3D applications is the graphics card and the CPU time involved in updating game logic is often very little. A good alternative to render tweening is the use of a delta time, that is, moving your entities each frame depending on the time it took for the program to process and render that frame.
;; Render tweening is quite an advanced technique, and it is not necessary to  use it, so don't worry if you don't quite understand it. See the castle demo  included in the mak (nickname of Mark Sibly, author of Blitz3D) directory of  the Blitz3D samples section for a demonstration of render tweening.
;; See also: <a class=small href=CaptureWorld.htm>CaptureWorld</a>, <a class=small href=CameraViewport.htm>CameraViewport</a>, <a class=small href=CameraProjMode.htm>CameraProjMode</a>.
;; Click <a href=http://www.blitzbasic.co.nz/b3ddocs/command.php?name=RenderWorld&ref=comments target=_blank>here</a> to view the latest version of this page online</body>
;; </html>
;;param tween# (optional) - defaults to 1.
function RenderWorld [tween#]
end function
;; Resets the collision state of an entity.
;; See also: <a class=small href=EntityBox.htm>EntityBox</a>, <a class=small href=EntityRadius.htm>EntityRadius</a>, <a class=small href=Collisions.htm>Collisions</a>, <a class=small href=EntityType.htm>EntityType</a>, <a class=small href=GetEntityType.htm>GetEntityType</a>.
;; Click <a href=http://www.blitzbasic.co.nz/b3ddocs/command.php?name=ResetEntity&ref=comments target=_blank>here</a> to view the latest version of this page online</body>
;; </html>
;;param entity - entity handle
function ResetEntity entity
end function
;; Rotates an entity so that it is at an absolute orientation.
;; Pitch is the same as the x angle of an entity, and is equivalent to tilting forward/backwards.
;; Yaw is the same as the y angle of an entity, and is equivalent to turning left/right.
;; Roll is the same as the z angle of an entity, and is equivalent to tilting left/right.
;; See also: <a class=small href=TurnEntity.htm>TurnEntity</a>, <a class=small href=RotateMesh.htm>RotateMesh</a>.
;;param entity - name of the entity to be rotated
;;param pitch# - angle in degrees of pitch rotation
;;param yaw# - angle in degrees of yaw rotation
;;param roll# - angle in degrees of roll rotation
;;param global (optional) - true if the angle rotated should be relative to 0,0,0 rather than a parent entity's orientation. False by default.
function RotateEntity entity,pitch#,yaw#,roll#,[,global]
; RotateEntity Example  
; --------------------  
Graphics3D 640,480  
SetBuffer BackBuffer()  
camera=CreateCamera()  
light=CreateLight()  
cone=CreateCone( 32 )  
PositionEntity cone,0,0,5  
While Not KeyDown( 1 )  
; Change rotation values depending on the key pressed  
If KeyDown( 208 )=True Then pitch#=pitch#-1  
If KeyDown( 200 )=True Then pitch#=pitch#+1  
If KeyDown( 203 )=True Then yaw#=yaw#-1  
If KeyDown( 205 )=True Then yaw#=yaw#+1  
If KeyDown( 45 )=True Then roll#=roll#-1  
If KeyDown( 44 )=True Then roll#=roll#+1  
; Rotate cone using rotation values  
RotateEntity cone,pitch#,yaw#,roll#  
RenderWorld  
Text 0,0,"Use cursor/Z/X keys to change cone rotation"  
Text 0,20,"Pitch: "+pitch#  
Text 0,40,"Yaw : "+yaw#  
Text 0,60,"Roll : "+roll#  
Flip  
Wend  
End  
end function
;; Rotates all vertices of a mesh by the specified rotation.
;; See also: <a class=small href=RotateEntity.htm>RotateEntity</a>, <a class=small href=TurnEntity.htm>TurnEntity</a>.
;;param mesh - mesh handle
;;param pitch# - pitch of mesh
;;param yaw# - yaw of mesh
;;param roll# - roll of mesh
function RotateMesh mesh,pitch#,yaw#,roll#
; RotateMesh Example  
; ------------------  
; In this example we will demonstrate the use of the RotateMesh command.  
; Unlike RotateEntity, RotateMesh actually modifies the actual mesh structure.  
; So whereas using RotateEntity 0,45,0 would only rotate an entity by 45 degrees the first time it was  
; used, RotateMesh 0,45,0 will rotate the mesh every time it is used.  
; This is because RotateEntity rotates an entity based on a fixed mesh structure, whereas RotateMesh  
; actually modifies the mesh structure itself.  
Graphics3D 640,480  
SetBuffer BackBuffer()  
camera=CreateCamera()  
light=CreateLight()  
; Rotate light to give better lighting of cube  
RotateEntity light,60,30,0  
; Create cube mesh  
cube=CreateCube()  
; Position cube in front of camera so we can see it  
PositionEntity cube,0,0,5  
While Not KeyDown(1)  
; If space bar pressed then rotate mesh by 45 degrees on the y axis. Also set syntax$ text.  
If KeyHit(57)=True Then RotateMesh cube,0,45,0 : syntax$="RotateMesh 0,45,0"  
RenderWorld  
Text 0,0,"Press space to rotate mesh by 45 degrees on the y axis"  
Text 0,20,syntax$  
Flip  
Wend  
End  
end function
;; Rotates a sprite.
;; See also: <a class=small href=CreateSprite.htm>CreateSprite</a>, <a class=small href=LoadSprite.htm>LoadSprite</a>.
;;param sprite - sprite handle
;;param angle# - absolute angle of sprite rotation
function RotateSprite sprite,angle#
Graphics3D 640,480  
cam = CreateCamera()  
MoveEntity cam,0,0,-5  
sp = CreateSprite()  
ang# = 0  
While Not KeyDown(1)  
RenderWorld:Flip  
RotateSprite sp,ang  
ang = ang +3  
Wend  
End  
end function
;; Rotates a texture.
;; This will have an immediate effect on all instances  of the texture being used.
;; Rotating a texture is useful for performing swirling texture effects,  such as for smoke etc.
;;param texture - texture handle
;;param angle# - absolute angle of texture rotation
function RotateTexture texture,angle#
; RotateTexture Example  
; ---------------------  
Graphics3D 640,480  
SetBuffer BackBuffer()  
camera=CreateCamera()  
light=CreateLight()  
RotateEntity light,90,0,0  
cube=CreateCube()  
PositionEntity cube,0,0,5  
; Load texture  
tex=LoadTexture( "media/b3dlogo.jpg" )  
; Texture cube  
EntityTexture cube,tex  
; Set initial texture angle value  
angle#=1  
While Not KeyDown( 1 )  
; Change texture angle value depending on key pressed  
If KeyDown( 205 )=True Then angle#=angle#-1  
If KeyDown( 203 )=True Then angle#=angle#+1  
; Rotate texture  
RotateTexture tex,angle#  
TurnEntity cube,0.1,0.1,0.1  
RenderWorld  
Text 0,0,"Use left and right cursor keys to change texture angle value"  
Text 0,20,"angle#="+angle#  
Flip  
Wend  
End  
end function
;; Scales an entity so that it is of an absolute size.
;; Scale values of 1,1,1 are the default size when creating/loading entities.
;; Scale values of 2,2,2 will double the size of an entity.
;; Scale values of 0,0,0 will make an entity disappear.
;; Scale values of less than 0,0,0 will invert an entity and make it bigger.
;; See also: <a class=small href=ScaleMesh.htm>ScaleMesh</a>, <a class=small href=FitMesh.htm>FitMesh</a>.
;;param entity - name of the entity to be scaled
;;param x_scale# - x size of entity
;;param y_scale# - y size of entity
;;param z_scale# - z size of entity
;;param global (optional) -
function ScaleEntity entity,x_scale#,y_scale#,z_scalel#,[,global]
; ScaleEntity Example  
; -------------------  
Graphics3D 640,480  
SetBuffer BackBuffer()  
camera=CreateCamera()  
light=CreateLight()  
cone=CreateCone( 32 )  
PositionEntity cone,0,0,5  
; Set scale values so that cone is default size to begin with  
x_scale#=1  
y_scale#=1  
z_scale#=1  
While Not KeyDown( 1 )  
; Change scale values depending on the key pressed  
If KeyDown( 203 )=True Then x_scale#=x_scale#-0.1  
If KeyDown( 205 )=True Then x_scale#=x_scale#+0.1  
If KeyDown( 208 )=True Then y_scale#=y_scale#-0.1  
If KeyDown( 200 )=True Then y_scale#=y_scale#+0.1  
If KeyDown( 44 )=True Then z_scale#=z_scale#-0.1  
If KeyDown( 30 )=True Then z_scale#=z_scale#+0.1  
; Scale cone using scale values  
ScaleEntity cone,x_scale#,y_scale#,z_scale#  
RenderWorld  
Text 0,0,"Use cursor/A/Z keys to scale cone"  
Text 0,20,"X Scale: "+x_scale#  
Text 0,40,"Y Scale: "+y_scale#  
Text 0,60,"Z Scale: "+z_scale#  
Flip  
Wend  
End  
end function
;; Scales all vertices of a mesh by the specified scaling factors.
;; See also: <a class=small href=FitMesh.htm>FitMesh</a>, <a class=small href=ScaleEntity.htm>ScaleEntity</a>.
;;param mesh - mesh handle
;;param x_scale# - x scale of mesh
;;param y_scale# - y scale of mesh
;;param z_scale# - z scale of mesh
function ScaleMesh mesh,x_scale#,y_scale#,z_scale#
; ScaleMesh Example  
; -----------------  
; In this example we will demonstrate the use of the ScaleMesh command.  
; Unlike ScaleEntity, ScaleMesh actually modifies the actual mesh structure.  
; So whereas using ScaleEntity 2,2,2 would only double the size of an entity the first time it was  
; used, ScaleMesh 2,2,2 will double the size of the mesh every time it is used.  
; This is because ScaleEntity scales an entity based on a fixed mesh structure, whereas ScaleMesh  
; actually modifies the mesh structure itself.  
Graphics3D 640,480  
SetBuffer BackBuffer()  
camera=CreateCamera()  
light=CreateLight()  
; Create cube mesh  
cube=CreateCube()  
; Position cube in front of camera so we can see it  
PositionEntity cube,0,0,5  
While Not KeyDown(1)  
; If space bar pressed then scale cube mesh by 1%. Also set syntax$ text.  
If KeyHit(57)=True Then ScaleMesh cube,1.01,1.01,1.01 : syntax$="ScaleMesh 1.01,1.01,1.01"  
RenderWorld  
Text 0,0,"Press space to scale mesh by 1%"  
Text 0,20,syntax$  
Flip  
Wend  
End  
end function
;; Scales a sprite.
;; See also: <a class=small href=LoadSprite.htm>LoadSprite</a>, <a class=small href=CreateSprite.htm>CreateSprite</a>.
;;param sprite - sprite handle
;;param x_scale# - x scale of sprite
;;param y scale# - y scale of sprite
function ScaleSprite sprite,x_scale#,y_scale#
Graphics3D 640,480  
cam = CreateCamera()  
MoveEntity cam,0,0,-5  
sp = CreateSprite()  
size# = 1.0  
While Not KeyDown(1)  
RenderWorld:Flip  
ScaleSprite sp,size,size  
size = size + 0.01  
Wend  
End  
end function
;; Scales a texture by an absolute amount.
;; This will have an immediate  effect on all instances of the texture being used.
;;param texture - name of texture
;;param u_scale# - u scale
;;param v_scale# - v scale
function ScaleTexture texture,u_scale#,v_scale#
; ScaleTexture Example  
; --------------------  
Graphics3D 640,480  
SetBuffer BackBuffer()  
camera=CreateCamera()  
light=CreateLight()  
RotateEntity light,90,0,0  
cube=CreateCube()  
PositionEntity cube,0,0,5  
; Load texture  
tex=LoadTexture( "media/b3dlogo.jpg" )  
; Texture cube  
EntityTexture cube,tex  
; Set initial uv scale values  
u_scale#=1  
v_scale#=1  
While Not KeyDown( 1 )  
; Change uv scale values depending on key pressed  
If KeyDown( 208 )=True Then u_scale#=u_scale#-0.01  
If KeyDown( 200 )=True Then u_scale#=u_scale#+0.01  
If KeyDown( 203 )=True Then v_scale#=v_scale#-0.01  
If KeyDown( 205 )=True Then v_scale#=v_scale#+0.01  
; Scale texture  
ScaleTexture tex,u_scale#,v_scale#  
TurnEntity cube,0.1,0.1,0.1  
RenderWorld  
Text 0,0,"Use cursor keys to change uv scale values"  
Text 0,20,"u_scale#="+u_scale#  
Text 0,40,"v_scale#="+v_scale#  
Flip  
Wend  
End  
end function
;; Sets an animation key for the specified entity at the specified frame.  The entity must have a valid animation sequence to work with.
;; This is most useful when you've got a character, or a complete set of complicated moves to perform, and you want to perform them en-masse.
;;param entity - entity handle
;;param frame - frame of animation to be used as anim key
;;param pos_key (optional) - true to include entity position information when setting  key. Defaults to true.
;;param rot_key (optional) - true to include entity rotation information when setting  key. Defaults to true.
;;param scale_key (optional) - true to include entity scale information when setting  key. Defaults to true.
function SetAnimKey entity,frame[,pos_key][,rot_key][,scale_key]
;Create 3d animation example  
;Set up a simple nice looking level  
Graphics3D 640,480  
camera=CreateCamera()  
PositionEntity camera,0,12,-12  
RotateEntity camera,35,0,0  
light=CreateLight(2)  
PositionEntity light,1000,1000,-1000  
ground=CreatePlane(2)  
EntityAlpha ground,0.5  
EntityColor ground,0,0,255  
mirror=CreateMirror()  
;Lets make a bouncing ball that squashes on impact with the floor.  
ball=CreateSphere(16)  
EntityShininess ball,1  
EntityColor ball,255,0,0  
; Lets animate him and "record" the 3D animation for later playback  
bloat#=0 : flatten#=0 : ypos#=10  
For frame=1 To 10  
;Drop the ball from height 10 to 2  
ypos = ypos - spd#  
spd#=spd#+.2  
PositionEntity ball,0,ypos,0  
ScaleEntity ball,1+bloat,1+flatten,1+bloat  
;If the ball is low enough make it look increasingly squashed  
If frame>8  
bloat=bloat+1.5  
flatten=flatten-.25  
Else  
flatten=flatten+.05  
EndIf  
;Record the frame!  
SetAnimKey ball,frame  
Next  
;Now we need to add the frames we've just made to the sequence of "film"!  
seq = AddAnimSeq(ball,frame-1) ; total number of frames  
;Play it back ping-pong!  
Animate ball,2,0.15  
While Not KeyHit(1)  
UpdateWorld  
RenderWorld  
Flip  
Wend  
End  
end function
;; SetAnimTime allows you to manually animate entities.
;; Click <a href=http://www.blitzbasic.co.nz/b3ddocs/command.php?name=SetAnimTime&ref=comments target=_blank>here</a> to view the latest version of this page online</body>
;; </html>
;;param entity - a valid entity handle.
;;param time# - a floating point time value.
;;param anim_seq - an optional animation sequence number.
function SetAnimTime entity,time#[,anim_seq]
end function
;; Selects a cube face for direct rendering to a texture.
;; This command should only be used when you wish to draw directly to a cubemap texture in real-time. Otherwise, just loading a pre-rendered cubemap with a flag of 128 will suffice.
;; To understand how this command works exactly it is important to recognise that Blitz treats cubemap textures slightly differently to how it treats other textures. Here's how it works...
;; A cubemap texture in Blitz actually consists of six images, each of which must be square 'power' of two size - e.g. 32, 64, 128 etc. Each corresponds to a particular cube face. These images are stored internally by Blitz, and the texture handle that is returned by LoadTexture/CreateTexture when specifying the cubemap flag, only provides access to one of these six images at once (by default the first one, or '0' face).
;; This is why, when loading a cubemap texture into Blitz using LoadTexture, all the six cubemap images must be laid out in a specific order (0-5, as described above), in a horizontal strip. Then Blitz takes this texture and internally converts it into six separate images.
;; So seeing as the texture handle returned by <a class=small href=../3d_commands/CreateTexture.htm>CreateTexture</a> / <a class=small href=../3d_commands/LoadTexture.htm>LoadTexture</a> only provides access to one of these images at once (no. 1 by default), how do we get access to the other five images? This is where SetCubeFace comes in. It will tell Blitz that whenever you next draw to a cubemap texture, to draw to the particular image representing the face you have specified with the face parameter.
;; Now you have the ability to draw to a cubemap in real-time.
;; To give you some idea of how this works in code, here's a function that updates a cubemap in real-time. It works by rendering six different views and copying them to the cubemap texture buffer, using SetCubeFace to specify which particular cubemap image should be drawn to.
;; ; Start of code
;; Function UpdateCubeMap( tex,camera )
;; tex_sz=TextureWidth(tex)
;; ; do left view
;; SetCubeFace tex,0
;; RotateEntity camera,0,90,0
;; RenderWorld
;; ; copy contents of backbuffer to cubemap
;; CopyRect 0,0,tex_sz,tex_sz,0,0,BackBuffer(),TextureBuffer(tex)
;; ; do forward view
;; SetCubeFace tex,1
;; RotateEntity camera,0,0,0
;; RenderWorld
;; CopyRect 0,0,tex_sz,tex_sz,0,0,BackBuffer(),TextureBuffer(tex)
;; ; do right view
;; SetCubeFace tex,2
;; RotateEntity camera,0,-90,0
;; RenderWorld
;; CopyRect 0,0,tex_sz,tex_sz,0,0,BackBuffer(),TextureBuffer(tex)
;; ; do backward view
;; SetCubeFace tex,3
;; RotateEntity camera,0,180,0
;; RenderWorld
;; CopyRect 0,0,tex_sz,tex_sz,0,0,BackBuffer(),TextureBuffer(tex)
;; ; do up view
;; SetCubeFace tex,4
;; RotateEntity camera,-90,0,0
;; RenderWorld
;; CopyRect 0,0,tex_sz,tex_sz,0,0,BackBuffer(),TextureBuffer(tex)
;; ; do down view
;; SetCubeFace tex,5
;; RotateEntity camera,90,0,0
;; RenderWorld
;; CopyRect 0,0,tex_sz,tex_sz,0,0,BackBuffer(),TextureBuffer(tex)
;; EndFunction
;; ; End of code
;; All rendering to a texture buffer affects the currently selected face. Do not change the selected cube face while a buffer is locked.
;; Finally, you may wish to combine the vram 256 flag with the cubic mapping flag when drawing to cubemap textures for faster access.
;; See also: <a class=small href=CreateTexture.htm>CreateTexture</a>, <a class=small href=LoadTexture.htm>LoadTexture</a>, <a class=small href=SetCubeMode.htm>SetCubeMode</a>.
;;param texture - texture
;;param face - face of cube to select. This should be one of the following values:
;;param 0: left (negative X) face
;;param 1: forward (positive Z) face - this is the default.
;;param 2: right (positive X) face
;;param 3: backward (negative Z) face
;;param 4: up (positive Y) face
;;param 5: down (negative Y) face
function SetCubeFace texture,face
; SetCubeFace Example  
; -------------------  
width=640  
height=480  
depth=0  
mode=0  
Graphics3D width,height,depth,mode  
SetBuffer BackBuffer()  
; If user's graphics card does not support cubic mapping then quit example  
If GfxDriverCaps3D()<110 Then RuntimeError "Sorry, your graphics card does not support cubic environemnt maps."  
cam=CreateCamera()  
PositionEntity cam,0,10,-10  
; Create separate camera for updating cube map - this allows us to manipulate main camera and cube camera which avoids any confusion  
cube_cam=CreateCamera()  
HideEntity cube_cam  
light=CreateLight()  
RotateEntity light,90,0,0  
; Load object we will apply cubemap to - the classic teapot  
teapot=LoadMesh("media/teapot.x")  
ScaleEntity teapot,3,3,3  
PositionEntity teapot,0,10,0  
; Create some scenery  
; ground  
ground=CreatePlane()  
EntityColor ground,168,133,55  
ground_tex=LoadTexture("media/sand.bmp")  
ScaleTexture ground_tex,10,10  
EntityTexture ground,ground_tex  
; sky  
sky=CreateSphere(24)  
ScaleEntity sky,500,500,500  
FlipMesh sky  
EntityFX sky,1  
sky_tex=LoadTexture("media/sky.bmp")  
EntityTexture sky,sky_tex  
; cactus  
cactus=LoadMesh("media/cactus2.x")  
FitMesh cactus,-5,0,-5,2,6,.5  
; camel  
camel=LoadMesh("media/camel.x")  
FitMesh camel,5,0,-5,6,5,4  
; Load ufo to give us a dynamic moving object that the cubemap will be able to reflect  
ufo_piv=CreatePivot()  
PositionEntity ufo_piv,0,15,0  
ufo=LoadMesh("media/green_ufo.x",ufo_piv)  
PositionEntity ufo,0,0,10  
; Create texture with color + cubic environment map + store in vram flags  
tex=CreateTexture(256,256,1+128+256)  
; Apply cubic environment map to teapot  
EntityTexture teapot,tex  
While Not KeyDown(1)  
; Control camera  
; mouse look  
mxs#=mxs#+(MouseXSpeed()/5.0)  
mys#=mys#+(MouseYSpeed()/5.0)  
RotateEntity cam,mys#,-mxs#,0  
MoveMouse width/2,height/2  
; move camera forwards/backwards/left/right with cursor keys  
If KeyDown(200)=True Then MoveEntity cam,0,0,.2 ; move camera forward  
If KeyDown(208)=True Then MoveEntity cam,0,0,-.2 ; move camera back  
If KeyDown(205)=True Then MoveEntity cam,.2,0,0 ; move camera left  
If KeyDown(203)=True Then MoveEntity cam,-.2,0,0 ; move camera right  
; Turn ufo pivot, causing child ufo mesh to spin around it (and teapot)  
TurnEntity ufo_piv,0,2,0  
; Hide our main camera before updating cube map - we don't need it to be rendererd every time cube_cam is rendered  
HideEntity cam  
; Update cubemap  
UpdateCubemap(tex,cube_cam,teapot)  
; Show main camera again  
ShowEntity cam  
RenderWorld  
Text 0,0,"Use mouse to look around"  
Text 0,20,"Use cursor keys to change camera position"  
Flip  
Wend  
Function UpdateCubemap(tex,camera,entity)  
tex_sz=TextureWidth(tex)  
; Show the camera we have specifically created for updating the cubemap  
ShowEntity camera  
; Hide entity that will have cubemap applied to it. This is so we can get cubemap from its position, without it blocking the view  
HideEntity entity  
; Position camera where the entity is - this is where we will be rendering views from for cubemap  
PositionEntity camera,EntityX#(entity),EntityY#(entity),EntityZ#(entity)  
CameraClsMode camera,False,True  
; Set the camera's viewport so it is the same size as our texture - so we can fit entire screen contents into texture  
CameraViewport camera,0,0,tex_sz,tex_sz  
; Update cubemap  
; do left view  
SetCubeFace tex,0  
RotateEntity camera,0,90,0  
RenderWorld  
CopyRect 0,0,tex_sz,tex_sz,0,0,BackBuffer(),TextureBuffer(tex)  
; do forward view  
SetCubeFace tex,1  
RotateEntity camera,0,0,0  
RenderWorld  
CopyRect 0,0,tex_sz,tex_sz,0,0,BackBuffer(),TextureBuffer(tex)  
; do right view  
SetCubeFace tex,2  
RotateEntity camera,0,-90,0  
RenderWorld  
CopyRect 0,0,tex_sz,tex_sz,0,0,BackBuffer(),TextureBuffer(tex)  
; do backward view  
SetCubeFace tex,3  
RotateEntity camera,0,180,0  
RenderWorld  
CopyRect 0,0,tex_sz,tex_sz,0,0,BackBuffer(),TextureBuffer(tex)  
; do up view  
SetCubeFace tex,4  
RotateEntity camera,-90,0,0  
RenderWorld  
CopyRect 0,0,tex_sz,tex_sz,0,0,BackBuffer(),TextureBuffer(tex)  
; do down view  
SetCubeFace tex,5  
RotateEntity camera,90,0,0  
RenderWorld  
CopyRect 0,0,tex_sz,tex_sz,0,0,BackBuffer(),TextureBuffer(tex)  
; Show entity again  
ShowEntity entity  
; Hide the cubemap camera  
HideEntity camera  
End Function  
end function
;; Set the rendering mode of a cubemap texture.
;; The available rendering modes are as follows:
;; 1: Specular (default). Use this to give your cubemapped objects a shiny effect.
;; 2: Diffuse. Use this to give your cubemapped objects a non-shiny, realistic lighting effect.
;; 3: Refraction. Good for 'cloak'-style effects.
;; See also: <a class=small href=CreateTexture.htm>CreateTexture</a>, <a class=small href=LoadTexture.htm>LoadTexture</a>, <a class=small href=SetCubeFace.htm>SetCubeFace</a>.
;;param texture - a valid texture handle
;;param mode - the rendering mode of the cubemap texture:
;;param 1: Specular (default)
;;param 2: Diffuse
;;param 3: Refraction
function SetCubeMode texture,mode
; SetCubeMode Example  
; -------------------  
width=640  
height=480  
depth=0  
mode=0  
Graphics3D width,height,depth,mode  
SetBuffer BackBuffer()  
; If user's graphics card does not support cubic mapping then quit example  
If GfxDriverCaps3D()<110 Then RuntimeError "Sorry, your graphics card does not support cubic environemnt maps."  
cam=CreateCamera()  
PositionEntity cam,0,10,-10  
; Create separate camera for updating cube map - this allows us to manipulate main camera and cube camera which avoids any confusion  
cube_cam=CreateCamera()  
HideEntity cube_cam  
light=CreateLight()  
RotateEntity light,90,0,0  
; Load object we will apply cubemap to - the classic teapot  
teapot=LoadMesh("media/teapot.x")  
ScaleEntity teapot,3,3,3  
PositionEntity teapot,0,10,0  
; Create some scenery  
; ground  
ground=CreatePlane()  
EntityColor ground,168,133,55  
ground_tex=LoadTexture("media/sand.bmp")  
ScaleTexture ground_tex,10,10  
EntityTexture ground,ground_tex  
; sky  
sky=CreateSphere(24)  
ScaleEntity sky,500,500,500  
FlipMesh sky  
EntityFX sky,1  
sky_tex=LoadTexture("media/sky.bmp")  
EntityTexture sky,sky_tex  
; cactus  
cactus=LoadMesh("media/cactus2.x")  
FitMesh cactus,-5,0,-5,2,6,.5  
; camel  
camel=LoadMesh("media/camel.x")  
FitMesh camel,5,0,-5,6,5,4  
; Load ufo to give us a dynamic moving object that the cubemap will be able to reflect  
ufo_piv=CreatePivot()  
PositionEntity ufo_piv,0,15,0  
ufo=LoadMesh("media/green_ufo.x",ufo_piv)  
PositionEntity ufo,0,0,10  
; Create texture with color + cubic environment map + store in vram flags  
tex=CreateTexture(256,256,1+128+256)  
; Apply cubic environment map to teapot  
EntityTexture teapot,tex  
; Set initial cube mode value  
cube_mode=1  
While Not KeyDown(1)  
; Control camera  
; mouse look  
mxs#=mxs#+(MouseXSpeed()/5.0)  
mys#=mys#+(MouseYSpeed()/5.0)  
RotateEntity cam,mys#,-mxs#,0  
MoveMouse width/2,height/2  
; move camera forwards/backwards/left/right with cursor keys  
If KeyDown(200)=True Then MoveEntity cam,0,0,.2 ; move camera forward  
If KeyDown(208)=True Then MoveEntity cam,0,0,-.2 ; move camera back  
If KeyDown(205)=True Then MoveEntity cam,.2,0,0 ; move camera left  
If KeyDown(203)=True Then MoveEntity cam,-.2,0,0 ; move camera right  
; If M key pressed then change cube mode  
If KeyHit(50)  
cube_mode=cube_mode+1  
If cube_mode=4 Then cube_mode=1  
SetCubeMode tex,cube_mode  
EndIf  
; Turn ufo pivot, causing child ufo mesh to spin around it (and teapot)  
TurnEntity ufo_piv,0,2,0  
; Hide our main camera before updating cube map - we don't need it to be rendererd every time cube_cam is rendered  
HideEntity cam  
; Update cubemap  
UpdateCubemap(tex,cube_cam,teapot)  
; Show main camera again  
ShowEntity cam  
RenderWorld  
Text 0,0,"Use mouse to look around"  
Text 0,20,"Use cursor keys to change camera position"  
Text 0,40,"Press M to change cube mode"  
Text 0,60,"SetCubeMode tex,"+cube_mode  
Flip  
Wend  
Function UpdateCubemap(tex,camera,entity)  
tex_sz=TextureWidth(tex)  
; Show the camera we have specifically created for updating the cubemap  
ShowEntity camera  
; Hide entity that will have cubemap applied to it. This is so we can get cubemap from its position, without it blocking the view  
HideEntity entity  
; Position camera where the entity is - this is where we will be rendering views from for cubemap  
PositionEntity camera,EntityX#(entity),EntityY#(entity),EntityZ#(entity)  
CameraClsMode camera,False,True  
; Set the camera's viewport so it is the same size as our texture - so we can fit entire screen contents into texture  
CameraViewport camera,0,0,tex_sz,tex_sz  
; Update cubemap  
; do left view  
SetCubeFace tex,0  
RotateEntity camera,0,90,0  
RenderWorld  
CopyRect 0,0,tex_sz,tex_sz,0,0,BackBuffer(),TextureBuffer(tex)  
; do forward view  
SetCubeFace tex,1  
RotateEntity camera,0,0,0  
RenderWorld  
CopyRect 0,0,tex_sz,tex_sz,0,0,BackBuffer(),TextureBuffer(tex)  
; do right view  
SetCubeFace tex,2  
RotateEntity camera,0,-90,0  
RenderWorld  
CopyRect 0,0,tex_sz,tex_sz,0,0,BackBuffer(),TextureBuffer(tex)  
; do backward view  
SetCubeFace tex,3  
RotateEntity camera,0,180,0  
RenderWorld  
CopyRect 0,0,tex_sz,tex_sz,0,0,BackBuffer(),TextureBuffer(tex)  
; do up view  
SetCubeFace tex,4  
RotateEntity camera,-90,0,0  
RenderWorld  
CopyRect 0,0,tex_sz,tex_sz,0,0,BackBuffer(),TextureBuffer(tex)  
; do down view  
SetCubeFace tex,5  
RotateEntity camera,90,0,0  
RenderWorld  
CopyRect 0,0,tex_sz,tex_sz,0,0,BackBuffer(),TextureBuffer(tex)  
; Show entity again  
ShowEntity entity  
; Hide the cubemap camera  
HideEntity camera  
End Function  
end function
;; Shows an entity. Very much the opposite of HideEntity.
;; Once an entity has been hidden using HideEntity,  use show entity to make it visible and involved in collisions again.  Note that ShowEntity has no effect if the enitities parent object is hidden.
;; Entities are shown by default after creating/loading them, so you should  only need to use ShowEntity after using HideEntity.
;; ShowEntity affects the specified entity only - child entities are not affected.
;; Click <a href=http://www.blitzbasic.co.nz/b3ddocs/command.php?name=ShowEntity&ref=comments target=_blank>here</a> to view the latest version of this page online</body>
;; </html>
;;param entity - entity handle
function ShowEntity entity
end function
;; Sets the view mode of a sprite.
;; The view mode determines how a sprite  alters its orientation in respect to the camera. This allows the sprite to in  some instances give the impression that it is more than two dimensional.
;; In technical terms, the four sprite modes perform the following changes:
;; 1: Sprite changes its pitch and yaw values to face camera, but doesn't roll.
;; 2: Sprite does not change either its pitch, yaw or roll values.
;; 3: Sprite changes its yaw and pitch to face camera, and changes its roll value  to match cameras.
;; 4: Sprite changes its yaw value to face camera, but not its pitch value, and  changes its roll value to match cameras.
;; Note that if you use sprite view mode 2, then because it is independent from  the camera, you will only be able to see it from one side unless you use EntityFx  flag 16 with it to disable backface culling.
;; See also: <a class=small href=CreateSprite.htm>CreateSprite</a>, <a class=small href= LoadSprite.htm> LoadSprite</a>.
;;param sprite - sprite handle
;;param view_mode - view_mode of sprite
;;param 1: fixed (sprite always faces camera - default)
;;param 2: free (sprite is independent of camera)
;;param 3: upright1 (sprite always faces camera, but rolls with camera as well, unlike  mode no.1)
;;param 4: upright2 (sprite always remains upright. Gives a 'billboard' effect. Good  for trees, spectators etc.)
function SpriteViewMode sprite,view_mode
; SpriteViewMode Example  
; ----------------------  
Graphics3D 640,480  
SetBuffer BackBuffer()  
pivot=CreatePivot()  
PositionEntity pivot,0,1,0  
camera=CreateCamera(pivot)  
PositionEntity camera,0,0,10  
light=CreateLight()  
RotateEntity light,90,0,0  
plane=CreatePlane()  
ground_tex=LoadTexture("media/Chorme-2.bmp")  
EntityTexture plane,ground_tex  
sprite=LoadSprite("media/b3dlogo.jpg")  
PositionEntity sprite,0,1,0  
pitch=-15  
yaw=180  
roll=0  
view_mode=1  
view_mode_info$=" (fixed)"  
While Not KeyDown(1)  
If KeyDown(208)=True And pitch<0 Then pitch=pitch+1  
If KeyDown(200)=True And pitch>-89 Then pitch=pitch-1  
If KeyDown(205)=True Then yaw=yaw+1  
If KeyDown(203)=True Then yaw=yaw-1  
If KeyDown(30)=True Then roll=roll+1  
If KeyDown(31)=True Then roll=roll-1  
; Change sprite view mode depending on key pressed  
If KeyHit(2)=True Then view_mode=1 : view_mode_info$=" (fixed)"  
If KeyHit(3)=True Then view_mode=2 : view_mode_info$=" (free)"  
If KeyHit(4)=True Then view_mode=3 : view_mode_info$=" (upright1)"  
If KeyHit(5)=True Then view_mode=4 : view_mode_info$=" (upright2)"  
; Set sprite view mode  
SpriteViewMode sprite,view_mode  
RotateEntity pivot,pitch,yaw,0  
PointEntity camera,sprite,roll  
RenderWorld  
Text 0,0,"Use cursor keys to orbit camera around sprite"  
Text 0,20,"Press A and S keys to roll camera"  
Text 0,40,"Press keys 1-4 to change sprite view mode"  
Text 0,60,"SpriteViewMode: "+view_mode+view_mode_info$  
Flip  
Wend  
End  
end function
;; Sets the detail level for a terrain. This is the number of triangles used  to represent the terrain. A typical value is 2000.
;; The optional vertex_morph  parameter specifies whether to enable vertex morphing. It is recommended you  set this to True, as it will reduce the visibility of LOD 'pop-in'.
;;param terrain - terrain handle
;;param detail_level - detail level of terrain
;;param vertex_morph (optional) - True to enable vertex morphing of terrain. Defaults  to False.
function TerrainDetail terrain,detail_level[,vertex_morph]
; TerrainDetail Example  
; ---------------------  
Graphics3D 640,480  
SetBuffer BackBuffer()  
camera=CreateCamera()  
PositionEntity camera,1,1,1  
light=CreateLight()  
RotateEntity light,90,0,0  
; Load terrain  
terrain=LoadTerrain( "media/height_map.bmp" )  
; Scale terrain  
ScaleEntity terrain,1,50,1  
; Texture terrain  
grass_tex=LoadTexture( "media/mossyground.bmp" )  
EntityTexture terrain,grass_tex,0,1  
; Set terrain detail value  
terra_detail=4000  
; Set vertex morph value  
vertex_morph=True  
While Not KeyDown( 1 )  
; Change terrain detail value depending on key pressed  
If KeyDown(26) Then terra_detail=terra_detail-10  
If KeyDown(27) Then terra_detail=terra_detail+10  
; Toggle vertex morphing on/off when spacebar is pressed  
If KeyHit(57)=True Then vertex_morph=1-vertex_morph  
; Set terrain detail, vertex morphing  
TerrainDetail terrain,terra_detail,vertex_morph  
If KeyDown( 203 )=True Then x#=x#-0.1  
If KeyDown( 205 )=True Then x#=x#+0.1  
If KeyDown( 208 )=True Then y#=y#-0.1  
If KeyDown( 200 )=True Then y#=y#+0.1  
If KeyDown( 44 )=True Then z#=z#-0.1  
If KeyDown( 30 )=True Then z#=z#+0.1  
If KeyDown( 205 )=True Then TurnEntity camera,0,-1,0  
If KeyDown( 203 )=True Then TurnEntity camera,0,1,0  
If KeyDown( 208 )=True Then MoveEntity camera,0,0,-0.1  
If KeyDown( 200 )=True Then MoveEntity camera,0,0,0.1  
x#=EntityX(camera)  
y#=EntityY(camera)  
z#=EntityZ(camera)  
terra_y#=TerrainY(terrain,x#,y#,z#)+5  
PositionEntity camera,x#,terra_y#,z#  
RenderWorld  
Text 0,0,"Use cursor keys to move about the terrain"  
Text 0,20,"Use [ and ] keys to change terrain detail level"  
Text 0,40,"Press spacebar to enable/disable vertex morphing"  
Text 0,60,"Terrain Detail: "+terra_detail  
If vertex_morph=True Then Text 0,80,"Vertex Morphing: True" Else Text 0,80,"Vertex  Morphing: False"  
Flip  
Wend  
End  
end function
;; Returns the height of the terrain at terrain grid coordinates x,z. The value  returned is in the range 0 to 1.
;; See also: TerrainY.
;;param terrain - terrain handle
;;param grid_x - grid x coordinate of terrain
;;param grid_z - grid z coordinate of terrain
function TerrainHeight# ( terrain,grid_x,grid_z )
; TerrainHeight Example  
; ---------------------  
Graphics3D 640,480  
SetBuffer BackBuffer()  
terra_size=32 ; initial size of terrain, and no. of grids segments, along each  side  
x_scale=10 ; x scale of terrain  
y_scale=50 ; y scale of terrain  
z_scale=10 ; z scale of terrain  
marker_x=terra_size/2 ; initial x position of marker  
marker_z=terra_size/2 ; initial z position of marker  
camera=CreateCamera()  
PositionEntity camera,(terra_size*x_scale)/2,50,0 ; position wherever; just  try and get good view of terrain!  
RotateEntity camera,30,0,0 ; again, try and get good view of terrain  
light=CreateLight()  
RotateEntity light,90,0,0  
; Create terrain  
terra=CreateTerrain(terra_size)  
ScaleEntity terra,x_scale,y_scale,z_scale  
; Texture terrain  
grass_tex=LoadTexture("media/mossyground.bmp")  
EntityTexture terra,grass_tex  
; Create marker  
marker=CreateSphere()  
ScaleEntity marker,1,1,1  
EntityColor marker,255,0,0  
While Not KeyDown(1)  
; Change marker position values depending on cursor key pressed  
If KeyHit(205)=True Then marker_x=marker_x+1  
If KeyHit(203)=True Then marker_x=marker_x-1  
If KeyHit(208)=True Then marker_z=marker_z-1  
If KeyHit(200)=True Then marker_z=marker_z+1  
; Get terrain height at marker position  
marker_y#=TerrainHeight(terra,marker_x,marker_z)  
; If A pressed then increase marker_y value and modify terrain  
If KeyDown(30)=True  
If marker_y#<1 Then marker_y#=marker_y#+0.005  
ModifyTerrain terra,marker_x,marker_z,marker_y#  
EndIf  
; If Z pressed then decrease marker_y value and modify terrain  
If KeyDown(44)=True  
If marker_y#>0 Then marker_y#=marker_y#-0.005  
ModifyTerrain terra,marker_x,marker_z,marker_y#  
EndIf  
; Position marker, taking into account x, y and z scales of terrain  
PositionEntity marker,marker_x*x_scale,marker_y#*y_scale,marker_z*z_scale  
RenderWorld  
Text 0,0,"Use cursor keys to move marker over the terrain"  
Text 0,20,"Press A or Z to alter height of terrain at marker's position"  
Text 0,40,"Terrain Height: "+marker_y#  
Flip  
Wend  
End  
end function
;; Enables or disables terrain shading.
;; Shaded terrains are a little slower  than non-shaded terrains, and in some instances can increase the visibility  of LOD 'pop-in'. However, the option is there to have shaded terrains if you  wish to do so.
;;param terrain - terrain handle
;;param enable - True to enable terrain shading, False to to disable it. The default  mode is False.
function TerrainShading terrain,enable
; TerrainShading Example  
; ----------------------  
Graphics3D 640,480  
SetBuffer BackBuffer()  
camera=CreateCamera()  
PositionEntity camera,1,1,1  
light=CreateLight()  
RotateEntity light,45,0,0  
; Load terrain  
terrain=LoadTerrain( "media/height_map.bmp" )  
; Set terrain detail, enable vertex morphing  
TerrainDetail terrain,4000,True  
; Scale terrain  
ScaleEntity terrain,1,50,1  
; Texture terrain  
grass_tex=LoadTexture( "media/mossyground.bmp" )  
EntityTexture terrain,grass_tex,0,1  
While Not KeyDown( 1 )  
; Toggle terrain shading value between 0 and 1 when spacebar is pressed  
If KeyHit(57)=True Then terra_shade=1-terra_shade  
; Enable/disable terrain shading  
TerrainShading terrain,terra_shade  
If KeyDown( 203 )=True Then x#=x#-0.1  
If KeyDown( 205 )=True Then x#=x#+0.1  
If KeyDown( 208 )=True Then y#=y#-0.1  
If KeyDown( 200 )=True Then y#=y#+0.1  
If KeyDown( 44 )=True Then z#=z#-0.1  
If KeyDown( 30 )=True Then z#=z#+0.1  
If KeyDown( 205 )=True Then TurnEntity camera,0,-1,0  
If KeyDown( 203 )=True Then TurnEntity camera,0,1,0  
If KeyDown( 208 )=True Then MoveEntity camera,0,0,-0.1  
If KeyDown( 200 )=True Then MoveEntity camera,0,0,0.1  
x#=EntityX(camera)  
y#=EntityY(camera)  
z#=EntityZ(camera)  
terra_y#=TerrainY(terrain,x#,y#,z#)+5  
PositionEntity camera,x#,terra_y#,z#  
RenderWorld  
Text 0,0,"Use cursor keys to move about the terrain"  
Text 0,20,"Press spacebar to toggle between TerrainShading True/False"  
If terra_shade=True Then Text 0,40,"TerrainShading: True" Else Text 0,40,"TerrainShading:  False"  
Flip  
Wend  
End  
end function
;; Returns the grid size used to create a terrain.
;;param terrain - terrain handle
function TerrainSize ( terrain )
; TerrainSize Example  
; -------------------  
Graphics3D 640,480  
SetBuffer BackBuffer()  
camera=CreateCamera()  
PositionEntity camera,1,1,1  
light=CreateLight()  
RotateEntity light,90,0,0  
; Load terrain  
terrain=LoadTerrain( "media/height_map.bmp" )  
; Set terrain detail, enable vertex morphing  
TerrainDetail terrain,4000,True  
; Scale terrain  
ScaleEntity terrain,1,50,1  
; Texture terrain  
grass_tex=LoadTexture( "media/mossyground.bmp" )  
EntityTexture terrain,grass_tex,0,1  
While Not KeyDown( 1 )  
If KeyDown( 203 )=True Then x#=x#-0.1  
If KeyDown( 205 )=True Then x#=x#+0.1  
If KeyDown( 208 )=True Then y#=y#-0.1  
If KeyDown( 200 )=True Then y#=y#+0.1  
If KeyDown( 44 )=True Then z#=z#-0.1  
If KeyDown( 30 )=True Then z#=z#+0.1  
If KeyDown( 205 )=True Then TurnEntity camera,0,-1,0  
If KeyDown( 203 )=True Then TurnEntity camera,0,1,0  
If KeyDown( 208 )=True Then MoveEntity camera,0,0,-0.1  
If KeyDown( 200 )=True Then MoveEntity camera,0,0,0.1  
x#=EntityX(camera)  
y#=EntityY(camera)  
z#=EntityZ(camera)  
terra_y#=TerrainY(terrain,x#,y#,z#)+5  
PositionEntity camera,x#,terra_y#,z#  
RenderWorld  
Text 0,0,"Use cursor keys to move about the terrain"  
; Output terrain size to screen  
Text 0,20,"Terrain Size: "+TerrainSize(terrain)  
Flip  
Wend  
End  
end function
;; Returns the interpolated x coordinate on a terrain.
;; See also: TerrainY, TerrainZ.
;;param terrain - terrain handle
;;param x# - world x coordinate
;;param y# - world y coordinate
;;param z# - world z coordinate
function TerrainX# (terrain,x#,y#,z# )
; TerrainX Example  
; ----------------  
Graphics3D 640,480  
SetBuffer BackBuffer()  
camera=CreateCamera()  
PositionEntity camera,1,1,1  
light=CreateLight()  
RotateEntity light,90,0,0  
; Load terrain  
terrain=LoadTerrain( "media/height_map.bmp" )  
; Scale terrain  
ScaleEntity terrain,1,50,1  
; Texture terrain  
grass_tex=LoadTexture( "media/mossyground.bmp" )  
EntityTexture terrain,grass_tex,0,1  
; Set terrain detail, enable vertex morphing  
TerrainDetail terrain,4000,True  
While Not KeyDown( 1 )  
If KeyDown( 203 )=True Then x#=x#-0.1  
If KeyDown( 205 )=True Then x#=x#+0.1  
If KeyDown( 208 )=True Then y#=y#-0.1  
If KeyDown( 200 )=True Then y#=y#+0.1  
If KeyDown( 44 )=True Then z#=z#-0.1  
If KeyDown( 30 )=True Then z#=z#+0.1  
If KeyDown( 205 )=True Then TurnEntity camera,0,-1,0  
If KeyDown( 203 )=True Then TurnEntity camera,0,1,0  
If KeyDown( 208 )=True Then MoveEntity camera,0,0,-0.1  
If KeyDown( 200 )=True Then MoveEntity camera,0,0,0.1  
x#=EntityX(camera)  
y#=EntityY(camera)  
z#=EntityZ(camera)  
terra_y#=TerrainY(terrain,x#,y#,z#)+5  
PositionEntity camera,x#,terra_y#,z#  
RenderWorld  
Text 0,0,"Use cursor keys to move about the terrain"  
; Output TerrainX value to screen  
Text 0,20,"TerrainX: "+TerrainX(terrain,x#,terra_y#,z#)  
Flip  
Wend  
End  
end function
;; Returns the interpolated y coordinate on a terrain.
;; Gets the ground's  height, basically.
;; See also: TerrainX, TerrainZ, TerrainHeight.
;;param terrain - terrain handle
;;param x# - world x coordinate
;;param y# - world y coordinate
;;param z# - world z coordinate
function TerrainY# (terrain,x#,y#,z# )
; TerrainY Example  
; ----------------  
Graphics3D 640,480  
SetBuffer BackBuffer()  
camera=CreateCamera()  
PositionEntity camera,1,1,1  
light=CreateLight()  
RotateEntity light,90,0,0  
; Load terrain  
terrain=LoadTerrain( "media/height_map.bmp" )  
; Scale terrain  
ScaleEntity terrain,1,50,1  
; Texture terrain  
grass_tex=LoadTexture( "media/mossyground.bmp" )  
EntityTexture terrain,grass_tex,0,1  
; Set terrain detail, enable vertex morphing  
TerrainDetail terrain,4000,True  
While Not KeyDown( 1 )  
If KeyDown( 203 )=True Then x#=x#-0.1  
If KeyDown( 205 )=True Then x#=x#+0.1  
If KeyDown( 208 )=True Then y#=y#-0.1  
If KeyDown( 200 )=True Then y#=y#+0.1  
If KeyDown( 44 )=True Then z#=z#-0.1  
If KeyDown( 30 )=True Then z#=z#+0.1  
If KeyDown( 205 )=True Then TurnEntity camera,0,-1,0  
If KeyDown( 203 )=True Then TurnEntity camera,0,1,0  
If KeyDown( 208 )=True Then MoveEntity camera,0,0,-0.1  
If KeyDown( 200 )=True Then MoveEntity camera,0,0,0.1  
x#=EntityX(camera)  
y#=EntityY(camera)  
z#=EntityZ(camera)  
terra_y#=TerrainY(terrain,x#,y#,z#)  
PositionEntity camera,x#,terra_y#+5,z#  
RenderWorld  
Text 0,0,"Use cursor keys to move about the terrain"  
; Output TerrainY value to screen  
Text 0,20,"TerrainY: "+terra_y#  
Flip  
Wend  
End  
end function
;; Returns the interpolated z coordinate on a terrain.
;; See also: TerrainX, TerrainY.
;;param terrain - terrain handle
;;param x# - world x coordinate
;;param y# - world y coordinate
;;param z# - world z coordinate
function TerrainZ# (terrain,x#,y#,z# )
; TerrainZ Example  
; ----------------  
Graphics3D 640,480  
SetBuffer BackBuffer()  
camera=CreateCamera()  
PositionEntity camera,1,1,1  
light=CreateLight()  
RotateEntity light,90,0,0  
; Load terrain  
terrain=LoadTerrain( "media/height_map.bmp" )  
; Scale terrain  
ScaleEntity terrain,1,50,1  
; Texture terrain  
grass_tex=LoadTexture( "media/mossyground.bmp" )  
EntityTexture terrain,grass_tex,0,1  
; Set terrain detail, enable vertex morphing  
TerrainDetail terrain,4000,True  
While Not KeyDown( 1 )  
If KeyDown( 203 )=True Then x#=x#-0.1  
If KeyDown( 205 )=True Then x#=x#+0.1  
If KeyDown( 208 )=True Then y#=y#-0.1  
If KeyDown( 200 )=True Then y#=y#+0.1  
If KeyDown( 44 )=True Then z#=z#-0.1  
If KeyDown( 30 )=True Then z#=z#+0.1  
If KeyDown( 205 )=True Then TurnEntity camera,0,-1,0  
If KeyDown( 203 )=True Then TurnEntity camera,0,1,0  
If KeyDown( 208 )=True Then MoveEntity camera,0,0,-0.1  
If KeyDown( 200 )=True Then MoveEntity camera,0,0,0.1  
x#=EntityX(camera)  
y#=EntityY(camera)  
z#=EntityZ(camera)  
terra_y#=TerrainY(terrain,x#,y#,z#)+5  
PositionEntity camera,x#,terra_y#,z#  
RenderWorld  
Text 0,0,"Use cursor keys to move about the terrain"  
; Output TerrainZ value to screen  
Text 0,20,"TerrainZ: "+TerrainZ(terrain,x#,terra_y#,z#)  
Flip  
Wend  
End  
end function
;; Sets the blending mode for a texture.
;; The texture blend mode determines how the texture will blend with the texture or polygon which is 'below' it. Texture 0 will blend with the polygons of the entity it is applied to. Texture 1 will blend with texture 0. Texture 2 will blend with texture 1. And so on.
;; Texture blending in Blitz effectively takes the highest order texture (the one with the highest index) and it blends with the texture below it, then that result to the texture directly below again, and so on until texture 0 which is blended with the polygons of the entity it is applied to and thus the world, depending on the <a class=small href=../3d_commands/EntityBlend.htm>EntityBlend</a> of the object.
;; Each of the blend modes are identical to their <a class=small href=../3d_commands/EntityBlend.htm>EntityBlend</a> counterparts.
;; In the case of multitexturing (more than one texture applied to an entity), it is not recommended you blend textures that have been loaded with the alpha flag, as this can cause unpredictable results on a variety of different graphics cards.
;; Use <a class=small href=../3d_commands/EntityTexture.htm>EntityTexture</a> to set the index number of a texture.
;; See also: <a class=small href=EntityBlend.htm>EntityBlend</a>, <a class=small href=EntityTexture.htm>EntityTexture</a>.
;;param Texture - Texture handle.
;;param Blend - Blend mode of texture.
;;param 0: Do not blend
;;param 1: No blend, or Alpha (alpha when texture loaded with alpha flag - not recommended  for multitexturing - see below)
;;param 2: Multiply (default)
;;param 3: Add
;;param 4: Dot3
;;param 5: Multiply 2
function TextureBlend Texture, Blend
; TextureBlend Example  
; --------------------  
Graphics3D 640,480  
SetBuffer BackBuffer()  
camera=CreateCamera()  
; Choose a background colour which isn't the same colour as anything else, to  avoid confusion  
CameraClsColor camera,255,0,0  
light=CreateLight()  
RotateEntity light,90,0,0  
cube=CreateCube()  
PositionEntity cube,0,0,5  
; Load textures  
tex0=LoadTexture( "media/b3dlogo.jpg" )  
tex1=LoadTexture( "media/chorme-2.bmp" )  
; Texture cube with textures  
EntityTexture cube,tex0,0,0  
EntityTexture cube,tex1,0,1  
tex0_blend_info$="no texture"  
tex1_blend_info$="no texture"  
While Not KeyDown( 1 )  
; Change texture 0 blending mode  
If KeyHit( 11 )=True  
tex0_blend=tex0_blend+1  
If tex0_blend=4 Then tex0_blend=0  
If tex0_blend=0 Then tex0_blend_info$="no texture"  
If tex0_blend=1 Then tex0_blend_info$="no blend"  
If tex0_blend=2 Then tex0_blend_info$="multiply"  
If tex0_blend=3 Then tex0_blend_info$="add"  
EndIf  
; Change texture 1 blending mode  
If KeyHit( 2 )=True  
tex1_blend=tex1_blend+1  
If tex1_blend=4 Then tex1_blend=0  
If tex1_blend=0 Then tex1_blend_info$="no texture"  
If tex1_blend=1 Then tex1_blend_info$="no blend"  
If tex1_blend=2 Then tex1_blend_info$="multiply"  
If tex1_blend=3 Then tex1_blend_info$="add"  
EndIf  
; Set texture blend modes  
TextureBlend tex0,tex0_blend  
TextureBlend tex1,tex1_blend  
TurnEntity cube,0.1,0.1,0.1  
RenderWorld  
Text 0,0,"Press 0 to change texture 0's blending mode"  
Text 0,20,"Press 1 to change texture 1's blending mode"  
Text 0,40,"TextureBlend tex0,"+tex0_blend+" ("+tex0_blend_info$+")"  
Text 0,60,"TextureBlend tex1,"+tex1_blend+" ("+tex1_blend_info$+")"  
Flip  
Wend  
End  
end function
;; Returns the handle of a texture's drawing buffer.
;; This can be used  with SetBuffer to perform 2D drawing operations to  the texture,  although it's usually faster to draw to an image, and then copy the  image buffer across to the texture buffer using CopyRect.
;; You cannot render 3D to a texture buffer; 3D can only be rendered to the  back buffer. To display 3D graphics on a texture, use CopyRect to copy the contents of  the back buffer to a texture buffer.
;;param texture - texture handle
;;param frame (optional) - texture frame
function TextureBuffer ( texture[,frame] )
; TextureBuffer Example  
; ---------------------  
Graphics3D 640,480  
SetBuffer BackBuffer()  
camera=CreateCamera()  
light=CreateLight()  
RotateEntity light,90,0,0  
cube=CreateCube()  
PositionEntity cube,0,0,5  
; Create texture of size 256x256  
tex=CreateTexture(256,256)  
; Set buffer - texture buffer  
SetBuffer TextureBuffer(tex)  
; Clear texture buffer with background white color  
ClsColor 255,255,255  
Cls  
; Draw text on texture  
font=LoadFont("arial",24)  
SetFont font  
Color 0,0,0  
Text 0,0,"This texture"  
Text 0,40,"was created using" : Color 0,0,255  
Text 0,80,"CreateTexture()" : Color 0,0,0  
Text 0,120,"and drawn to using" : Color 0,0,255  
Text 0,160,"SetBuffer TextureBuffer()"  
; Texture cube with texture  
EntityTexture cube,tex  
; Set buffer - backbuffer  
SetBuffer BackBuffer()  
While Not KeyDown( 1 )  
pitch#=0  
yaw#=0  
roll#=0  
If KeyDown( 208 )=True Then pitch#=-1  
If KeyDown( 200 )=True Then pitch#=1  
If KeyDown( 203 )=True Then yaw#=-1  
If KeyDown( 205 )=True Then yaw#=1  
If KeyDown( 45 )=True Then roll#=-1  
If KeyDown( 44 )=True Then roll#=1  
TurnEntity cube,pitch#,yaw#,roll#  
RenderWorld  
Flip  
Wend  
End  
end function
;; Sets the texture coordinate mode for a texture.
;; This determines where  the UV values used to look up a texture come from.
;; Click <a href=http://www.blitzbasic.co.nz/b3ddocs/command.php?name=TextureCoords&ref=comments target=_blank>here</a> to view the latest version of this page online</body>
;; </html>
;;param texture - name of texture
;;param coords -
;;param 0: UV coordinates are from first UV set in vertices (default)
;;param 1: UV coordinates are from second UV set in vertices
function TextureCoords texture,coords
end function
;; Adds a texture filter. Any textures loaded that contain the text specified  by match_text$ will have the provided flags added.
;; This is mostly of use when loading a mesh.
;; By default, the following texture filter is used:
;; TextureFilter "",1+8
;; This means that all loaded textures will have color and be mipmapped by default.
;;param match_text$ - text that, if found in texture filename, will activate certain  filters
;;param flags - filter texture flags:
;;param 1: Color
;;param 2: Alpha
;;param 4: Masked
;;param 8: Mipmapped
;;param 16: Clamp U
;;param 32: Clamp V
;;param 64: Spherical reflection map
;;param 128: <void>
;;param 256: Store texture in vram
;;param 512: Force the use of high color textures
function TextureFilter match_text$,flags
; ClearTextureFilters and TextureFilter Example.  
; ----------------------------------------------  
Const tex_color 	= 1		; Color texture  
Const tex_alpha 	= 2		; Alpha texture (Include alpha channel data)  
Const tex_mask 		= 4		; Masked texture (black is transparent)  
Const tex_mipmap 	= 8		; Create texture mipmaps  
Const tex_clampu 	= 16	; Restrict U texture coords from "bleeding over"  
Const tex_clampv	= 32	; Restrict V texture coords from "bleeding over"  
Const tex_envshpere	= 64	; Load texture as a spherical environment map  
Const tex_vram 		= 256	; Force texture graphics to vram  
Const tex_highcolor	= 512	; Forces texture graphics to be 32-bits per pixel  
Graphics3D 640,480  
; Removes any texture filters that might apply.  
ClearTextureFilters  
; Add an alpha texture to the list of  
; texture filters to apply to files  
; that have "_alpha" in their filenames.  
TextureFilter "_alpha",tex_color + tex_alpha + tex_mipmap  
; Set appropriate texture flags for loading  
; suitable skybox textures from files named  
; something with "_skybox".  
TextureFilter "_skybox", tex_color + tex_mipmap + tex_clampu + tex_clampv  
; Set the flags for loading a spherical refletction  
; map to apply to all "_refmap" files.  
TextureFilter "_refmap", tex_color + tex_mipmap + tex_envshpere  
; Setup a texture filter to allow faster  
; (and easier) pixel manipulation on all  
; loaded "_fastblit" files.  
TextureFilter "_fastblit", tex_color + tex_vram + tex_highcolor  
; This is where you would normally load your special  
; textures.  
; The next bit resets the texture filters to their  
; standard settings.  
ClearTextureFilters  
TextureFilter "", tex_color + tex_mipmap  
End  
end function
;; Returns the height of a texture.
;;param texture - texture handle
function TextureHeight ( texture )
; TextureWidth and TextureHeight Example.  
; ---------------------------------------  
Graphics3D 640,480  
; This bit of code creates 4 textures  
; of different sizes and shapes.  
texture1=CreateTexture(256,256,59)  
texture2=CreateTexture(256,128,59)  
texture3=CreateTexture(128,256,59)  
texture4=CreateTexture(200,200,59)  
; The following lines print the selected value  
; of the texture, and reports the actual texture  
; resolution.  
Print "Texture 1 was created at 256x256"  
Print "On your system the dimensions are "+TextureWidth(texture1)+"x"+TextureHeight(texture1)  
Print  
Print "Texture 2 was created at 256x128"  
Print "On your system the dimensions are "+TextureWidth(texture2)+"x"+TextureHeight(texture2)  
Print  
Print "Texture 3 was created at 128x256"  
Print "On your system the dimensions are "+TextureWidth(texture3)+"x"+TextureHeight(texture3)  
Print  
Print "Texture 4 was created at 200x200"  
Print "On your system the dimensions are "+TextureWidth(texture4)+"x"+TextureHeight(texture4)  
WaitKey  
End  
end function
;; Returns a texture's absolute filename.
;; To find out just the name of the texture, you will need to parse the string returned by TextureName. One such function to do this is:
;; ; start of code
;; Function StripPath$(file$)
;; If Len(file$)>0
;; For i=Len(file$) To 1 Step -1
;; mi$=Mid$(file$,i,1)
;; If mi$="\" Or mi$="/" Then Return name$ Else name$=mi$+name$
;; Next
;; EndIf
;; Return name$
;; End Function
;; ; end of code
;; See also: <a class=small href=GetBrushTexture.htm>GetBrushTexture</a>.
;;param texture - a valid texture handle
function TextureName$(texture)
; TextureName Example  
; -------------------  
Graphics3D 640,480  
SetBuffer BackBuffer()  
camera=CreateCamera()  
light=CreateLight()  
RotateEntity light,90,0,0  
; Load mesh  
crate=LoadMesh("media/wood-crate/wcrate1.3ds")  
PositionEntity crate,0,0,100  
; Get mesh surface  
surf=GetSurface(crate,1)  
; Get surface brush  
crate_brush=GetSurfaceBrush(surf)  
; Get brush texture  
crate_tex=GetBrushTexture(crate_brush,0)  
While Not KeyDown( 1 )  
RenderWorld  
; Display full texture name  
Text 0,0,"Texture name, as returned by TextureName$():"  
Text 0,20,TextureName$(crate_tex)  
; Display trimmed texture name  
Text 0,40,"Texture name with path stripped:"  
Text 0,60,StripPath$(TextureName$(crate_tex))  
Flip  
Wend  
End  
Function StripPath$(file$)  
If Len(file$)>0  
For i=Len(file$) To 1 Step -1  
mi$=Mid$(file$,i,1)  
If mi$="\" Or mi$="/" Then Return name$ Else name$=mi$+name$  
Next  
EndIf  
Return name$  
End Function  
end function
;; Returns the width of a texture.
;;param texture - texture handle
function TextureWidth (texture )
; TextureWidth and TextureHeight Example.  
; ---------------------------------------  
Graphics3D 640,480  
; This bit of code creates 4 textures  
; of different sizes and shapes.  
texture1=CreateTexture(256,256,59)  
texture2=CreateTexture(256,128,59)  
texture3=CreateTexture(128,256,59)  
texture4=CreateTexture(200,200,59)  
; The following lines print the selected value  
; of the texture, and reports the actual texture  
; resolution.  
Print "Texture 1 was created at 256x256"  
Print "On your system the dimensions are "+TextureWidth(texture1)+"x"+TextureHeight(texture1)  
Print  
Print "Texture 2 was created at 256x128"  
Print "On your system the dimensions are "+TextureWidth(texture2)+"x"+TextureHeight(texture2)  
Print  
Print "Texture 3 was created at 128x256"  
Print "On your system the dimensions are "+TextureWidth(texture3)+"x"+TextureHeight(texture3)  
Print  
Print "Texture 4 was created at 200x200"  
Print "On your system the dimensions are "+TextureWidth(texture4)+"x"+TextureHeight(texture4)  
WaitKey  
End  
end function
;; Returns the X component of the last TFormPoint, TFormVector or TFormNormal  operation.
;; See those commands for examples.
;; Click <a href=http://www.blitzbasic.co.nz/b3ddocs/command.php?name=TFormedX&ref=comments target=_blank>here</a> to view the latest version of this page online</body>
;; </html>
;;param None.
function TFormedX()
end function
;; Returns the Y component of the last TFormPoint, TFormVector or TFormNormal  operation.
;; See those commands for examples.
;; Click <a href=http://www.blitzbasic.co.nz/b3ddocs/command.php?name=TFormedY&ref=comments target=_blank>here</a> to view the latest version of this page online</body>
;; </html>
;;param None.
function TFormedY()
end function
;; Returns the Z component of the last TFormPoint,  TFormVector or TFormNormal operation.
;; See those commands for examples.
;; Click <a href=http://www.blitzbasic.co.nz/b3ddocs/command.php?name=TFormedZ&ref=comments target=_blank>here</a> to view the latest version of this page online</body>
;; </html>
;;param None.
function TFormedZ()
end function
;; Transforms between coordinate systems. After using TFormNormal the new
;; components can be read with TFormedX(), TFormedY() and TFormedZ().
;; This is exactly the same as TFormVector but with one added feature.
;; After the transformation the new vector is 'normalized', meaning it
;; is scaled to have length 1.
;; For example, suppose the result of TFormVector is (1,2,2).
;; This vector has length Sqr( 1*1 + 2*2 + 2*2 ) = Sqr( 9 ) = 3.
;; This means TFormNormal would produce ( 1/3, 2/3, 2/3 ).
;;param x#, y#, z# = components of a vector in 3d space
;;param source_entity = handle of source entity, or 0 for 3d world
;;param dest_entity = handle of destination entity, or 0 for 3d world
function TFormNormal x#, y#, z#, source_entity, dest_entity
; TFormNormal example  
Graphics3D 640, 480  
; Just a quick demonstration of the 'normalization' feature.  
TFormNormal 1,2,2, 0,0    ;  transform from world to world  
; The transformation from world to world does nothing.  
; But afterward the vector (1,2,2) is divided by the length 3.  
message$ = "The normalized vector is  ( "  
message = message + TFormedX() + ",  " + TFormedY() + ",  " + TFormedZ() + " )"  
Text 70, 180, message  
Flip  
WaitKey()  
End  
end function
;; Transforms between coordinate systems. After using TFormPoint the new
;; coordinates can be read with TFormedX(), TFormedY() and TFormedZ().
;; See EntityX() for details about local coordinates.
;; Consider a sphere built with CreateSphere(). The 'north pole' is at (0,1,0).
;; At first, local and global coordinates are the same. As the sphere is moved,
;; turned and scaled the global coordinates of the point change.
;; But it is always at (0,1,0) in the sphere's local space.
;;param x#, y#, z# = coordinates of a point in 3d space
;;param source_entity = handle of source entity, or 0 for 3d world
;;param dest_entity = handle of destination entity, or 0 for 3d world
function TFormPoint x#, y#, z#, source_entity, dest_entity
; TFormPoint example  
Graphics3D 640, 480  
s = CreateSphere()       ; center at (0,0,0)  north pole at (0,1,0)  
MoveEntity s, 1,2,3      ; center at (1,2,3)  north pole at (1,2+1,3)  
ScaleEntity s, 10,10,10  ; center at (1,2,3)  north pole at (1,2+10,3)  
; Now verify that the north pole is at (1,12,3) in the 3d world  
TFormPoint 0,1,0, s,0    ; north pole transformed from sphere to world  
message$ = "North pole is at ( "  
message = message + TFormedX() + ",  " + TFormedY() + ",  " + TFormedZ() + " )"  
Text 180, 200, message  
Flip  
WaitKey()  
End  
end function
;; Transforms between coordinate systems. After using TFormVector the new
;; components can be read with TFormedX(), TFormedY() and TFormedZ().
;; See EntityX() for details about local coordinates.
;; Similar to TFormPoint, but operates on a vector. A vector can be thought of
;; as 'displacement relative to current location'.
;; For example, vector (1,2,3) means one step to the right, two steps up
;; and three steps forward.
;; This is analogous to PositionEntity and MoveEntity:
;; PositionEntity entity, x,y,z   ; put entity at point (x,y,z)
;; MoveEntity entity, x,y,z       ; add vector (x,y,z) to current position
;;param x#, y#, z# = components of a vector in 3d space
;;param source_entity = handle of source entity, or 0 for 3d world
;;param dest_entity = handle of destination entity, or 0 for 3d world
function TFormVector x#, y#, z#, source_entity, dest_entity
; TFormVector example  
Graphics3D 640, 480  
p = CreatePivot()  
PositionEntity p, 10, 20, 30   ; easy to visualize  
TurnEntity p, -5, -15, 25      ; hard to visualize  
; Question: what would happen if we took one step 'forward'?  
; The local vector corresponding to one step forward is (0,0,1)  
; in the pivot's local space. We need the global version.  
TFormVector 0,0,1, p,0    ;  transform from pivot to world  
message$ = "'One step forward' vector is  ( "  
message = message + TFormedX() + ",  " + TFormedY() + ",  " + TFormedZ() + " )"  
Text 70, 180, message  
; Now actually take the step. The new location should be  
; (10,20,30) plus the vector we just computed.  
MoveEntity p, 0,0,1  
message$ = "New location of pivot is  ( "  
message = message + EntityX(p) + ",  "  
message = message + EntityY(p) + ",  " + EntityZ(p) + " )"  
Text 100, 210, message  
Flip  
WaitKey()  
End  
end function
;; Translates an entity relative to its current position and not its  orientation.
;; What this means is that an entity will move in a certain direction despite where it may be facing. Imagine that you have a game character that you want to make jump in the air at the same time as doing a triple somersault. Translating the character by a positive y amount will mean the character will always travel directly up in their air, regardless of where it may be facing due to the somersault action.
;; See also: <a class=small href=MoveEntity.htm>MoveEntity</a>, <a class=small href=PositionEntity.htm>PositionEntity</a>, <a class=small href=PositionMesh.htm>PositionMesh</a>.
;;param entity - name of entity to be translated
;;param x# - x amount that entity will be translated by
;;param y# - y amount that entity will be translated by
;;param z# - z amount that entity will be translated by
;;param global (optional) -
function TranslateEntity entity,x#,y#,z#,[,global]
; TranslateEntity Example  
; -----------------------  
Graphics3D 640,480  
SetBuffer BackBuffer()  
camera=CreateCamera()  
light=CreateLight()  
cone=CreateCone( 32 )  
; Rotate cone by random amount to demonstrate that TranslateEntity is  independent of entity orientation  
RotateEntity cone,Rnd( 0,360 ),Rnd( 0,360 ),Rnd( 0,360 )  
; Translate cone in front of camera, so we can see it to begin with  
TranslateEntity cone,0,0,10  
While Not KeyDown( 1 )  
; Reset translation values - otherwise, the cone will not stop!  
x#=0  
y#=0  
z#=0  
; Change translation values depending on the key pressed  
If KeyDown( 203 )=True Then x#=-0.1  
If KeyDown( 205 )=True Then x#=0.1  
If KeyDown( 208 )=True Then y#=-0.1  
If KeyDown( 200 )=True Then y#=0.1  
If KeyDown( 44 )=True Then z#=-0.1  
If KeyDown( 30 )=True Then z#=0.1  
; Translate sphere using translation values  
TranslateEntity cone,x#,y#,z#  
; If spacebar pressed then rotate cone by random amount  
If KeyHit( 57 )=True Then RotateEntity cone,Rnd( 0,360 ),Rnd( 0,360 ),Rnd(  0,360 )  
RenderWorld  
Text 0,0,"Use cursor/A/Z keys to translate cone, spacebar to rotate cone by  random amount"  
Text 0,20,"X Translation: "+x#  
Text 0,40,"Y Translation: "+y#  
Text 0,60,"Z Translation: "+z#  
Flip  
Wend  
End  
end function
;; Returns the vertex of a triangle corner.
;; Click <a href=http://www.blitzbasic.co.nz/b3ddocs/command.php?name=TriangleVertex&ref=comments target=_blank>here</a> to view the latest version of this page online</body>
;; </html>
;;param surface - surface handle
;;param triangle_index - triangle index
;;param corner - corner of triangle. Should be 0, 1 or 2.
function TriangleVertex ( surface,triangle_index,corner )
end function
;; Returns the number of triangles rendered during the most recent RenderWorld.
;; Useful for debugging purposes -  to see if you're displaying, too many, or too little, polygons.
;;param None.
function TrisRendered()
; TrisRendered Example  
; --------------------  
Graphics3D 640,480  
SetBuffer BackBuffer()  
camera=CreateCamera()  
PositionEntity camera,0,0,-2  
light=CreateLight()  
RotateEntity light,90,0,0  
segs=Rand( 2,16 )  
sphere=CreateSphere(segs)  
While Not KeyDown( 1 )  
If KeyHit( 57 )=True  
FreeEntity sphere  
segs=Rand( 2,16 )  
sphere=CreateSphere( segs )  
EndIf  
RenderWorld  
Text 0,0,"Press space to create a sphere with a random segments value"  
; Display triangles rendered  
Text 0,20,"Triangles Rendered: "+TrisRendered()  
Flip  
Wend  
End  
end function
;; Turns an entity relative to its current orientation.
;; Pitch is the same as the x angle of an entity, and is equivalent to tilting forward/backwards.
;; Yaw is the same as the y angle of an entity, and is equivalent to turning left/right.
;; Roll is the same as the z angle of an entity, and is equivalent to tilting left/right.
;; See also: <a class=small href=RotateEntity.htm>RotateEntity</a>, <a class=small href=RotateMesh.htm>RotateMesh</a>.
;;param entity - name of entity to be rotated
;;param pitch# - angle in degrees that entity will be pitched
;;param yaw# - angle in degrees that entity will be yawed
;;param roll# - angle in degrees that entity will be rolled
;;param global (optional) -
function TurnEntity entity,pitch#,yaw#,roll#,[,global]
; TurnEntity Example  
; ------------------  
Graphics3D 640,480  
SetBuffer BackBuffer()  
camera=CreateCamera()  
light=CreateLight()  
cone=CreateCone( 32 )  
PositionEntity cone,0,0,5  
While Not KeyDown( 1 )  
; Reset turn values - otherwise, the cone will not stop turning!  
pitch#=0  
yaw#=0  
roll#=0  
; Change movement values depending on the key pressed  
If KeyDown( 208 )=True Then pitch#=-1  
If KeyDown( 200 )=True Then pitch#=1  
If KeyDown( 203 )=True Then yaw#=-1  
If KeyDown( 205 )=True Then yaw#=1  
If KeyDown( 45 )=True Then roll#=-1  
If KeyDown( 44 )=True Then roll#=1  
; Move sphere using movement values  
TurnEntity cone,pitch#,yaw#,roll#  
RenderWorld  
Text 0,0,"Use cursor/Z/X keys to turn cone"  
Text 0,20,"Pitch: "+pitch#  
Text 0,40,"Yaw: "+yaw#  
Text 0,60,"Roll: "+roll#  
Flip  
Wend  
End  
end function
;; Recalculates all normals in a mesh. This is necessary for correct lighting  if you have not set surface normals using 'VertexNormals' commands.
;; Click <a href=http://www.blitzbasic.co.nz/b3ddocs/command.php?name=UpdateNormals&ref=comments target=_blank>here</a> to view the latest version of this page online</body>
;; </html>
;;param mesh - mesh handle
function UpdateNormals mesh
end function
;; Animates all entities in the world, and performs collision checking.
;; The  optional anim_speed# parameter allows you affect the animation speed of all  entities at once. A value of 1 will animate entities at their usual animation  speed, a value of 2 will animate entities at double their animation speed, and  so on.
;; For best results use this command once per main loop, just before calling RenderWorld.
;; Click <a href=http://www.blitzbasic.co.nz/b3ddocs/command.php?name=UpdateWorld&ref=comments target=_blank>here</a> to view the latest version of this page online</body>
;; </html>
;;param anim_speed# (optional) - a master control for animation speed. Defaults  to 1.
function UpdateWorld [anim_speed#]
end function
;; Returns the pitch value of a vector.
;; Using this command will return the same result as using <a class=small href=../3d_commands/EntityPitch.htm>EntityPitch</a> to get the pitch value of an entity that is pointing in the vector's direction.
;; See also: <a class=small href=VectorYaw.htm>VectorYaw</a>, <a class=small href=EntityPitch.htm>EntityPitch</a>.
;;param x# - x vector length
;;param y# - y vector length
;;param z# - z vector length
function VectorPitch# ( x#,y#,z# )
; VectorPitch Example  
; -------------------  
Graphics3D 640,480  
SetBuffer BackBuffer()  
; Set vector x,y,z values  
vx#=2  
vy#=2  
vz#=0  
camera=CreateCamera()  
PositionEntity camera,0,0,-5  
light=CreateLight()  
cone=CreateCone()  
; Rotate cone so that it points in the direction it is facing  
RotateMesh cone,90,0,0  
pivot=CreatePivot()  
; Position pivot to represent a vector  
PositionEntity pivot,vx#,vy#,vz#  
; Point cone in direction of pivot. It should now have same pitch value.  
PointEntity cone,pivot  
While Not KeyDown(1)  
RenderWorld  
; Print entity pitch value and vector pitch value. Both should be identical.  
Text 0,0,"Entity pitch: "+EntityPitch(cone)  
Text 0,20,"Vector pitch: "+VectorPitch(vx#,vy#,vz#)  
Flip  
Wend  
End  
end function
;; Returns the yaw value of a vector.
;; Using this command will return the same result as using <a class=small href=../3d_commands/EntityYaw.htm>EntityYaw</a> to get the yaw value of an entity that is pointing in the vector's direction.
;; See also: <a class=small href=VectorPitch.htm>VectorPitch</a>, <a class=small href=EntityYaw.htm>EntityYaw</a>.
;;param x# - x vector length
;;param y# - y vector length
;;param z# - z vector length
function VectorYaw# ( x#,y#,z# )
; VectorYaw Example  
; -----------------  
Graphics3D 640,480  
SetBuffer BackBuffer()  
; Set vector x,y,z values  
vx#=2  
vy#=2  
vz#=0  
camera=CreateCamera()  
PositionEntity camera,0,0,-5  
light=CreateLight()  
cone=CreateCone()  
; Rotate cone so that it points in the direction it is facing  
RotateMesh cone,90,0,0  
pivot=CreatePivot()  
; Position pivot to represent a vector  
PositionEntity pivot,vx#,vy#,vz#  
; Point cone in direction of pivot. It should now have same yaw value.  
PointEntity cone,pivot  
While Not KeyDown(1)  
RenderWorld  
; Print entity yaw value and vector yaw value. Both should be identical.  
Text 0,0,"Entity yaw: "+EntityYaw(cone)  
Text 0,20,"Vector yaw: "+VectorYaw(vx#,vy#,vz#)  
Flip  
Wend  
End  
end function
;; Returns the alpha component of a vertices color, set using <a class=small href=../3d_commands/VertexColor.htm>VertexColor</a>
;; See also: <a class=small href=VertexRed.htm>VertexRed</a>, <a class=small href=VertexGreen.htm>VertexGreen</a>, <a class=small href=VertexBlue.htm>VertexBlue</a>, <a class=small href=VertexColor.htm>VertexColor</a>.
;; Click <a href=http://www.blitzbasic.co.nz/b3ddocs/command.php?name=VertexAlpha&ref=comments target=_blank>here</a> to view the latest version of this page online</body>
;; </html>
;;param surface - surface handle
;;param index - index of vertex
function VertexAlpha# ( surface,index )
end function
;; Returns the blue component of a vertices color.
;; Click <a href=http://www.blitzbasic.co.nz/b3ddocs/command.php?name=VertexBlue&ref=comments target=_blank>here</a> to view the latest version of this page online</body>
;; </html>
;;param surface - surface handle
;;param index - index of vertex
function VertexBlue# ( surface,index )
end function
;; Sets the color of an existing vertex.
;; NB. If you want to set the alpha individually for vertices using the alpha# parameter then you need to use EntityFX 32 (to force alpha-blending) on the entity.
;; See also: <a class=small href=EntityFX.htm>EntityFX</a>.
;; Click <a href=http://www.blitzbasic.co.nz/b3ddocs/command.php?name=VertexColor&ref=comments target=_blank>here</a> to view the latest version of this page online</body>
;; </html>
;;param surface - surface handle
;;param index - index of vertex
;;param red# - red value of vertex
;;param green# - green value of vertex
;;param blue# - blue value of vertex
;;param alpha# - optional alpha transparency of vertex (0.0 to 1.0 - default: 1.0)
function VertexColor surface,index,red#,green#,blue#[,alpha#]
end function
;; Sets the geometric coordinates of an existing vertex.
;; This is the command  used to perform what is commonly referred to as 'dynamic mesh deformation'.  It will reposition a vertex so that all the triangle edges connected to it,  will move also. This will give the effect of parts of the mesh suddenly deforming.
;; Click <a href=http://www.blitzbasic.co.nz/b3ddocs/command.php?name=VertexCoords&ref=comments target=_blank>here</a> to view the latest version of this page online</body>
;; </html>
;;param surface - surface handle
;;param index - index of vertex
;;param x# - x position of vertex
;;param y# - y position of vertex
;;param z# - z position of vertex
function VertexCoords surface,index,x#,y#,z#
end function
;; Returns the green component of a vertices color.
;; Click <a href=http://www.blitzbasic.co.nz/b3ddocs/command.php?name=VertexGreen&ref=comments target=_blank>here</a> to view the latest version of this page online</body>
;; </html>
;;param surface - surface handle
;;param index - index of vertex
function VertexGreen# ( surface,index )
end function
;; Sets the normal of an existing vertex.
;; Click <a href=http://www.blitzbasic.co.nz/b3ddocs/command.php?name=VertexNormal&ref=comments target=_blank>here</a> to view the latest version of this page online</body>
;; </html>
;;param surface - surface handle
;;param index - index of vertex
;;param nx# - normal x of vertex
;;param ny# - normal y of vertex
;;param nz# - normal z of vertex
function VertexNormal surface,index,nx#,ny#,nz#
end function
;; Returns the x component of a vertices normal.
;; Click <a href=http://www.blitzbasic.co.nz/b3ddocs/command.php?name=VertexNX&ref=comments target=_blank>here</a> to view the latest version of this page online</body>
;; </html>
;;param surface - surface handle
;;param index - index of vertex
function VertexNX# ( surface,index )
end function
;; Returns the y component of a vertices normal.
;; Click <a href=http://www.blitzbasic.co.nz/b3ddocs/command.php?name=VertexNY&ref=comments target=_blank>here</a> to view the latest version of this page online</body>
;; </html>
;;param surface - surface handle
;;param index - index of vertex
function VertexNY# ( surface,index )
end function
;; Returns the z component of a vertices normal.
;; Click <a href=http://www.blitzbasic.co.nz/b3ddocs/command.php?name=VertexNZ&ref=comments target=_blank>here</a> to view the latest version of this page online</body>
;; </html>
;;param surface - surface handle
;;param index - index of vertex
function VertexNZ# ( surface,index )
end function
;; Returns the red component of a vertices color.
;; Click <a href=http://www.blitzbasic.co.nz/b3ddocs/command.php?name=VertexRed&ref=comments target=_blank>here</a> to view the latest version of this page online</body>
;; </html>
;;param surface - surface handle
;;param index - index of vertex
function VertexRed# ( surface,index )
end function
;; Sets the texture coordinates of an existing vertex.
;; Click <a href=http://www.blitzbasic.co.nz/b3ddocs/command.php?name=VertexTexCoords&ref=comments target=_blank>here</a> to view the latest version of this page online</body>
;; </html>
;;param surface - surface handle
;;param index - index of vertex
;;param u# - u# coordinate of vertex
;;param v# - v# coordinate of vertex
;;param w# (optional) - w# coordinate of vertex
;;param coord_set (optional) - co_oord set. Should be set to 0 or 1.
function VertexTexCoords surface,index,u#,v#[,w#][,coord_set]
end function
;; Returns the texture u coordinate of a vertex.
;; See also: VertexV, VertexTexCoords
;; Click <a href=http://www.blitzbasic.co.nz/b3ddocs/command.php?name=VertexU&ref=comments target=_blank>here</a> to view the latest version of this page online</body>
;; </html>
;;param surface - surface handle
;;param index - index of vertex
;;param coord_set (optional) - UV mapping coordinate set. Should be set to 0 or 1.
function VertexU# ( surface,index [,coord_set] )
end function
;; Returns the texture v coordinate of a vertex.
;; See also: VertexU, VertexTexCoords
;; Click <a href=http://www.blitzbasic.co.nz/b3ddocs/command.php?name=VertexV&ref=comments target=_blank>here</a> to view the latest version of this page online</body>
;; </html>
;;param surface - surface handle
;;param index - index of vertex
;;param coord_set (optional) - UV mapping coordinate set. Should be set to 0 or 1.
function VertexV# ( surface,index [,coord_set] )
end function
;; Returns the texture w coordinate of a vertex.
;; Click <a href=http://www.blitzbasic.co.nz/b3ddocs/command.php?name=VertexW&ref=comments target=_blank>here</a> to view the latest version of this page online</body>
;; </html>
;;param surface - surface handle
;;param index - index of vertex
function VertexW# ( surface,index )
end function
;; Returns the x coordinate of a vertex.
;; Click <a href=http://www.blitzbasic.co.nz/b3ddocs/command.php?name=VertexX&ref=comments target=_blank>here</a> to view the latest version of this page online</body>
;; </html>
;;param surface - surface handle
;;param index - index of vertex
function VertexX# ( surface,index )
end function
;; Returns the y coordinate of a vertex.
;; Click <a href=http://www.blitzbasic.co.nz/b3ddocs/command.php?name=VertexY&ref=comments target=_blank>here</a> to view the latest version of this page online</body>
;; </html>
;;param surface - surface handle
;;param index - index of vertex
function VertexY# ( surface,index )
end function
;; Returns the z coordinate of a vertex.
;; Click <a href=http://www.blitzbasic.co.nz/b3ddocs/command.php?name=VertexZ&ref=comments target=_blank>here</a> to view the latest version of this page online</body>
;; </html>
;;param surface - surface handle
;;param index - index of vertex
function VertexZ ( surface,index )
end function
;; Enables or disables w-buffering.
;; W-buffering is a technique used to draw 3D object in order of their depth -  i.e. the ones furthest away from the camera first, then the ones closer to the  camera, and so on.
;; Normally, z-buffering is used to perform such a technique, but a z-buffer can  be slightly inaccurate in 16-bit colour mode, for which the level of precision  is less than in 24-bit or 32-bit colour modes. This means that in some situations,  objects will appear to overlap each other when they shouldn't and so on.
;; To compensate for this, you can use w-buffering. This is a slightly more accurate  technique than z-buffering, although it may be less compatible on some set-ups  than z-buffering.
;; Click <a href=http://www.blitzbasic.co.nz/b3ddocs/command.php?name=WBuffer&ref=comments target=_blank>here</a> to view the latest version of this page online</body>
;; </html>
;;param enable - True to enable w-buffering rendering, False to disable.
;;param The default  WBuffer mode is True for 16-bit colour mode, False for 24-bit and 32-bit.
function WBuffer enable
end function
;; Windowed3D returns true if the host machine's selected graphics card is capable of rendering 3D graphics in a window using the current desktop colour depth.
;; This mainly concerns older graphics cards, some of which may not be able to render 3D in a window at all, while others maybe be able to only render in a window if the user's desktop is set to a certain colour depth such as 16.
;;param None.
function Windowed3D()
If Windowed3D ()  
Graphics3D 640, 480, 0, 2  
Print "Windowed mode!"  
Else  
Graphics3D 640, 480, 0, 1  
Print "Full screen modes only!"  
EndIf  
MouseWait  
End  
end function
;; Enables or disables wireframe rendering.
;; This will show the outline of  each polygon on the screen, with no shaded-in areas.
;; Wireframe mode should only be used for debugging purposes, as driver support  is patchy. For the same reason, no support is offered for the wireframe rendering  of individual polygon entities.
;;param enable - True to enable wireframe rendering, False to disable.
;;param The default  Wireframe mode is False.
function Wireframe enable
; Wireframe Example  
; -----------------  
Graphics3D 640,480,16  
SetBuffer BackBuffer()  
camera=CreateCamera()  
light=CreateLight()  
RotateEntity light,90,0,0  
sphere=CreateSphere( 32 )  
PositionEntity sphere,0,0,2  
While Not KeyDown( 1 )  
; Toggle wireframe enable value between true and false when spacebar is pressed  
If KeyHit( 57 )=True Then enable=1-enable  
; Enable/disable wireframe rendering  
WireFrame enable  
RenderWorld  
Text 0,0,"Press spacebar to toggle between Wireframe True/False"  
If enable=True Then Text 0,20,"Wireframe True" Else Text 0,20,"Wireframe False"  
Flip  
Wend  
End  
end function
