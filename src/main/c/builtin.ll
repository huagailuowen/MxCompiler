; ModuleID = 'builtin.c'
source_filename = "builtin.c"
target datalayout = "e-m:e-p270:32:32-p271:32:32-p272:64:64-i64:64-i128:128-f80:128-n8:16:32:64-S128"
target triple = "x86_64-pc-linux-gnu"

@.str = private unnamed_addr constant [3 x i8] c"%s\00", align 1
@.str.1 = private unnamed_addr constant [3 x i8] c"%d\00", align 1
@.str.2 = private unnamed_addr constant [4 x i8] c"%d\0A\00", align 1
@.str.3 = private unnamed_addr constant [5 x i8] c"true\00", align 1
@.str.4 = private unnamed_addr constant [6 x i8] c"false\00", align 1

; Function Attrs: nounwind uwtable
define dso_local void @print(ptr noundef %0) local_unnamed_addr #0 {
  %2 = tail call i32 (ptr, ...) @printf(ptr noundef nonnull @.str, ptr noundef %0) #8
  ret void
}

declare i32 @printf(ptr noundef, ...) local_unnamed_addr #1

; Function Attrs: nofree nounwind uwtable
define dso_local void @println(ptr nocapture noundef readonly %0) local_unnamed_addr #2 {
  %2 = tail call i32 @puts(ptr noundef nonnull dereferenceable(1) %0) #9
  ret void
}

; Function Attrs: nofree nounwind
declare noundef i32 @puts(ptr nocapture noundef readonly) local_unnamed_addr #3

; Function Attrs: nounwind uwtable
define dso_local void @printInt(i32 noundef %0) local_unnamed_addr #0 {
  %2 = tail call i32 (ptr, ...) @printf(ptr noundef nonnull @.str.1, i32 noundef %0) #8
  ret void
}

; Function Attrs: nounwind uwtable
define dso_local void @printlnInt(i32 noundef %0) local_unnamed_addr #0 {
  %2 = tail call i32 (ptr, ...) @printf(ptr noundef nonnull @.str.2, i32 noundef %0) #8
  ret void
}

; Function Attrs: nounwind uwtable
define dso_local noundef ptr @getString() local_unnamed_addr #0 {
  %1 = tail call ptr @malloc(i32 noundef 4096) #8
  %2 = tail call i32 (ptr, ...) @scanf(ptr noundef nonnull @.str, ptr noundef %1) #9
  ret ptr %1
}

; Function Attrs: mustprogress nocallback nofree nosync nounwind willreturn memory(argmem: readwrite)
declare void @llvm.lifetime.start.p0(i64 immarg, ptr nocapture) #4

declare ptr @malloc(i32 noundef) local_unnamed_addr #1

; Function Attrs: nofree nounwind
declare noundef i32 @scanf(ptr nocapture noundef readonly, ...) local_unnamed_addr #3

; Function Attrs: mustprogress nocallback nofree nosync nounwind willreturn memory(argmem: readwrite)
declare void @llvm.lifetime.end.p0(i64 immarg, ptr nocapture) #4

; Function Attrs: nofree nounwind uwtable
define dso_local i32 @getInt() local_unnamed_addr #2 {
  %1 = alloca i32, align 4
  call void @llvm.lifetime.start.p0(i64 4, ptr nonnull %1) #10
  %2 = call i32 (ptr, ...) @scanf(ptr noundef nonnull @.str.1, ptr noundef nonnull %1) #9
  %3 = load i32, ptr %1, align 4, !tbaa !5
  call void @llvm.lifetime.end.p0(i64 4, ptr nonnull %1) #10
  ret i32 %3
}

; Function Attrs: nounwind uwtable
define dso_local noundef ptr @toString(i32 noundef %0) local_unnamed_addr #0 {
  %2 = tail call ptr @malloc(i32 noundef 10) #8
  %3 = tail call i32 (ptr, ptr, ...) @sprintf(ptr noundef nonnull dereferenceable(1) %2, ptr noundef nonnull dereferenceable(1) @.str.1, i32 noundef %0) #9
  ret ptr %2
}

; Function Attrs: nofree nounwind
declare noundef i32 @sprintf(ptr noalias nocapture noundef writeonly, ptr nocapture noundef readonly, ...) local_unnamed_addr #3

; Function Attrs: mustprogress nofree norecurse nosync nounwind willreturn memory(none) uwtable
define dso_local noundef nonnull ptr @toString_bool(i1 noundef zeroext %0) local_unnamed_addr #5 {
  %2 = select i1 %0, ptr @.str.3, ptr @.str.4
  ret ptr %2
}

