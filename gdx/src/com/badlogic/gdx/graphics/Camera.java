package com.badlogic.gdx.graphics;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Frustum;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Quaternion;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.math.collision.Ray;

public abstract class Camera {

    public final Vector3 position = new Vector3();
    public final Vector3 direction = new Vector3(0, 0, -1);
    public final Vector3 up = new Vector3(0, 1, 0);
    public final Matrix4 projection = new Matrix4();
    public final Matrix4 view = new Matrix4();
    public final Matrix4 combined = new Matrix4();
    public final Matrix4 invProjectionView = new Matrix4();

    public float near = 1;
    public float far = 100;

    public float viewportWidth = 0;
    public float viewportHeight = 0;

    public final Frustum frustum = new Frustum();

    private final Vector3 tmpVec = new Vector3();
    private final Ray ray = new Ray(new Vector3(), new Vector3());

    public abstract void update();

    public abstract void update(boolean updateFrustum);

    public void lookAt(float x, float y, float z) {
        tmpVec.set(x, y, z).sub(position).nor();
        if (!tmpVec.isZero()) {
            float dot = tmpVec.dot(up); // up and direction must ALWAYS be orthonormal vectors
            if (Math.abs(dot - 1) < 0.000000001f) {
                // Collinear
                up.set(direction).scl(-1);
            } else if (Math.abs(dot + 1) < 0.000000001f) {
                // Collinear opposite
                up.set(direction);
            }
            direction.set(tmpVec);
            normalizeUp();
        }
    }

    public void lookAt(Vector3 target) {
        lookAt(target.x, target.y, target.z);
    }

    public void normalizeUp() {
        tmpVec.set(direction).crs(up);
        up.set(tmpVec).crs(direction).nor();
    }

    public void rotate(float angle, float axisX, float axisY, float axisZ) {
        direction.rotate(angle, axisX, axisY, axisZ);
        up.rotate(angle, axisX, axisY, axisZ);
    }

    public void rotate(Vector3 axis, float angle) {
        direction.rotate(axis, angle);
        up.rotate(axis, angle);
    }

    public void rotate(final Matrix4 transform) {
        direction.rot(transform);
        up.rot(transform);
    }

    public void rotate(final Quaternion quat) {
        quat.transform(direction);
        quat.transform(up);
    }

    public void rotateAround(Vector3 point, Vector3 axis, float angle) {
        tmpVec.set(point);
        tmpVec.sub(position);
        translate(tmpVec);
        rotate(axis, angle);
        tmpVec.rotate(axis, angle);
        translate(-tmpVec.x, -tmpVec.y, -tmpVec.z);
    }

    public void transform(final Matrix4 transform) {
        position.mul(transform);
        rotate(transform);
    }

    public void translate(float x, float y, float z) {
        position.add(x, y, z);
    }

    public void translate(Vector3 vec) {
        position.add(vec);
    }

    public Vector3 unproject(Vector3 touchCoords, float viewportX, float viewportY, float viewportWidth, float viewportHeight) {
        float x = touchCoords.x - viewportX, y = Gdx.graphics.getHeight() - touchCoords.y - viewportY;
        touchCoords.x = (2 * x) / viewportWidth - 1;
        touchCoords.y = (2 * y) / viewportHeight - 1;
        touchCoords.z = 2 * touchCoords.z - 1;
        touchCoords.prj(invProjectionView);
        return touchCoords;
    }

    public Vector3 unproject(Vector3 touchCoords) {
        unproject(touchCoords, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        return touchCoords;
    }

    public Vector3 project(Vector3 worldCoords) {
        project(worldCoords, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        return worldCoords;
    }

    public Vector3 project(Vector3 worldCoords, float viewportX, float viewportY, float viewportWidth, float viewportHeight) {
        worldCoords.prj(combined);
        worldCoords.x = viewportWidth * (worldCoords.x + 1) / 2 + viewportX;
        worldCoords.y = viewportHeight * (worldCoords.y + 1) / 2 + viewportY;
        worldCoords.z = (worldCoords.z + 1) / 2;
        return worldCoords;
    }

    public Ray getPickRay(float touchX, float touchY, float viewportX, float viewportY, float viewportWidth, float viewportHeight) {
        unproject(ray.origin.set(touchX, touchY, 0), viewportX, viewportY, viewportWidth, viewportHeight);
        unproject(ray.direction.set(touchX, touchY, 1), viewportX, viewportY, viewportWidth, viewportHeight);
        ray.direction.sub(ray.origin).nor();
        return ray;
    }

    public Ray getPickRay(float touchX, float touchY) {
        return getPickRay(touchX, touchY, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    }

}
