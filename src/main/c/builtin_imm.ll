; ModuleID = 'builtin.c'
source_filename = "builtin.c"
target datalayout = "e-m:e-p:32:32-i64:64-n32-S128"
target triple = "riscv32-unknown-unknown-elf"

@.str = private unnamed_addr constant [3 x i8] c"%s\00", align 1
@.str.1 = private unnamed_addr constant [4 x i8] c"%s\0A\00", align 1
@.str.2 = private unnamed_addr constant [3 x i8] c"%d\00", align 1
@.str.3 = private unnamed_addr constant [4 x i8] c"%d\0A\00", align 1
@.str.4 = private unnamed_addr constant [5 x i8] c"true\00", align 1
@.str.5 = private unnamed_addr constant [6 x i8] c"false\00", align 1

; Function Attrs: noinline nounwind optnone
define dso_local void @print(ptr noundef %0) #0 {
  %2 = alloca ptr, align 4
  store ptr %0, ptr %2, align 4
  %3 = load ptr, ptr %2, align 4
  %4 = call i32 (ptr, ...) @printf(ptr noundef @.str, ptr noundef %3) #2
  ret void
}

declare dso_local i32 @printf(ptr noundef, ...) #1

; Function Attrs: noinline nounwind optnone
define dso_local void @println(ptr noundef %0) #0 {
  %2 = alloca ptr, align 4
  store ptr %0, ptr %2, align 4
  %3 = load ptr, ptr %2, align 4
  %4 = call i32 (ptr, ...) @printf(ptr noundef @.str.1, ptr noundef %3) #2
  ret void
}

; Function Attrs: noinline nounwind optnone
define dso_local void @printInt(i32 noundef %0) #0 {
  %2 = alloca i32, align 4
  store i32 %0, ptr %2, align 4
  %3 = load i32, ptr %2, align 4
  %4 = call i32 (ptr, ...) @printf(ptr noundef @.str.2, i32 noundef %3) #2
  ret void
}

; Function Attrs: noinline nounwind optnone
define dso_local void @printlnInt(i32 noundef %0) #0 {
  %2 = alloca i32, align 4
  store i32 %0, ptr %2, align 4
  %3 = load i32, ptr %2, align 4
  %4 = call i32 (ptr, ...) @printf(ptr noundef @.str.3, i32 noundef %3) #2
  ret void
}

; Function Attrs: noinline nounwind optnone
define dso_local ptr @getString() #0 {
  %1 = alloca ptr, align 4
  %2 = call ptr @malloc(i32 noundef 4096) #2
  store ptr %2, ptr %1, align 4
  %3 = load ptr, ptr %1, align 4
  %4 = call i32 (ptr, ...) @scanf(ptr noundef @.str, ptr noundef %3) #3
  %5 = load ptr, ptr %1, align 4
  ret ptr %5
}

declare dso_local ptr @malloc(i32 noundef) #1

declare dso_local i32 @scanf(ptr noundef, ...) #1

; Function Attrs: noinline nounwind optnone
define dso_local i32 @getInt() #0 {
  %1 = alloca i32, align 4
  %2 = call i32 (ptr, ...) @scanf(ptr noundef @.str.2, ptr noundef %1) #3
  %3 = load i32, ptr %1, align 4
  ret i32 %3
}

; Function Attrs: noinline nounwind optnone
define dso_local ptr @toString(i32 noundef %0) #0 {
  %2 = alloca i32, align 4
  %3 = alloca ptr, align 4
  store i32 %0, ptr %2, align 4
  %4 = call ptr @malloc(i32 noundef 10) #2
  store ptr %4, ptr %3, align 4
  %5 = load ptr, ptr %3, align 4
  %6 = load i32, ptr %2, align 4
  %7 = call i32 (ptr, ptr, ...) @sprintf(ptr noundef %5, ptr noundef @.str.2, i32 noundef %6) #3
  %8 = load ptr, ptr %3, align 4
  ret ptr %8
}

