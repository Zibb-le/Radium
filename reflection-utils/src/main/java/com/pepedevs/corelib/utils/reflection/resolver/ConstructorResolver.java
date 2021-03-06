package com.pepedevs.corelib.utils.reflection.resolver;

import com.pepedevs.corelib.utils.reflection.general.ConstructorReflection;
import com.pepedevs.corelib.utils.reflection.resolver.wrapper.ConstructorWrapper;

import java.lang.reflect.Constructor;

/** Resolver for constructors */
public class ConstructorResolver extends MemberResolver<Constructor> {

    public ConstructorResolver(Class<?> clazz) {
        super(clazz);
    }

    public ConstructorResolver(String className) throws ClassNotFoundException {
        super(className);
    }

    @Override
    public Constructor resolveIndex(int index)
            throws IndexOutOfBoundsException, ReflectiveOperationException {
        return ConstructorReflection.setAccessible(this.clazz.getDeclaredConstructors()[index]);
    }

    @Override
    public Constructor resolveIndexSilent(int index) {
        try {
            return this.resolveIndex(index);
        } catch (IndexOutOfBoundsException | ReflectiveOperationException ignored) {
        }
        return null;
    }

    @Override
    public ConstructorWrapper resolveIndexWrapper(int index) {
        return new ConstructorWrapper<>(this.resolveIndexSilent(index));
    }

    public ConstructorWrapper resolveWrapper(Class<?>[]... types) {
        return new ConstructorWrapper<>(this.resolveSilent(types));
    }

    public Constructor resolveSilent(Class<?>[]... types) {
        try {
            return this.resolve(types);
        } catch (NoSuchMethodException ignored) {
        }
        return null;
    }

    public Constructor resolve(Class<?>[]... types) throws NoSuchMethodException {
        ResolverQuery.Builder builder = ResolverQuery.builder();
        for (Class<?>[] type : types) builder.with(type);
        try {
            return super.resolve(builder.build());
        } catch (ReflectiveOperationException e) {
            throw (NoSuchMethodException) e;
        }
    }

    @Override
    protected Constructor resolveObject(ResolverQuery query) throws ReflectiveOperationException {
        return ConstructorReflection.setAccessible(
                this.clazz.getDeclaredConstructor(query.getTypes()));
    }

    public Constructor resolveFirstConstructor() throws ReflectiveOperationException {
        for (Constructor<?> constructor : this.clazz.getDeclaredConstructors()) {
            return ConstructorReflection.setAccessible(constructor);
        }
        return null;
    }

    public Constructor resolveFirstConstructorSilent() {
        try {
            return this.resolveFirstConstructor();
        } catch (Exception ignored) {
        }
        return null;
    }

    public Constructor resolveLastConstructor() throws ReflectiveOperationException {
        Constructor<?> constructor = null;
        for (Constructor<?> constructor1 : this.clazz.getDeclaredConstructors()) {
            constructor = constructor1;
        }
        if (constructor != null) {
            return ConstructorReflection.setAccessible(constructor);
        }
        return null;
    }

    public Constructor resolveLastConstructorSilent() {
        try {
            return this.resolveLastConstructor();
        } catch (Exception ignored) {
        }
        return null;
    }

    @Override
    protected NoSuchMethodException notFoundException(String joinedNames) {
        return new NoSuchMethodException(
                "Could not resolve constructor for " + joinedNames + " in class " + this.clazz);
    }
}
