
/**
 * Remove type suffix from declaration
 * @param name the declaration
 * @returns the declaration without the type suffix
 */
export function removeType(name: string): string {
    let q = name.trim();
    if (q.endsWith(']')) q = q.substring(0, q.lastIndexOf('['));
    if (q.indexOf('.') > 0) {
        return q.split('.')[0];
    } else if (q.endsWith('$') || q.endsWith('#') || q.endsWith('%')) {
        return q.substring(0, q.trim().length - 1);
    } else return q;
}

/**
 * Extract the type from a declaration
 * @param name the declaration
 * @returns the type of the variable, '(%)' if defaults to integer, followed by [] if blitz array
 */
export function extractType(name: string): string {
    let q = name.trim();
    let suffix = '';
    if (q.endsWith(']')) {
        suffix = '[]';
        q = q.substring(0, q.lastIndexOf('['));
    }
    if (q.indexOf('.') > 0) {
        return q.split('.')[1] + suffix;
    } else if (q.endsWith('$') || q.endsWith('#') || q.endsWith('%')) {
        return q.substring(q.length - 1) + suffix;
    } else return '(%)' + suffix;
}

/**
 * Check if given string is a builtin blitz keyword
 * @param name the string to check
 * @returns true if name is in the list of keywords given by compiler
 */