declare dso_local i32 @sprintf(ptr noundef, ptr noundef, ...) #1

; Function Attrs: noinline nounwind optnone
define dso_local ptr @toString_bool(i1 noundef zeroext %0) #0 {
  %2 = alloca ptr, align 4
  %3 = alloca i8, align 1
  %4 = zext i1 %0 to i8
  store i8 %4, ptr %3, align 1
  %5 = load i8, ptr %3, align 1
  %6 = trunc i8 %5 to i1
  br i1 %6, label %7, label %8

7:                                                ; preds = %1
  store ptr @.str.4, ptr %2, align 4
  br label %9

8:                                                ; preds = %1
  store ptr @.str.5, ptr %2, align 4
  br label %9

9:                                                ; preds = %8, %7
  %10 = load ptr, ptr %2, align 4
  ret ptr %10
}

; Function Attrs: noinline nounwind optnone
define dso_local ptr @__malloc_array(i32 noundef %0, i32 noundef %1) #0 {
  %3 = alloca i32, align 4
  %4 = alloca i32, align 4
  %5 = alloca ptr, align 4
  store i32 %0, ptr %3, align 4
  store i32 %1, ptr %4, align 4
  %6 = load i32, ptr %4, align 4
  %7 = load i32, ptr %3, align 4
  %8 = mul nsw i32 %6, %7
  %9 = add nsw i32 %8, 4
  %10 = call ptr @malloc(i32 noundef %9) #2
  store ptr %10, ptr %5, align 4
  %11 = load i32, ptr %4, align 4
  %12 = load ptr, ptr %5, align 4
  %13 = getelementptr inbounds i32, ptr %12, i32 0
  store i32 %11, ptr %13, align 4
  %14 = load ptr, ptr %5, align 4
  %15 = getelementptr inbounds i32, ptr %14, i32 1
  ret ptr %15
}

; Function Attrs: noinline nounwind optnone
define dso_local ptr @__malloc(i32 noundef %0, i32 noundef %1) #0 {
  %3 = alloca i32, align 4
  %4 = alloca i32, align 4
  store i32 %0, ptr %3, align 4
  store i32 %1, ptr %4, align 4
  %5 = load i32, ptr %4, align 4
  %6 = load i32, ptr %3, align 4
  %7 = mul nsw i32 %5, %6
  %8 = call ptr @malloc(i32 noundef %7) #2
  ret ptr %8
}

; Function Attrs: noinline nounwind optnone
define dso_local i32 @buildInArraySize(ptr noundef %0) #0 {
  %2 = alloca ptr, align 4
  store ptr %0, ptr %2, align 4
  %3 = load ptr, ptr %2, align 4
  %4 = getelementptr inbounds i32, ptr %3, i32 -1
  %5 = load i32, ptr %4, align 4
  ret i32 %5
}

; Function Attrs: noinline nounwind optnone
define dso_local i32 @__string_length(ptr noundef %0) #0 {
  %2 = alloca ptr, align 4
  %3 = alloca i32, align 4
  store ptr %0, ptr %2, align 4
  store i32 0, ptr %3, align 4
  br label %4

4:                                                ; preds = %11, %1
  %5 = load ptr, ptr %2, align 4
  %6 = load i32, ptr %3, align 4
  %7 = getelementptr inbounds i8, ptr %5, i32 %6
  %8 = load i8, ptr %7, align 1
  %9 = zext i8 %8 to i32
  %10 = icmp ne i32 %9, 0
  br i1 %10, label %11, label %14

11:                                               ; preds = %4
  %12 = load i32, ptr %3, align 4
  %13 = add nsw i32 %12, 1
  store i32 %13, ptr %3, align 4
  br label %4, !llvm.loop !5

14:                                               ; preds = %4
  %15 = load i32, ptr %3, align 4
  ret i32 %15
}

