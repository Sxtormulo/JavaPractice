
import java.lang.foreign.*;
import java.lang.invoke.MethodHandle;
import static java.lang.foreign.ValueLayout.JAVA_BYTE;
import java.util.stream.IntStream;

public class NativeTriangle
{

    void main(String[] args) throws Throwable
    {
        String pattern = (args.length > 0) ? args[0] : "********";
        invokeStrdup(pattern);
    }

    private static void invokeStrdup(String pattern) throws Throwable
    {
        try(var confinedArena = Arena.ofConfined())
        {
            // Allocate off-heap memory and copy the argument, a Java string,
            // into off-heap memory
            var nativeString = confinedArena.allocateFrom(pattern);

            // Obtain an instance of the native linker
            var linker = Linker.nativeLinker();

            // Locate the address of the c function signature
            var stdLib = linker.defaultLookup();
            var strdup_address = stdLib.find("strdup")
                .get();

            // Create a description of the C function
            var layout = MemoryLayout.sequenceLayout(Long.MAX_VALUE, JAVA_BYTE);
            var strdup_signature = FunctionDescriptor.of(ValueLayout.ADDRESS
                .withTargetLayout(layout), ValueLayout.ADDRESS.withTargetLayout(layout));

            // Create a downcall handle for the C function
            var strdup_handle = linker.downcallHandle(strdup_address, strdup_signature);

            // Call the C function directly from java
            var duplicateAddress = (MemorySegment) strdup_handle.invokeExact(nativeString);
            IntStream.iterate(pattern.length() - 1, i -> i >= 0, i -> --i)
                .mapToObj(duplicateAddress::getString)
                .forEach(IO::println);
        }
    }
}
