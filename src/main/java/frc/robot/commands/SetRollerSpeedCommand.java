// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.RollerSubsystem;

public class SetRollerSpeedCommand extends Command {

  private final RollerSubsystem rollerSubsystem;
  private double frontSpeed, integrationSpeed;

  /** Creates a new SetRollerSpeedCommand. */
  public SetRollerSpeedCommand(RollerSubsystem rollerSubsystem, double frontSpeed, double integrationSpeed) {
    this.rollerSubsystem = rollerSubsystem;
    this.frontSpeed = frontSpeed;
    this.integrationSpeed = integrationSpeed;
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    rollerSubsystem.setRollSpeed(frontSpeed, integrationSpeed);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {}

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return true;
  }
}