; Function Attrs: noinline nounwind optnone
define dso_local ptr @__string_substring(ptr noundef %0, i32 noundef %1, i32 noundef %2) #0 {
  %4 = alloca ptr, align 4
  %5 = alloca i32, align 4
  %6 = alloca i32, align 4
  %7 = alloca i32, align 4
  %8 = alloca ptr, align 4
  %9 = alloca i32, align 4
  store ptr %0, ptr %4, align 4
  store i32 %1, ptr %5, align 4
  store i32 %2, ptr %6, align 4
  %10 = load i32, ptr %6, align 4
  store i32 %10, ptr %7, align 4
  %11 = load i32, ptr %7, align 4
  %12 = add nsw i32 %11, 1
  %13 = call ptr @malloc(i32 noundef %12) #2
  store ptr %13, ptr %8, align 4
  store i32 0, ptr %9, align 4
  br label %14

14:                                               ; preds = %28, %3
  %15 = load i32, ptr %9, align 4
  %16 = load i32, ptr %7, align 4
  %17 = icmp slt i32 %15, %16
  br i1 %17, label %18, label %31

18:                                               ; preds = %14
  %19 = load ptr, ptr %4, align 4
  %20 = load i32, ptr %5, align 4
  %21 = load i32, ptr %9, align 4
  %22 = add nsw i32 %20, %21
  %23 = getelementptr inbounds i8, ptr %19, i32 %22
  %24 = load i8, ptr %23, align 1
  %25 = load ptr, ptr %8, align 4
  %26 = load i32, ptr %9, align 4
  %27 = getelementptr inbounds i8, ptr %25, i32 %26
  store i8 %24, ptr %27, align 1
  br label %28

28:                                               ; preds = %18
  %29 = load i32, ptr %9, align 4
  %30 = add nsw i32 %29, 1
  store i32 %30, ptr %9, align 4
  br label %14, !llvm.loop !7

31:                                               ; preds = %14
  %32 = load ptr, ptr %8, align 4
  %33 = load i32, ptr %7, align 4
  %34 = getelementptr inbounds i8, ptr %32, i32 %33
  store i8 0, ptr %34, align 1
  %35 = load ptr, ptr %8, align 4
  ret ptr %35
}

; Function Attrs: noinline nounwind optnone
define dso_local i32 @__string_parseInt(ptr noundef %0) #0 {
  %2 = alloca ptr, align 4
  %3 = alloca i32, align 4
  %4 = alloca i32, align 4
  store ptr %0, ptr %2, align 4
  store i32 0, ptr %3, align 4
  store i32 0, ptr %4, align 4
  br label %5

5:                                                ; preds = %12, %1
  %6 = load ptr, ptr %2, align 4
  %7 = load i32, ptr %4, align 4
  %8 = getelementptr inbounds i8, ptr %6, i32 %7
  %9 = load i8, ptr %8, align 1
  %10 = zext i8 %9 to i32
  %11 = icmp ne i32 %10, 0
  br i1 %11, label %12, label %24

12:                                               ; preds = %5
  %13 = load i32, ptr %3, align 4
  %14 = mul nsw i32 %13, 10
  %15 = load ptr, ptr %2, align 4
  %16 = load i32, ptr %4, align 4
  %17 = getelementptr inbounds i8, ptr %15, i32 %16
  %18 = load i8, ptr %17, align 1
  %19 = zext i8 %18 to i32
  %20 = sub nsw i32 %19, 48
  %21 = add nsw i32 %14, %20
  store i32 %21, ptr %3, align 4
  %22 = load i32, ptr %4, align 4
  %23 = add nsw i32 %22, 1
  store i32 %23, ptr %4, align 4
  br label %5, !llvm.loop !8

24:                                               ; preds = %5
  %25 = load i32, ptr %3, align 4
  ret i32 %25
}