export function isBuiltinBlitzFunction(name: string): boolean {
    return ',Abs,After,And,Before,Case,Const,Data,Default,Delete,Dim,Each,Else,ElseIf,EndIf,Exit,False,Field,First,Float,For,Forever,Function,Global,Gosub,Goto,Handle,If,Include,Insert,Int,Last,Local,Mod,New,Next,Not,Null,Object,Or,Pi,Read,Repeat,Restore,Return,Sar,Select,Sgn,Shl,Shr,Step,Str,Then,To,True,Type,Until,Wend,While,Xor,End,Stop,AppTitle,RuntimeError,ExecFile,Delay,MilliSecs,CommandLine,SystemProperty,GetEnv,SetEnv,CreateTimer,WaitTimer,FreeTimer,DebugLog,RuntimeStats,Sin,Cos,Tan,ASin,ACos,ATan,ATan2,Sqr,Floor,Ceil,Exp,Log,Log10,Rnd,Rand,SeedRnd,RndSeed,String,Left,Right,Replace,Instr,Mid,Upper,Lower,Trim,LSet,RSet,Chr,Asc,Len,Hex,Bin,CurrentDate,CurrentTime,Eof,ReadAvail,ReadByte,ReadShort,ReadInt,ReadFloat,ReadString,ReadLine,WriteByte,WriteShort,WriteInt,WriteFloat,WriteString,WriteLine,CopyStream,DottedIP,CountHostIPs,HostIP,CreateUDPStream,CloseUDPStream,SendUDPMsg,RecvUDPMsg,UDPStreamIP,UDPStreamPort,UDPMsgIP,UDPMsgPort,UDPTimeouts,OpenTCPStream,CloseTCPStream,CreateTCPServer,CloseTCPServer,AcceptTCPStream,TCPStreamIP,TCPStreamPort,TCPTimeouts,OpenFile,ReadFile,WriteFile,CloseFile,FilePos,SeekFile,ReadDir,CloseDir,NextFile,CurrentDir,ChangeDir,CreateDir,DeleteDir,FileSize,FileType,CopyFile,DeleteFile,CreateBank,FreeBank,BankSize,ResizeBank,CopyBank,PeekByte,PeekShort,PeekInt,PeekFloat,PokeByte,PokeShort,PokeInt,PokeFloat,ReadBytes,WriteBytes,CallDLL,CountGfxDrivers,GfxDriverName,SetGfxDriver,CountGfxModes,GfxModeExists,GfxModeWidth,GfxModeHeight,GfxModeDepth,AvailVidMem,TotalVidMem,GfxDriver3D,CountGfxModes3D,GfxMode3DExists,GfxMode3D,Windowed3D,Graphics,Graphics3D,EndGraphics,GraphicsLost,SetGamma,UpdateGamma,GammaRed,GammaGreen,GammaBlue,FrontBuffer,BackBuffer,ScanLine,VWait,Flip,GraphicsWidth,GraphicsHeight,GraphicsDepth,SetBuffer,GraphicsBuffer,LoadBuffer,SaveBuffer,BufferDirty,LockBuffer,UnlockBuffer,ReadPixel,WritePixel,ReadPixelFast,WritePixelFast,CopyPixel,CopyPixelFast,Origin,Viewport,Color,GetColor,ColorRed,ColorGreen,ColorBlue,ClsColor,SetFont,Cls,Plot,Rect,Oval,Line,Text,CopyRect,LoadFont,FreeFont,FontWidth,FontHeight,StringWidth,StringHeight,OpenMovie,DrawMovie,MovieWidth,MovieHeight,MoviePlaying,CloseMovie,LoadImage,LoadAnimImage,CopyImage,CreateImage,FreeImage,SaveImage,GrabImage,ImageBuffer,DrawImage,DrawBlock,TileImage,TileBlock,DrawImageRect,DrawBlockRect,MaskImage,HandleImage,MidHandle,AutoMidHandle,ImageWidth,ImageHeight,ImageXHandle,ImageYHandle,ScaleImage,ResizeImage,RotateImage,TFormImage,TFormFilter,ImagesOverlap,ImagesCollide,RectsOverlap,ImageRectOverlap,ImageRectCollide,Write,Print,Input,Locate,ShowPointer,HidePointer,KeyDown,KeyHit,GetKey,WaitKey,FlushKeys,MouseDown,MouseHit,GetMouse,WaitMouse,MouseWait,MouseX,MouseY,MouseZ,MouseXSpeed,MouseYSpeed,MouseZSpeed,FlushMouse,MoveMouse,JoyType,JoyDown,JoyHit,GetJoy,WaitJoy,JoyWait,JoyX,JoyY,JoyZ,JoyU,JoyV,JoyPitch,JoyYaw,JoyRoll,JoyHat,JoyXDir,JoyYDir,JoyZDir,JoyUDir,JoyVDir,FlushJoy,EnableDirectInput,DirectInputEnabled,LoadSound,FreeSound,LoopSound,SoundPitch,SoundVolume,SoundPan,PlaySound,PlayMusic,PlayCDTrack,StopChannel,PauseChannel,ResumeChannel,ChannelPitch,ChannelVolume,ChannelPan,ChannelPlaying,Load3DSound,LoaderMatrix,HWMultiTex,HWTexUnits,GfxDriverCaps3D,WBuffer,Dither,AntiAlias,WireFrame,AmbientLight,ClearCollisions,Collisions,UpdateWorld,CaptureWorld,RenderWorld,ClearWorld,ActiveTextures,TrisRendered,Stats3D,CreateTexture,LoadTexture,LoadAnimTexture,FreeTexture,TextureBlend,TextureCoords,ScaleTexture,RotateTexture,PositionTexture,TextureWidth,TextureHeight,TextureName,SetCubeFace,SetCubeMode,TextureBuffer,ClearTextureFilters,TextureFilter,CreateBrush,LoadBrush,FreeBrush,BrushColor,BrushAlpha,BrushShininess,BrushTexture,GetBrushTexture,BrushBlend,BrushFX,LoadMesh,LoadAnimMesh,LoadAnimSeq,CreateMesh,CreateCube,CreateSphere,CreateCylinder,CreateCone,CopyMesh,ScaleMesh,RotateMesh,PositionMesh,FitMesh,FlipMesh,PaintMesh,AddMesh,UpdateNormals,LightMesh,MeshWidth,MeshHeight,MeshDepth,MeshesIntersect,CountSurfaces,GetSurface,MeshCullBox,CreateSurface,GetSurfaceBrush,GetEntityBrush,FindSurface,ClearSurface,PaintSurface,AddVertex,AddTriangle,VertexCoords,VertexNormal,VertexColor,VertexTexCoords,CountVertices,CountTriangles,VertexX,VertexY,VertexZ,VertexNX,VertexNY,VertexNZ,VertexRed,VertexGreen,VertexBlue,VertexAlpha,VertexU,VertexV,VertexW,TriangleVertex,CreateCamera,CameraZoom,CameraRange,CameraClsColor,CameraClsMode,CameraProjMode,CameraViewport,CameraFogColor,CameraFogRange,CameraFogMode,CameraProject,ProjectedX,ProjectedY,ProjectedZ,EntityInView,EntityVisible,EntityPick,LinePick,CameraPick,PickedX,PickedY,PickedZ,PickedNX,PickedNY,PickedNZ,PickedTime,PickedEntity,PickedSurface,PickedTriangle,CreateLight,LightColor,LightRange,LightConeAngles,CreatePivot,CreateSprite,LoadSprite,RotateSprite,ScaleSprite,HandleSprite,SpriteViewMode,LoadMD2,AnimateMD2,MD2AnimTime,MD2AnimLength,MD2Animating,LoadBSP,BSPLighting,BSPAmbientLight,CreateMirror,CreatePlane,CreateTerrain,LoadTerrain,TerrainDetail,TerrainShading,TerrainX,TerrainY,TerrainZ,TerrainSize,TerrainHeight,ModifyTerrain,CreateListener,EmitSound,CopyEntity,EntityX,EntityY,EntityZ,EntityPitch,EntityYaw,EntityRoll,GetMatElement,TFormPoint,TFormVector,TFormNormal,TFormedX,TFormedY,TFormedZ,VectorYaw,VectorPitch,DeltaPitch,DeltaYaw,ResetEntity,EntityType,EntityPickMode,GetParent,GetEntityType,EntityRadius,EntityBox,EntityDistance,EntityCollided,CountCollisions,CollisionX,CollisionY,CollisionZ,CollisionNX,CollisionNY,CollisionNZ,CollisionTime,CollisionEntity,CollisionSurface,CollisionTriangle,MoveEntity,TurnEntity,TranslateEntity,PositionEntity,ScaleEntity,RotateEntity,PointEntity,AlignToVector,SetAnimTime,Animate,SetAnimKey,AddAnimSeq,ExtractAnimSeq,AnimSeq,AnimTime,AnimLength,Animating,EntityParent,CountChildren,GetChild,FindChild,PaintEntity,EntityColor,EntityAlpha,EntityShininess,EntityTexture,EntityBlend,EntityFX,EntityAutoFade,EntityOrder,HideEntity,ShowEntity,FreeEntity,NameEntity,EntityName,EntityClass,'.toLowerCase().indexOf(',' + name.toLowerCase() + ',') >= 0;
}