; Function Attrs: nounwind uwtable
define dso_local nonnull ptr @__malloc_array(i32 noundef %0, i32 noundef %1) local_unnamed_addr #0 {
  %3 = mul nsw i32 %1, %0
  %4 = add nsw i32 %3, 4
  %5 = tail call ptr @malloc(i32 noundef %4) #8
  store i32 %0, ptr %5, align 4, !tbaa !5
  %6 = getelementptr inbounds i32, ptr %5, i64 1
  ret ptr %6
}

; Function Attrs: nounwind uwtable
define dso_local ptr @__malloc(i32 noundef %0, i32 noundef %1) local_unnamed_addr #0 {
  %3 = mul nsw i32 %1, %0
  %4 = tail call ptr @malloc(i32 noundef %3) #8
  ret ptr %4
}

; Function Attrs: mustprogress nofree norecurse nosync nounwind willreturn memory(argmem: read) uwtable
define dso_local i32 @buildInArraySize(ptr nocapture noundef readonly %0) local_unnamed_addr #6 {
  %2 = getelementptr inbounds i32, ptr %0, i64 -1
  %3 = load i32, ptr %2, align 4, !tbaa !5
  ret i32 %3
}

; Function Attrs: nofree norecurse nosync nounwind memory(argmem: read) uwtable
define dso_local i32 @__string_length(ptr nocapture noundef readonly %0) local_unnamed_addr #7 {
  br label %2

2:                                                ; preds = %2, %1
  %3 = phi i64 [ %7, %2 ], [ 0, %1 ]
  %4 = getelementptr inbounds i8, ptr %0, i64 %3
  %5 = load i8, ptr %4, align 1, !tbaa !9
  %6 = icmp eq i8 %5, 0
  %7 = add nuw i64 %3, 1
  br i1 %6, label %8, label %2, !llvm.loop !10

8:                                                ; preds = %2
  %9 = trunc i64 %3 to i32
  ret i32 %9
}

; Function Attrs: nounwind uwtable
define dso_local noundef ptr @__string_substring(ptr noundef %0, i32 noundef %1, i32 noundef %2) local_unnamed_addr #0 {
  %4 = sub nsw i32 %2, %1
  %5 = add nsw i32 %4, 1
  %6 = tail call ptr @malloc(i32 noundef %5) #8
  %7 = sext i32 %1 to i64
  %8 = getelementptr inbounds i8, ptr %0, i64 %7
  %9 = tail call ptr @memcpy(ptr noundef %6, ptr noundef %8, i32 noundef %4) #8
  %10 = sext i32 %4 to i64
  %11 = getelementptr inbounds i8, ptr %6, i64 %10
  store i8 0, ptr %11, align 1, !tbaa !9
  ret ptr %6
}

declare ptr @memcpy(ptr noundef, ptr noundef, i32 noundef) local_unnamed_addr #1

; Function Attrs: nofree norecurse nosync nounwind memory(argmem: read) uwtable
define dso_local i32 @__string_parseInt(ptr nocapture noundef readonly %0) local_unnamed_addr #7 {
  %2 = load i8, ptr %0, align 1, !tbaa !9
  %3 = icmp eq i8 %2, 0
  br i1 %3, label %16, label %4

4:                                                ; preds = %1, %4
  %5 = phi i64 [ %12, %4 ], [ 0, %1 ]
  %6 = phi i8 [ %14, %4 ], [ %2, %1 ]
  %7 = phi i32 [ %11, %4 ], [ 0, %1 ]
  %8 = sext i8 %6 to i32
  %9 = mul nsw i32 %7, 10
  %10 = add i32 %9, -48
  %11 = add i32 %10, %8
  %12 = add nuw nsw i64 %5, 1
  %13 = getelementptr inbounds i8, ptr %0, i64 %12
  %14 = load i8, ptr %13, align 1, !tbaa !9
  %15 = icmp eq i8 %14, 0
  br i1 %15, label %16, label %4, !llvm.loop !12

16:                                               ; preds = %4, %1
  %17 = phi i32 [ 0, %1 ], [ %11, %4 ]
  ret i32 %17
}