; Function Attrs: noinline nounwind optnone
define dso_local i32 @__string_ord(ptr noundef %0, i32 noundef %1) #0 {
  %3 = alloca ptr, align 4
  %4 = alloca i32, align 4
  store ptr %0, ptr %3, align 4
  store i32 %1, ptr %4, align 4
  %5 = load ptr, ptr %3, align 4
  %6 = load i32, ptr %4, align 4
  %7 = getelementptr inbounds i8, ptr %5, i32 %6
  %8 = load i8, ptr %7, align 1
  %9 = zext i8 %8 to i32
  ret i32 %9
}

; Function Attrs: noinline nounwind optnone
define dso_local i32 @__string_eq(ptr noundef %0, ptr noundef %1) #0 {
  %3 = alloca i32, align 4
  %4 = alloca ptr, align 4
  %5 = alloca ptr, align 4
  %6 = alloca i32, align 4
  store ptr %0, ptr %4, align 4
  store ptr %1, ptr %5, align 4
  store i32 0, ptr %6, align 4
  br label %7

7:                                                ; preds = %36, %2
  %8 = load ptr, ptr %4, align 4
  %9 = load i32, ptr %6, align 4
  %10 = getelementptr inbounds i8, ptr %8, i32 %9
  %11 = load i8, ptr %10, align 1
  %12 = zext i8 %11 to i32
  %13 = icmp ne i32 %12, 0
  br i1 %13, label %14, label %21

14:                                               ; preds = %7
  %15 = load ptr, ptr %5, align 4
  %16 = load i32, ptr %6, align 4
  %17 = getelementptr inbounds i8, ptr %15, i32 %16
  %18 = load i8, ptr %17, align 1
  %19 = zext i8 %18 to i32
  %20 = icmp ne i32 %19, 0
  br label %21

21:                                               ; preds = %14, %7
  %22 = phi i1 [ false, %7 ], [ %20, %14 ]
  br i1 %22, label %23, label %39

23:                                               ; preds = %21
  %24 = load ptr, ptr %4, align 4
  %25 = load i32, ptr %6, align 4
  %26 = getelementptr inbounds i8, ptr %24, i32 %25
  %27 = load i8, ptr %26, align 1
  %28 = zext i8 %27 to i32
  %29 = load ptr, ptr %5, align 4
  %30 = load i32, ptr %6, align 4
  %31 = getelementptr inbounds i8, ptr %29, i32 %30
  %32 = load i8, ptr %31, align 1
  %33 = zext i8 %32 to i32
  %34 = icmp ne i32 %28, %33
  br i1 %34, label %35, label %36

35:                                               ; preds = %23
  store i32 0, ptr %3, align 4
  br label %55

36:                                               ; preds = %23
  %37 = load i32, ptr %6, align 4
  %38 = add nsw i32 %37, 1
  store i32 %38, ptr %6, align 4
  br label %7, !llvm.loop !9

39:                                               ; preds = %21
  %40 = load ptr, ptr %4, align 4
  %41 = load i32, ptr %6, align 4
  %42 = getelementptr inbounds i8, ptr %40, i32 %41
  %43 = load i8, ptr %42, align 1
  %44 = zext i8 %43 to i32
  %45 = icmp eq i32 %44, 0
  br i1 %45, label %46, label %54

46:                                               ; preds = %39
  %47 = load ptr, ptr %5, align 4
  %48 = load i32, ptr %6, align 4
  %49 = getelementptr inbounds i8, ptr %47, i32 %48
  %50 = load i8, ptr %49, align 1
  %51 = zext i8 %50 to i32
  %52 = icmp eq i32 %51, 0
  br i1 %52, label %53, label %54

53:                                               ; preds = %46
  store i32 1, ptr %3, align 4
  br label %55

54:                                               ; preds = %46, %39
  store i32 0, ptr %3, align 4
  br label %55

55:                                               ; preds = %54, %53, %35
  %56 = load i32, ptr %3, align 4
  ret i32 %56
}

