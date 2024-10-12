// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix6.hardware.TalonFX;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkBase.IdleMode;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.RollerConstants;

public class RollerSubsystem extends SubsystemBase {

  private final CANSparkMax frontMotor; //Last year's code uses a CANSparkMax so I hope that's correct
  private final TalonFX integrationMotor;
  /** Creates a new RollerSubsystem. */
  public RollerSubsystem() {
    integrationMotor = new TalonFX(RollerConstants.INTEGRATION_MOTOR_ID_MOTOR_ID);
    frontMotor = new CANSparkMax(RollerConstants.FRONT_MOTOR_ID, MotorType.kBrushless);
    frontMotor.setIdleMode(IdleMode.kBrake); //I don't know what these do but they seem important
    frontMotor.setInverted(true);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  //Sets the speed of the rollers
  public void setRollSpeed(double front, double integration) {
    frontMotor.set(front);
    integrationMotor.set(integration);
  } 
}