; Function Attrs: mustprogress nofree norecurse nosync nounwind willreturn memory(argmem: read) uwtable
define dso_local i32 @__string_ord(ptr nocapture noundef readonly %0, i32 noundef %1) local_unnamed_addr #6 {
  %3 = sext i32 %1 to i64
  %4 = getelementptr inbounds i8, ptr %0, i64 %3
  %5 = load i8, ptr %4, align 1, !tbaa !9
  %6 = sext i8 %5 to i32
  ret i32 %6
}

; Function Attrs: nofree norecurse nosync nounwind memory(argmem: read) uwtable
define dso_local noundef i32 @__string_eq(ptr nocapture noundef readonly %0, ptr nocapture noundef readonly %1) local_unnamed_addr #7 {
  %3 = load i8, ptr %0, align 1, !tbaa !9
  %4 = icmp eq i8 %3, 0
  br i1 %4, label %18, label %10

5:                                                ; preds = %16
  %6 = add nuw i64 %11, 1
  %7 = getelementptr inbounds i8, ptr %0, i64 %6
  %8 = load i8, ptr %7, align 1, !tbaa !9
  %9 = icmp eq i8 %8, 0
  br i1 %9, label %18, label %10, !llvm.loop !13

10:                                               ; preds = %2, %5
  %11 = phi i64 [ %6, %5 ], [ 0, %2 ]
  %12 = phi i8 [ %8, %5 ], [ %3, %2 ]
  %13 = getelementptr inbounds i8, ptr %1, i64 %11
  %14 = load i8, ptr %13, align 1, !tbaa !9
  %15 = icmp eq i8 %14, 0
  br i1 %15, label %23, label %16

16:                                               ; preds = %10
  %17 = icmp eq i8 %12, %14
  br i1 %17, label %5, label %24

18:                                               ; preds = %5, %2
  %19 = phi i64 [ 0, %2 ], [ %6, %5 ]
  %20 = getelementptr inbounds i8, ptr %1, i64 %19
  %21 = load i8, ptr %20, align 1, !tbaa !9
  %22 = icmp eq i8 %21, 0
  br i1 %22, label %24, label %23

23:                                               ; preds = %10, %18
  br label %24

24:                                               ; preds = %16, %18, %23
  %25 = phi i32 [ 0, %23 ], [ 1, %18 ], [ 0, %16 ]
  ret i32 %25
}

; Function Attrs: nofree norecurse nosync nounwind memory(argmem: read) uwtable
define dso_local noundef i32 @__string_ne(ptr nocapture noundef readonly %0, ptr nocapture noundef readonly %1) local_unnamed_addr #7 {
  %3 = load i8, ptr %0, align 1, !tbaa !9
  %4 = icmp eq i8 %3, 0
  br i1 %4, label %18, label %10

5:                                                ; preds = %16
  %6 = add nuw i64 %11, 1
  %7 = getelementptr inbounds i8, ptr %0, i64 %6
  %8 = load i8, ptr %7, align 1, !tbaa !9
  %9 = icmp eq i8 %8, 0
  br i1 %9, label %18, label %10, !llvm.loop !13

10:                                               ; preds = %2, %5
  %11 = phi i64 [ %6, %5 ], [ 0, %2 ]
  %12 = phi i8 [ %8, %5 ], [ %3, %2 ]
  %13 = getelementptr inbounds i8, ptr %1, i64 %11
  %14 = load i8, ptr %13, align 1, !tbaa !9
  %15 = icmp eq i8 %14, 0
  br i1 %15, label %23, label %16

16:                                               ; preds = %10
  %17 = icmp eq i8 %12, %14
  br i1 %17, label %5, label %24

18:                                               ; preds = %5, %2
  %19 = phi i64 [ 0, %2 ], [ %6, %5 ]
  %20 = getelementptr inbounds i8, ptr %1, i64 %19
  %21 = load i8, ptr %20, align 1, !tbaa !9
  %22 = icmp eq i8 %21, 0
  br i1 %22, label %24, label %23

23:                                               ; preds = %10, %18
  br label %24

24:                                               ; preds = %16, %18, %23
  %25 = phi i32 [ 1, %23 ], [ 0, %18 ], [ 1, %16 ]
  ret i32 %25
}

; Function Attrs: nofree norecurse nosync nounwind memory(argmem: read) uwtable
define dso_local noundef i32 @__string_lt(ptr nocapture noundef readonly %0, ptr nocapture noundef readonly %1) local_unnamed_addr #7 {
  %3 = load i8, ptr %0, align 1, !tbaa !9
  %4 = icmp eq i8 %3, 0
  br i1 %4, label %18, label %10