; Function Attrs: noinline nounwind optnone
define dso_local i32 @__string_ne(ptr noundef %0, ptr noundef %1) #0 {
  %3 = alloca ptr, align 4
  %4 = alloca ptr, align 4
  store ptr %0, ptr %3, align 4
  store ptr %1, ptr %4, align 4
  %5 = load ptr, ptr %3, align 4
  %6 = load ptr, ptr %4, align 4
  %7 = call i32 @__string_eq(ptr noundef %5, ptr noundef %6) #3
  %8 = icmp ne i32 %7, 0
  %9 = xor i1 %8, true
  %10 = zext i1 %9 to i32
  ret i32 %10
}

; Function Attrs: noinline nounwind optnone
define dso_local i32 @__string_lt(ptr noundef %0, ptr noundef %1) #0 {
  %3 = alloca i32, align 4
  %4 = alloca ptr, align 4
  %5 = alloca ptr, align 4
  %6 = alloca i32, align 4
  store ptr %0, ptr %4, align 4
  store ptr %1, ptr %5, align 4
  store i32 0, ptr %6, align 4
  br label %7

7:                                                ; preds = %36, %2
  %8 = load ptr, ptr %4, align 4
  %9 = load i32, ptr %6, align 4
  %10 = getelementptr inbounds i8, ptr %8, i32 %9
  %11 = load i8, ptr %10, align 1
  %12 = zext i8 %11 to i32
  %13 = icmp ne i32 %12, 0
  br i1 %13, label %14, label %21

14:                                               ; preds = %7
  %15 = load ptr, ptr %5, align 4
  %16 = load i32, ptr %6, align 4
  %17 = getelementptr inbounds i8, ptr %15, i32 %16
  %18 = load i8, ptr %17, align 1
  %19 = zext i8 %18 to i32
  %20 = icmp ne i32 %19, 0
  br label %21

21:                                               ; preds = %14, %7
  %22 = phi i1 [ false, %7 ], [ %20, %14 ]
  br i1 %22, label %23, label %39

23:                                               ; preds = %21
  %24 = load ptr, ptr %4, align 4
  %25 = load i32, ptr %6, align 4
  %26 = getelementptr inbounds i8, ptr %24, i32 %25
  %27 = load i8, ptr %26, align 1
  %28 = zext i8 %27 to i32
  %29 = load ptr, ptr %5, align 4
  %30 = load i32, ptr %6, align 4
  %31 = getelementptr inbounds i8, ptr %29, i32 %30
  %32 = load i8, ptr %31, align 1
  %33 = zext i8 %32 to i32
  %34 = icmp sge i32 %28, %33
  br i1 %34, label %35, label %36

35:                                               ; preds = %23
  store i32 0, ptr %3, align 4
  br label %55

36:                                               ; preds = %23
  %37 = load i32, ptr %6, align 4
  %38 = add nsw i32 %37, 1
  store i32 %38, ptr %6, align 4
  br label %7, !llvm.loop !10

39:                                               ; preds = %21
  %40 = load ptr, ptr %4, align 4
  %41 = load i32, ptr %6, align 4
  %42 = getelementptr inbounds i8, ptr %40, i32 %41
  %43 = load i8, ptr %42, align 1
  %44 = zext i8 %43 to i32
  %45 = icmp eq i32 %44, 0
  br i1 %45, label %46, label %54

46:                                               ; preds = %39
  %47 = load ptr, ptr %5, align 4
  %48 = load i32, ptr %6, align 4
  %49 = getelementptr inbounds i8, ptr %47, i32 %48
  %50 = load i8, ptr %49, align 1
  %51 = zext i8 %50 to i32
  %52 = icmp eq i32 %51, 0
  br i1 %52, label %53, label %54

53:                                               ; preds = %46
  store i32 0, ptr %3, align 4
  br label %55

54:                                               ; preds = %46, %39
  store i32 1, ptr %3, align 4
  br label %55

55:                                               ; preds = %54, %53, %35
  %56 = load i32, ptr %3, align 4
  ret i32 %56
}

