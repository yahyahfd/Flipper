# The PHONY directives specify that these targets are not files to avoid errors
.PHONY: all run clean

GRADLE=./gradlew

# Target all builds the project.
all:
	$(GRADLE) build

# Target run executes the program and start with target all to build the
# project.
run : all
	$(GRADLE) run

# Target clean removes all files produced during build.
clean :
	$(GRADLE) clean