5:                                                ; preds = %16
  %6 = add nuw i64 %11, 1
  %7 = getelementptr inbounds i8, ptr %0, i64 %6
  %8 = load i8, ptr %7, align 1, !tbaa !9
  %9 = icmp eq i8 %8, 0
  br i1 %9, label %18, label %10, !llvm.loop !14

10:                                               ; preds = %2, %5
  %11 = phi i64 [ %6, %5 ], [ 0, %2 ]
  %12 = phi i8 [ %8, %5 ], [ %3, %2 ]
  %13 = getelementptr inbounds i8, ptr %1, i64 %11
  %14 = load i8, ptr %13, align 1, !tbaa !9
  %15 = icmp eq i8 %14, 0
  br i1 %15, label %23, label %16

16:                                               ; preds = %10
  %17 = icmp slt i8 %12, %14
  br i1 %17, label %5, label %24

18:                                               ; preds = %5, %2
  %19 = phi i64 [ 0, %2 ], [ %6, %5 ]
  %20 = getelementptr inbounds i8, ptr %1, i64 %19
  %21 = load i8, ptr %20, align 1, !tbaa !9
  %22 = icmp eq i8 %21, 0
  br i1 %22, label %24, label %23

23:                                               ; preds = %10, %18
  br label %24

24:                                               ; preds = %16, %18, %23
  %25 = phi i32 [ 1, %23 ], [ 0, %18 ], [ 0, %16 ]
  ret i32 %25
}

; Function Attrs: nofree norecurse nosync nounwind memory(argmem: read) uwtable
define dso_local noundef i32 @__string_le(ptr nocapture noundef readonly %0, ptr nocapture noundef readonly %1) local_unnamed_addr #7 {
  %3 = load i8, ptr %1, align 1, !tbaa !9
  %4 = icmp eq i8 %3, 0
  br i1 %4, label %18, label %10

5:                                                ; preds = %16
  %6 = add nuw i64 %11, 1
  %7 = getelementptr inbounds i8, ptr %1, i64 %6
  %8 = load i8, ptr %7, align 1, !tbaa !9
  %9 = icmp eq i8 %8, 0
  br i1 %9, label %18, label %10, !llvm.loop !14

10:                                               ; preds = %2, %5
  %11 = phi i64 [ %6, %5 ], [ 0, %2 ]
  %12 = phi i8 [ %8, %5 ], [ %3, %2 ]
  %13 = getelementptr inbounds i8, ptr %0, i64 %11
  %14 = load i8, ptr %13, align 1, !tbaa !9
  %15 = icmp eq i8 %14, 0
  br i1 %15, label %23, label %16

16:                                               ; preds = %10
  %17 = icmp slt i8 %12, %14
  br i1 %17, label %5, label %24

18:                                               ; preds = %5, %2
  %19 = phi i64 [ 0, %2 ], [ %6, %5 ]
  %20 = getelementptr inbounds i8, ptr %0, i64 %19
  %21 = load i8, ptr %20, align 1, !tbaa !9
  %22 = icmp eq i8 %21, 0
  br i1 %22, label %24, label %23

23:                                               ; preds = %10, %18
  br label %24

24:                                               ; preds = %16, %18, %23
  %25 = phi i32 [ 0, %23 ], [ 1, %18 ], [ 1, %16 ]
  ret i32 %25
}

; Function Attrs: nofree norecurse nosync nounwind memory(argmem: read) uwtable
define dso_local noundef i32 @__string_gt(ptr nocapture noundef readonly %0, ptr nocapture noundef readonly %1) local_unnamed_addr #7 {
  %3 = load i8, ptr %1, align 1, !tbaa !9
  %4 = icmp eq i8 %3, 0
  br i1 %4, label %18, label %10

5:                                                ; preds = %16
  %6 = add nuw i64 %11, 1
  %7 = getelementptr inbounds i8, ptr %1, i64 %6
  %8 = load i8, ptr %7, align 1, !tbaa !9
  %9 = icmp eq i8 %8, 0
  br i1 %9, label %18, label %10, !llvm.loop !14

10:                                               ; preds = %2, %5
  %11 = phi i64 [ %6, %5 ], [ 0, %2 ]
  %12 = phi i8 [ %8, %5 ], [ %3, %2 ]
  %13 = getelementptr inbounds i8, ptr %0, i64 %11
  %14 = load i8, ptr %13, align 1, !tbaa !9
  %15 = icmp eq i8 %14, 0
  br i1 %15, label %23, label %16