; Function Attrs: noinline nounwind optnone
define dso_local i32 @__string_le(ptr noundef %0, ptr noundef %1) #0 {
  %3 = alloca ptr, align 4
  %4 = alloca ptr, align 4
  store ptr %0, ptr %3, align 4
  store ptr %1, ptr %4, align 4
  %5 = load ptr, ptr %4, align 4
  %6 = load ptr, ptr %3, align 4
  %7 = call i32 @__string_lt(ptr noundef %5, ptr noundef %6) #3
  %8 = icmp ne i32 %7, 0
  %9 = xor i1 %8, true
  %10 = zext i1 %9 to i32
  ret i32 %10
}

; Function Attrs: noinline nounwind optnone
define dso_local i32 @__string_gt(ptr noundef %0, ptr noundef %1) #0 {
  %3 = alloca ptr, align 4
  %4 = alloca ptr, align 4
  store ptr %0, ptr %3, align 4
  store ptr %1, ptr %4, align 4
  %5 = load ptr, ptr %4, align 4
  %6 = load ptr, ptr %3, align 4
  %7 = call i32 @__string_lt(ptr noundef %5, ptr noundef %6) #3
  ret i32 %7
}

; Function Attrs: noinline nounwind optnone
define dso_local i32 @__string_ge(ptr noundef %0, ptr noundef %1) #0 {
  %3 = alloca ptr, align 4
  %4 = alloca ptr, align 4
  store ptr %0, ptr %3, align 4
  store ptr %1, ptr %4, align 4
  %5 = load ptr, ptr %3, align 4
  %6 = load ptr, ptr %4, align 4
  %7 = call i32 @__string_lt(ptr noundef %5, ptr noundef %6) #3
  %8 = icmp ne i32 %7, 0
  %9 = xor i1 %8, true
  %10 = zext i1 %9 to i32
  ret i32 %10
}

; Function Attrs: noinline nounwind optnone
define dso_local ptr @__string_concat(ptr noundef %0, ptr noundef %1) #0 {
  %3 = alloca ptr, align 4
  %4 = alloca ptr, align 4
  %5 = alloca i32, align 4
  %6 = alloca i32, align 4
  %7 = alloca ptr, align 4
  %8 = alloca i32, align 4
  %9 = alloca i32, align 4
  store ptr %0, ptr %3, align 4
  store ptr %1, ptr %4, align 4
  %10 = load ptr, ptr %3, align 4
  %11 = call i32 @__string_length(ptr noundef %10) #3
  store i32 %11, ptr %5, align 4
  %12 = load ptr, ptr %4, align 4
  %13 = call i32 @__string_length(ptr noundef %12) #3
  store i32 %13, ptr %6, align 4
  %14 = load i32, ptr %5, align 4
  %15 = load i32, ptr %6, align 4
  %16 = add nsw i32 %14, %15
  %17 = add nsw i32 %16, 1
  %18 = call ptr @malloc(i32 noundef %17) #2
  store ptr %18, ptr %7, align 4
  store i32 0, ptr %8, align 4
  br label %19

19:                                               ; preds = %31, %2
  %20 = load i32, ptr %8, align 4
  %21 = load i32, ptr %5, align 4
  %22 = icmp slt i32 %20, %21
  br i1 %22, label %23, label %34

23:                                               ; preds = %19
  %24 = load ptr, ptr %3, align 4
  %25 = load i32, ptr %8, align 4
  %26 = getelementptr inbounds i8, ptr %24, i32 %25
  %27 = load i8, ptr %26, align 1
  %28 = load ptr, ptr %7, align 4
  %29 = load i32, ptr %8, align 4
  %30 = getelementptr inbounds i8, ptr %28, i32 %29
  store i8 %27, ptr %30, align 1
  br label %31

31:                                               ; preds = %23
  %32 = load i32, ptr %8, align 4
  %33 = add nsw i32 %32, 1
  store i32 %33, ptr %8, align 4
  br label %19, !llvm.loop !11

