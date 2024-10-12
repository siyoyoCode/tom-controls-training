// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.Constants.OperatorConstants;
import frc.robot.commands.IntakePivotSetPositionCommand;
import frc.robot.commands.SetRollerSpeedCommand;
import frc.robot.subsystems.IntakePivotSubsystem;
import frc.robot.subsystems.RollerSubsystem;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.ConditionalCommand;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.Trigger;

/**
 * This class is where the bulk of the robot should bPe declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and trigger mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  private final IntakePivotSubsystem IntakePivotSubsystem;
  private final RollerSubsystem RollerSubsystem;
  private final XboxController controller = new XboxController(2);
  private final JoystickButton leftBumper = new JoystickButton(controller, XboxController.Button.kLeftBumper.value);
  private final JoystickButton rightBumper = new JoystickButton(controller, XboxController.Button.kRightBumper.value);
  private final JoystickButton xButton = new JoystickButton(controller, XboxController.Button.kX.value);
  private final JoystickButton bButton = new JoystickButton(controller, XboxController.Button.kB.value);
  
  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    IntakePivotSubsystem = new IntakePivotSubsystem();
    RollerSubsystem = new RollerSubsystem();
    configureBindings();
  }

  /**
   * Use this method to define your trigger->command mappings. Triggers can be created via the
   * {@link Trigger#Trigger(java.util.function.BooleanSupplier)} constructor with an arbitrary
   * predicate, or via the named factories in {@link
   * edu.wpi.first.wpilibj2.command.button.CommandGenericHID}'s subclasses for {@link
   * CommandXboxController Xbox}/{@link edu.wpi.first.wpilibj2.command.button.CommandPS4Controller
   * PS4} controllers or {@link edu.wpi.first.wpilibj2.command.button.CommandJoystick Flight
   * joysticks}.
   */
  private void configureBindings() {
    //origonal testing
    //leftBumper.onTrue(new IntakePivotSetPositionCommand(IntakePivotSubsystem, 0));
    //rightBumper.onTrue(new IntakePivotSetPositionCommand(IntakePivotSubsystem, 1));

    //x button toggles intake pivot position
    xButton.onTrue(new ConditionalCommand(
        new IntakePivotSetPositionCommand(IntakePivotSubsystem, 0),
        new IntakePivotSetPositionCommand(IntakePivotSubsystem, 1),
        () -> IntakePivotSubsystem.getEncoderPosition() > .5
    ));
    
    //b button stops roller
    bButton.onTrue(new SetRollerSpeedCommand(RollerSubsystem, 0, 0));

    //triggers set roller speed, speeds found in last year's code then divided by 10 because they were too fast
    leftBumper.onTrue(new SetRollerSpeedCommand(RollerSubsystem, 0.07, 0.055));
    rightBumper.onTrue(new SetRollerSpeedCommand(RollerSubsystem, -0.075, -0.1));

  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An example command will be run in autonomous
    return null;
  }
}