/**
 * Check if given string is an unbracketed keyword - might be incomplete
 * @param name the string to check
 * @returns true if name is a keyword which shouldn't be followed by parenthesis
 */
export function isBlitzKeyword(name: string): boolean {
    return ',if,then,else,elseif,endif,select,case,default,end,and,or,xor,not,repeat,until,forever,while,wend,for,to,step,next,exit,goto,gosub,return,function,const,global,local,dim,type,field,new,each,first,last,insert,delete,true,false,null,data,read,restore,include,pi,mod,shl,shr,sar,'.includes(',' + name.toLowerCase() + ',');
}

/**
 * Determines where the comment starts in a valid blitz3d line. If there is no comment, the line length is returned so that it will always be bigger than all considerable character positions.
 * @param line the line of code to be examined
 * @returns the position of the first ';' character which is not in a string, or the line length, if there are none
 */
export function startOfComment(line: string): number {
    let instring: boolean = false;
    for (let i = 0; i < line.length; i++) {
        if (line[i] == '"') instring = !instring;
        if (line[i] == ';' && !instring) return i;
    }
    return line.length + 1; // so that c >= startOfComment only if the cth is a comment character
}

/**
 * Check if given position is between double quotes or after the last double quote (but before the start of comment)
 * @param line the line to search in
 * @param position the position to check
 * @returns if the position is in a string in the command line
 */
export function isInString(line: string, position: number): boolean {
    const scpos = startOfComment(line);
    for (let ap = line.indexOf('"'); ap != -1 && ap < scpos; ap = line.indexOf('"', ap + 1)) {
        if (position > ap && (line.indexOf('"', ap + 1) == -1 || position < line.indexOf('"', ap + 1))) return true;
        ap = line.indexOf('"', ap + 1);
        if (ap == -1 || ap > scpos) return false;
    }
    return false;
}