16:                                               ; preds = %10
  %17 = icmp slt i8 %12, %14
  br i1 %17, label %5, label %24

18:                                               ; preds = %5, %2
  %19 = phi i64 [ 0, %2 ], [ %6, %5 ]
  %20 = getelementptr inbounds i8, ptr %0, i64 %19
  %21 = load i8, ptr %20, align 1, !tbaa !9
  %22 = icmp eq i8 %21, 0
  br i1 %22, label %24, label %23

23:                                               ; preds = %10, %18
  br label %24

24:                                               ; preds = %16, %18, %23
  %25 = phi i32 [ 1, %23 ], [ 0, %18 ], [ 0, %16 ]
  ret i32 %25
}

; Function Attrs: nofree norecurse nosync nounwind memory(argmem: read) uwtable
define dso_local noundef i32 @__string_ge(ptr nocapture noundef readonly %0, ptr nocapture noundef readonly %1) local_unnamed_addr #7 {
  %3 = load i8, ptr %0, align 1, !tbaa !9
  %4 = icmp eq i8 %3, 0
  br i1 %4, label %18, label %10

5:                                                ; preds = %16
  %6 = add nuw i64 %11, 1
  %7 = getelementptr inbounds i8, ptr %0, i64 %6
  %8 = load i8, ptr %7, align 1, !tbaa !9
  %9 = icmp eq i8 %8, 0
  br i1 %9, label %18, label %10, !llvm.loop !14

10:                                               ; preds = %2, %5
  %11 = phi i64 [ %6, %5 ], [ 0, %2 ]
  %12 = phi i8 [ %8, %5 ], [ %3, %2 ]
  %13 = getelementptr inbounds i8, ptr %1, i64 %11
  %14 = load i8, ptr %13, align 1, !tbaa !9
  %15 = icmp eq i8 %14, 0
  br i1 %15, label %23, label %16

16:                                               ; preds = %10
  %17 = icmp slt i8 %12, %14
  br i1 %17, label %5, label %24

18:                                               ; preds = %5, %2
  %19 = phi i64 [ 0, %2 ], [ %6, %5 ]
  %20 = getelementptr inbounds i8, ptr %1, i64 %19
  %21 = load i8, ptr %20, align 1, !tbaa !9
  %22 = icmp eq i8 %21, 0
  br i1 %22, label %24, label %23

23:                                               ; preds = %10, %18
  br label %24

24:                                               ; preds = %16, %18, %23
  %25 = phi i32 [ 0, %23 ], [ 1, %18 ], [ 1, %16 ]
  ret i32 %25
}

; Function Attrs: nounwind uwtable
define dso_local noundef ptr @__string_concat(ptr noundef %0, ptr noundef %1) local_unnamed_addr #0 {
  br label %3

3:                                                ; preds = %3, %2
  %4 = phi i64 [ %8, %3 ], [ 0, %2 ]
  %5 = getelementptr inbounds i8, ptr %0, i64 %4
  %6 = load i8, ptr %5, align 1, !tbaa !9
  %7 = icmp eq i8 %6, 0
  %8 = add nuw i64 %4, 1
  br i1 %7, label %9, label %3, !llvm.loop !10

9:                                                ; preds = %3, %9
  %10 = phi i64 [ %14, %9 ], [ 0, %3 ]
  %11 = getelementptr inbounds i8, ptr %1, i64 %10
  %12 = load i8, ptr %11, align 1, !tbaa !9
  %13 = icmp eq i8 %12, 0
  %14 = add nuw i64 %10, 1
  br i1 %13, label %15, label %9, !llvm.loop !10

15:                                               ; preds = %9
  %16 = trunc i64 %4 to i32
  %17 = trunc i64 %10 to i32
  %18 = add nsw i32 %17, %16
  %19 = add nsw i32 %18, 1
  %20 = tail call ptr @malloc(i32 noundef %19) #8
  %21 = tail call ptr @memcpy(ptr noundef %20, ptr noundef %0, i32 noundef %16) #8
  %22 = shl i64 %4, 32
  %23 = ashr exact i64 %22, 32
  %24 = getelementptr inbounds i8, ptr %20, i64 %23
  %25 = tail call ptr @memcpy(ptr noundef %24, ptr noundef nonnull %1, i32 noundef %17) #8
  %26 = sext i32 %18 to i64
  %27 = getelementptr inbounds i8, ptr %20, i64 %26
  store i8 0, ptr %27, align 1, !tbaa !9
  ret ptr %20
}

