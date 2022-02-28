package fr.radi3nt.maths.components;

import fr.radi3nt.maths.components.matrices.Matrix;
import fr.radi3nt.maths.components.matrices.implementation.MatrixCreator;
import fr.radi3nt.maths.components.vectors.Vector3f;
import fr.radi3nt.maths.components.vectors.implementations.SimpleVector3f;

public class Transform {
	
	private Vector3f translation;
	private Vector3f rotation;
	private Vector3f scaling;

	private Matrix modelMatrix;
	private Matrix worldMatrix;

	private boolean changed = false;

	public Transform()
	{
		setTranslation(new SimpleVector3f(0,0,0));
		setRotation(new SimpleVector3f(0,0,0));
		setScaling(new SimpleVector3f(1,1,1));
	}
	
	public Matrix getWorldMatrix()
	{
		checkUpdate();
		return worldMatrix;
	}
	
	public Matrix getModelMatrix()
	{
		checkUpdate();
		return modelMatrix;
	}

	public Vector3f getTranslation() {
		changed();
		return translation;
	}

	public void setTranslation(Vector3f translation) {
		this.translation = translation;
		changed();
	}

	public Vector3f getRotation() {
		changed();
		return rotation;
	}

	public void setRotation(Vector3f rotation) {
		this.rotation = rotation;
		changed();
	}

	public Vector3f getScaling() {
		changed();
		return scaling;
	}

	public void setScaling(Vector3f scaling) {
		this.scaling = scaling;
		changed();
	}

	private void changed() {
		changed = true;
	}

	private void updateModelMatrix() {
		Matrix rotate = MatrixCreator.createMatrix().rotate((float) Math.toRadians(rotation.getX()), new SimpleVector3f(1, 0, 0)).rotate((float) Math.toRadians(rotation.getY()), new SimpleVector3f(0, 1, 0)).rotate((float) Math.toRadians(rotation.getZ()), new SimpleVector3f(0, 0, 1));
		this.modelMatrix = rotate;
	}

	private void updateWorldMatrix() {
		Matrix translate = MatrixCreator.createMatrix().translation(translation);
		Matrix rotate = MatrixCreator.createMatrix().rotate((float) Math.toRadians(rotation.getX()), new SimpleVector3f(1, 0, 0)).rotate((float) Math.toRadians(rotation.getY()), new SimpleVector3f(0, 1, 0)).rotate((float) Math.toRadians(rotation.getZ()), new SimpleVector3f(0, 0, 1));
		Matrix scale = MatrixCreator.createMatrix().scaling(scaling);
		worldMatrix = translate.mul(rotate).mul(scale);
	}

	private void checkUpdate() {
		if (changed) {
			updateWorldMatrix();
			updateModelMatrix();
		}
		changed = false;
	}
}