34:                                               ; preds = %19
  store i32 0, ptr %9, align 4
  br label %35

35:                                               ; preds = %49, %34
  %36 = load i32, ptr %9, align 4
  %37 = load i32, ptr %6, align 4
  %38 = icmp slt i32 %36, %37
  br i1 %38, label %39, label %52

39:                                               ; preds = %35
  %40 = load ptr, ptr %4, align 4
  %41 = load i32, ptr %9, align 4
  %42 = getelementptr inbounds i8, ptr %40, i32 %41
  %43 = load i8, ptr %42, align 1
  %44 = load ptr, ptr %7, align 4
  %45 = load i32, ptr %5, align 4
  %46 = load i32, ptr %9, align 4
  %47 = add nsw i32 %45, %46
  %48 = getelementptr inbounds i8, ptr %44, i32 %47
  store i8 %43, ptr %48, align 1
  br label %49

49:                                               ; preds = %39
  %50 = load i32, ptr %9, align 4
  %51 = add nsw i32 %50, 1
  store i32 %51, ptr %9, align 4
  br label %35, !llvm.loop !12

52:                                               ; preds = %35
  %53 = load ptr, ptr %7, align 4
  %54 = load i32, ptr %5, align 4
  %55 = load i32, ptr %6, align 4
  %56 = add nsw i32 %54, %55
  %57 = getelementptr inbounds i8, ptr %53, i32 %56
  store i8 0, ptr %57, align 1
  %58 = load ptr, ptr %7, align 4
  ret ptr %58
}