attributes #0 = { nounwind uwtable "min-legal-vector-width"="0" "no-builtin-malloc" "no-builtin-memcpy" "no-builtin-printf" "no-builtin-strlen" "no-trapping-math"="true" "stack-protector-buffer-size"="8" "target-cpu"="x86-64" "target-features"="+cmov,+cx8,+fxsr,+mmx,+sse,+sse2,+x87" "tune-cpu"="generic" }
attributes #1 = { "no-builtin-malloc" "no-builtin-memcpy" "no-builtin-printf" "no-builtin-strlen" "no-trapping-math"="true" "stack-protector-buffer-size"="8" "target-cpu"="x86-64" "target-features"="+cmov,+cx8,+fxsr,+mmx,+sse,+sse2,+x87" "tune-cpu"="generic" }
attributes #2 = { nofree nounwind uwtable "min-legal-vector-width"="0" "no-builtin-malloc" "no-builtin-memcpy" "no-builtin-printf" "no-builtin-strlen" "no-trapping-math"="true" "stack-protector-buffer-size"="8" "target-cpu"="x86-64" "target-features"="+cmov,+cx8,+fxsr,+mmx,+sse,+sse2,+x87" "tune-cpu"="generic" }
attributes #3 = { nofree nounwind "no-builtin-malloc" "no-builtin-memcpy" "no-builtin-printf" "no-builtin-strlen" "no-trapping-math"="true" "stack-protector-buffer-size"="8" "target-cpu"="x86-64" "target-features"="+cmov,+cx8,+fxsr,+mmx,+sse,+sse2,+x87" "tune-cpu"="generic" }
attributes #4 = { mustprogress nocallback nofree nosync nounwind willreturn memory(argmem: readwrite) }
attributes #5 = { mustprogress nofree norecurse nosync nounwind willreturn memory(none) uwtable "min-legal-vector-width"="0" "no-builtin-malloc" "no-builtin-memcpy" "no-builtin-printf" "no-builtin-strlen" "no-trapping-math"="true" "stack-protector-buffer-size"="8" "target-cpu"="x86-64" "target-features"="+cmov,+cx8,+fxsr,+mmx,+sse,+sse2,+x87" "tune-cpu"="generic" }
attributes #6 = { mustprogress nofree norecurse nosync nounwind willreturn memory(argmem: read) uwtable "min-legal-vector-width"="0" "no-builtin-malloc" "no-builtin-memcpy" "no-builtin-printf" "no-builtin-strlen" "no-trapping-math"="true" "stack-protector-buffer-size"="8" "target-cpu"="x86-64" "target-features"="+cmov,+cx8,+fxsr,+mmx,+sse,+sse2,+x87" "tune-cpu"="generic" }
attributes #7 = { nofree norecurse nosync nounwind memory(argmem: read) uwtable "min-legal-vector-width"="0" "no-builtin-malloc" "no-builtin-memcpy" "no-builtin-printf" "no-builtin-strlen" "no-trapping-math"="true" "stack-protector-buffer-size"="8" "target-cpu"="x86-64" "target-features"="+cmov,+cx8,+fxsr,+mmx,+sse,+sse2,+x87" "tune-cpu"="generic" }
attributes #8 = { nobuiltin nounwind "no-builtin-malloc" "no-builtin-memcpy" "no-builtin-printf" "no-builtin-strlen" }
attributes #9 = { "no-builtin-malloc" "no-builtin-memcpy" "no-builtin-printf" "no-builtin-strlen" }
attributes #10 = { nounwind }

!llvm.module.flags = !{!0, !1, !2, !3}
!llvm.ident = !{!4}

!0 = !{i32 1, !"wchar_size", i32 4}
!1 = !{i32 8, !"PIC Level", i32 2}
!2 = !{i32 7, !"PIE Level", i32 2}
!3 = !{i32 7, !"uwtable", i32 2}
!4 = !{!"Ubuntu clang version 18.1.3 (1ubuntu1)"}
!5 = !{!6, !6, i64 0}
!6 = !{!"int", !7, i64 0}
!7 = !{!"omnipotent char", !8, i64 0}
!8 = !{!"Simple C/C++ TBAA"}
!9 = !{!7, !7, i64 0}
!10 = distinct !{!10, !11}
!11 = !{!"llvm.loop.mustprogress"}
!12 = distinct !{!12, !11}
!13 = distinct !{!13, !11}
!14 = distinct !{!14, !11}