attributes #0 = { noinline nounwind optnone "frame-pointer"="all" "no-builtin-malloc" "no-builtin-memcpy" "no-builtin-printf" "no-builtin-strlen" "no-trapping-math"="true" "stack-protector-buffer-size"="8" "target-cpu"="generic-rv32" "target-features"="+32bit,+a,+c,+m,+relax,-d,-e,-experimental-zacas,-experimental-zcmop,-experimental-zfbfmin,-experimental-zicfilp,-experimental-zicfiss,-experimental-zimop,-experimental-ztso,-experimental-zvfbfmin,-experimental-zvfbfwma,-f,-h,-smaia,-smepmp,-ssaia,-svinval,-svnapot,-svpbmt,-v,-xcvalu,-xcvbi,-xcvbitmanip,-xcvelw,-xcvmac,-xcvmem,-xcvsimd,-xsfvcp,-xsfvfnrclipxfqf,-xsfvfwmaccqqq,-xsfvqmaccdod,-xsfvqmaccqoq,-xtheadba,-xtheadbb,-xtheadbs,-xtheadcmo,-xtheadcondmov,-xtheadfmemidx,-xtheadmac,-xtheadmemidx,-xtheadmempair,-xtheadsync,-xtheadvdot,-xventanacondops,-za128rs,-za64rs,-zawrs,-zba,-zbb,-zbc,-zbkb,-zbkc,-zbkx,-zbs,-zca,-zcb,-zcd,-zce,-zcf,-zcmp,-zcmt,-zdinx,-zfa,-zfh,-zfhmin,-zfinx,-zhinx,-zhinxmin,-zic64b,-zicbom,-zicbop,-zicboz,-ziccamoa,-ziccif,-zicclsm,-ziccrse,-zicntr,-zicond,-zicsr,-zifencei,-zihintntl,-zihintpause,-zihpm,-zk,-zkn,-zknd,-zkne,-zknh,-zkr,-zks,-zksed,-zksh,-zkt,-zmmul,-zvbb,-zvbc,-zve32f,-zve32x,-zve64d,-zve64f,-zve64x,-zvfh,-zvfhmin,-zvkb,-zvkg,-zvkn,-zvknc,-zvkned,-zvkng,-zvknha,-zvknhb,-zvks,-zvksc,-zvksed,-zvksg,-zvksh,-zvkt,-zvl1024b,-zvl128b,-zvl16384b,-zvl2048b,-zvl256b,-zvl32768b,-zvl32b,-zvl4096b,-zvl512b,-zvl64b,-zvl65536b,-zvl8192b" }
attributes #1 = { "frame-pointer"="all" "no-builtin-malloc" "no-builtin-memcpy" "no-builtin-printf" "no-builtin-strlen" "no-trapping-math"="true" "stack-protector-buffer-size"="8" "target-cpu"="generic-rv32" "target-features"="+32bit,+a,+c,+m,+relax,-d,-e,-experimental-zacas,-experimental-zcmop,-experimental-zfbfmin,-experimental-zicfilp,-experimental-zicfiss,-experimental-zimop,-experimental-ztso,-experimental-zvfbfmin,-experimental-zvfbfwma,-f,-h,-smaia,-smepmp,-ssaia,-svinval,-svnapot,-svpbmt,-v,-xcvalu,-xcvbi,-xcvbitmanip,-xcvelw,-xcvmac,-xcvmem,-xcvsimd,-xsfvcp,-xsfvfnrclipxfqf,-xsfvfwmaccqqq,-xsfvqmaccdod,-xsfvqmaccqoq,-xtheadba,-xtheadbb,-xtheadbs,-xtheadcmo,-xtheadcondmov,-xtheadfmemidx,-xtheadmac,-xtheadmemidx,-xtheadmempair,-xtheadsync,-xtheadvdot,-xventanacondops,-za128rs,-za64rs,-zawrs,-zba,-zbb,-zbc,-zbkb,-zbkc,-zbkx,-zbs,-zca,-zcb,-zcd,-zce,-zcf,-zcmp,-zcmt,-zdinx,-zfa,-zfh,-zfhmin,-zfinx,-zhinx,-zhinxmin,-zic64b,-zicbom,-zicbop,-zicboz,-ziccamoa,-ziccif,-zicclsm,-ziccrse,-zicntr,-zicond,-zicsr,-zifencei,-zihintntl,-zihintpause,-zihpm,-zk,-zkn,-zknd,-zkne,-zknh,-zkr,-zks,-zksed,-zksh,-zkt,-zmmul,-zvbb,-zvbc,-zve32f,-zve32x,-zve64d,-zve64f,-zve64x,-zvfh,-zvfhmin,-zvkb,-zvkg,-zvkn,-zvknc,-zvkned,-zvkng,-zvknha,-zvknhb,-zvks,-zvksc,-zvksed,-zvksg,-zvksh,-zvkt,-zvl1024b,-zvl128b,-zvl16384b,-zvl2048b,-zvl256b,-zvl32768b,-zvl32b,-zvl4096b,-zvl512b,-zvl64b,-zvl65536b,-zvl8192b" }
attributes #2 = { nobuiltin "no-builtin-malloc" "no-builtin-memcpy" "no-builtin-printf" "no-builtin-strlen" }
attributes #3 = { "no-builtin-malloc" "no-builtin-memcpy" "no-builtin-printf" "no-builtin-strlen" }

!llvm.module.flags = !{!0, !1, !2, !3}
!llvm.ident = !{!4}

!0 = !{i32 1, !"wchar_size", i32 4}
!1 = !{i32 1, !"target-abi", !"ilp32"}
!2 = !{i32 7, !"frame-pointer", i32 2}
!3 = !{i32 8, !"SmallDataLimit", i32 8}
!4 = !{!"Ubuntu clang version 18.1.3 (1ubuntu1)"}
!5 = distinct !{!5, !6}
!6 = !{!"llvm.loop.mustprogress"}
!7 = distinct !{!7, !6}
!8 = distinct !{!8, !6}
!9 = distinct !{!9, !6}
!10 = distinct !{!10, !6}
!11 = distinct !{!11, !6}
!12 = distinct !{!12, !6}
